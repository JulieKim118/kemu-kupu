package fileio;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import enums.AnswerStatus;
import statistics.ProportionsFeed;
import statistics.TimelineFeed;
import statistics.VocabularyStatistics;

/**
 * Reads from and writes to the file about the user's attempted spelling of
 * every word tested in the Games Module quiz.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class StatisticsIO extends FileIO {

	/**
	 * Creates a StatisticsIO object with the filepath to the file to modify.
	 *
	 * @param filepath A String object containing the filepath to the file that the
	 *                 object deals with.
	 */
	public StatisticsIO(String filepath) {
		super(filepath);
	}

	/**
	 * Records a spelling attempt to file.
	 *
	 * @param dateTime     An OffsetDateTime object recording when the attempt took
	 *                     place.
	 * @param word         A String containing the word attempted when spelt
	 *                     correctly.
	 * @param wordList     A String containing the topic that the word belongs to.
	 * @param answerStatus An AnswerStatus enumeration representing whether the user
	 *                     has mastered, faulted or failed the attempted word.
	 * @param scoreEarned  An integer representing how much earning the user got
	 *                     from the attempt.
	 */
	public void recordWordSpelling(OffsetDateTime dateTime, String word, String wordList, AnswerStatus answerStatus,
			int scoreEarned) {
		String formatted = word.replace(' ', '-');
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) + " ");
		stringBuilder.append(formatted + " ");
		stringBuilder.append(wordList + " ");
		stringBuilder.append(answerStatus.toString() + " ");
		stringBuilder.append(scoreEarned);
		appendLine(stringBuilder.toString());
	}

	/**
	 * Retrieves user performance about all words in a given wordlist, detailing
	 * information about how many times the mastered, faulted and failed words in
	 * the topic associated with the wordlist.
	 *
	 * @param wordList A String containing the wordlist to retrieve on.
	 * @return A VocabularyStatistics object containing information on words from
	 *         the input wordlist.
	 */
	public VocabularyStatistics getVocabularyStatistics(String wordList) {
		VocabularyStatistics vocabularyStatistics = new VocabularyStatistics();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordIfInTargetWordList(record, wordList, vocabularyStatistics);
		}
		return vocabularyStatistics;
	}

	/**
	 * Helper function to add word to VocabularyStatistics object when it belongs to
	 * the target word list.
	 */
	private void addRecordIfInTargetWordList(String record, String targetWordList,
			VocabularyStatistics vocabularyStatistics) {
		String wordList = getStringPartFromLine(record, 2);
		if (wordList.equals(targetWordList)) {
			String word = getStringPartFromLine(record, 1);
			String answerStatus = getStringPartFromLine(record, 3);
			addToVocabularyStatistics(word, answerStatus, vocabularyStatistics);
		}

	}

	/**
	 * Determines if word is mastered, faulted or failed, then adds it appropriately
	 * to the VocabularyStatistics.
	 */
	private void addToVocabularyStatistics(String word, String answerStatus,
			VocabularyStatistics vocabularyStatistics) {
		switch (answerStatus) {
		case "MASTERED":
			vocabularyStatistics.incrementMastered(word);
			break;
		case "FAULTED":
			vocabularyStatistics.incrementFaulted(word);
			break;
		case "FAILED":
			vocabularyStatistics.incrementFailed(word);
			break;
		default:
			break;
		}
	}

	/**
	 * Gets the timeline feed for mastered attempts for the current day.
	 *
	 * @return A TimelineFeed object containing information about mastered attempts.
	 */
	public TimelineFeed getMasteredTimelineFeedForDay() {
		return getTimelineFeedForDay("MASTERED");
	}

	/**
	 * Gets the timeline feed for faulted attempts for the current day.
	 *
	 * @return A TimelineFeed object containing information about faulted attempts.
	 */
	public TimelineFeed getFaultedTimelineFeedForDay() {
		return getTimelineFeedForDay("FAULTED");
	}

	/**
	 * Gets the timeline feed for failed attempts for the current day.
	 *
	 * @return A TimelineFeed object containing information about failed attempts.
	 */
	public TimelineFeed getFailedTimelineFeedForDay() {
		return getTimelineFeedForDay("FAILED");
	}

	/**
	 * Gets timeline feed for the day, taking mastered, faulted or failed depending
	 * on the input answer status.
	 */
	private TimelineFeed getTimelineFeedForDay(String answerStatus) {
		TimelineFeed timelineFeed = new TimelineFeed();
		initialiseTimelineFeedForDay(timelineFeed);
		OffsetDateTime currentDateTime = OffsetDateTime.now();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordToTimelineIfSameDay(record, currentDateTime, timelineFeed, answerStatus);
		}
		return timelineFeed;
	}

	/**
	 * Fills in the TimelineFeed that will be returned with default values of zeros
	 * for all hours in the day.
	 */
	private void initialiseTimelineFeedForDay(TimelineFeed timelineFeed) {
		for (int i = 0; i < 24; i++) {
			String hourString = String.valueOf(i);
			timelineFeed.addData(hourString + ":00", 0);
		}
	}

	/**
	 * Determines whether an attempt is done on the current day. If so, add it to
	 * the TimelineFeed to be returned.
	 */
	private void addRecordToTimelineIfSameDay(String record, OffsetDateTime currentDateTime, TimelineFeed timelineFeed,
			String answerStatus) {
		String dateTimeString = getStringPartFromLine(record, 0);
		OffsetDateTime recordDateTime = OffsetDateTime.parse(dateTimeString);
		if (currentDateTime.getYear() == recordDateTime.getYear()
				&& currentDateTime.getDayOfYear() == recordDateTime.getDayOfYear()) {
			addRecordToTimelineFeedForDay(record, recordDateTime, timelineFeed, answerStatus);
		}
	}

	/**
	 * Adds attempt to TimelineFeed to be returned if it was attempted on the
	 * current day.
	 */
	private void addRecordToTimelineFeedForDay(String record, OffsetDateTime recordDateTime, TimelineFeed timelineFeed,
			String answerStatus) {
		int hour = recordDateTime.getHour();
		String hourParsed = String.valueOf(hour) + ":00";
		addRecordToTimelineFeed(record, hourParsed, timelineFeed, answerStatus);
	}

	/**
	 * Gets the timeline feed for mastered attempts for the current week.
	 *
	 * @return A TimelineFeed object containing information about mastered attempts.
	 */
	public TimelineFeed getMasteredTimelineFeedForWeek() {
		return getTimelineFeedForWeek("MASTERED");
	}

	/**
	 * Gets the timeline feed for faulted attempts for the current week.
	 *
	 * @return A TimelineFeed object containing information about fautled attempts.
	 */
	public TimelineFeed getFaultedTimelineFeedForWeek() {
		return getTimelineFeedForWeek("FAULTED");
	}

	/**
	 * Gets the timeline feed for failed attempts for the current week.
	 *
	 * @return A TimelineFeed object containing information about failed attempts.
	 */
	public TimelineFeed getFailedTimelineFeedForWeek() {
		return getTimelineFeedForWeek("FAILED");
	}

	/**
	 * Gets timeline feed for the week, taking mastered, faulted or failed depending
	 * on the input answer status.
	 */
	private TimelineFeed getTimelineFeedForWeek(String answerStatus) {
		TimelineFeed timelineFeed = new TimelineFeed();
		initialiseTimelineFeedForWeek(timelineFeed);
		OffsetDateTime currentDateTime = OffsetDateTime.now();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordToTimelineIfSameWeek(record, currentDateTime, timelineFeed, answerStatus);
		}
		return timelineFeed;
	}

	/**
	 * Fills in the TimelineFeed that will be returned with default values of zeros
	 * for all days in the week.
	 */
	private void initialiseTimelineFeedForWeek(TimelineFeed timelineFeed) {
		timelineFeed.addData("Monday", 0);
		timelineFeed.addData("Tuesday", 0);
		timelineFeed.addData("Wednesday", 0);
		timelineFeed.addData("Thursday", 0);
		timelineFeed.addData("Friday", 0);
		timelineFeed.addData("Saturday", 0);
		timelineFeed.addData("Sunday", 0);
	}

	/**
	 * Determines whether an attempt is done on the current week. If so, add it to
	 * the TimelineFeed to be returned.
	 */
	private void addRecordToTimelineIfSameWeek(String record, OffsetDateTime currentDateTime, TimelineFeed timelineFeed,
			String answerStatus) {
		String dateTimeString = getStringPartFromLine(record, 0);
		OffsetDateTime recordDateTime = OffsetDateTime.parse(dateTimeString);
		int currentWeekOfYear = getWeekOfYear(currentDateTime);
		int recordWeekOfYear = getWeekOfYear(recordDateTime);
		if (currentDateTime.getYear() == recordDateTime.getYear() && currentWeekOfYear == recordWeekOfYear) {
			addRecordToTimelineFeedForWeek(record, recordDateTime, timelineFeed, answerStatus);
		}
	}

	/**
	 * Adds attempt to TimelineFeed to be returned if it was attempted on the
	 * current week.
	 */
	private void addRecordToTimelineFeedForWeek(String record, OffsetDateTime recordDateTime, TimelineFeed timelineFeed,
			String answerStatus) {
		DayOfWeek dayOfWeek = recordDateTime.getDayOfWeek();
		String dayOfWeekParsed;
		switch (dayOfWeek) {
		case MONDAY:
			dayOfWeekParsed = "Monday";
			break;
		case TUESDAY:
			dayOfWeekParsed = "Tuesday";
			break;
		case WEDNESDAY:
			dayOfWeekParsed = "Wednesday";
			break;
		case THURSDAY:
			dayOfWeekParsed = "Thursday";
			break;
		case FRIDAY:
			dayOfWeekParsed = "Friday";
			break;
		case SATURDAY:
			dayOfWeekParsed = "Saturday";
			break;
		default:
			dayOfWeekParsed = "Sunday";
			break;
		}
		addRecordToTimelineFeed(record, dayOfWeekParsed, timelineFeed, answerStatus);
	}

	/**
	 * Helper method to add a record to the input timeline feed.
	 */
	private void addRecordToTimelineFeed(String record, String category, TimelineFeed timelineFeed,
			String answerStatus) {
		String answerStatusString = getStringPartFromLine(record, 3);
		if (answerStatus.equals(answerStatusString)) {
			timelineFeed.addData(category, 1);
		}
	}

	/**
	 * Retrieves the proportions of mastered, faulted and failed attempts in the
	 * day.
	 *
	 * @return A ProportionsFeed object containing information about the mastered,
	 *         faulted and failed attempts in the day.
	 */
	public ProportionsFeed getProportionsFeedForDay() {
		ProportionsFeed proportionsFeed = new ProportionsFeed();
		OffsetDateTime currentDateTime = OffsetDateTime.now();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordToProportionsIfSameDay(record, currentDateTime, proportionsFeed);
		}
		return proportionsFeed;
	}

	/**
	 * Determine if a record falls on the same day. If so, include it in the
	 * ProportionsFeed.
	 */
	private void addRecordToProportionsIfSameDay(String record, OffsetDateTime currentDateTime,
			ProportionsFeed proportionsFeed) {
		String dateTimeString = getStringPartFromLine(record, 0);
		OffsetDateTime recordDateTime = OffsetDateTime.parse(dateTimeString);
		if (currentDateTime.getYear() == recordDateTime.getYear()
				&& currentDateTime.getDayOfYear() == recordDateTime.getDayOfYear()) {
			addRecordToProportionsFeed(record, proportionsFeed);
		}
	}

	/**
	 * Retrieves the proportions of mastered, faulted and failed attempts in the
	 * week.
	 *
	 * @return A ProportionsFeed object containing information about the mastered,
	 *         faulted and failed attempts in the week.
	 */
	public ProportionsFeed getProportionsFeedForWeek() {
		ProportionsFeed proportionsFeed = new ProportionsFeed();
		OffsetDateTime currentDateTime = OffsetDateTime.now();
		ArrayList<String> records = readLines();
		for (String record : records) {
			addRecordToProportionsIfSameWeek(record, currentDateTime, proportionsFeed);
		}
		return proportionsFeed;
	}

	/**
	 * Determine if a record falls on the same week. If so, include it in the
	 * ProportionsFeed.
	 */
	private void addRecordToProportionsIfSameWeek(String record, OffsetDateTime currentDateTime,
			ProportionsFeed proportionsFeed) {
		String dateTimeString = getStringPartFromLine(record, 0);
		OffsetDateTime recordDateTime = OffsetDateTime.parse(dateTimeString);
		int currentWeekOfYear = getWeekOfYear(currentDateTime);
		int recordWeekOfYear = getWeekOfYear(recordDateTime);
		if (currentDateTime.getYear() == recordDateTime.getYear() && currentWeekOfYear == recordWeekOfYear) {
			addRecordToProportionsFeed(record, proportionsFeed);
		}
	}

	/**
	 * Get the week number for the input OffsetDateTime object.
	 */
	private int getWeekOfYear(OffsetDateTime dateTime) {
		Calendar calendar = Calendar.getInstance();
		int year = dateTime.getYear();
		int monthValue = dateTime.getMonthValue();
		int dayOfMonth = dateTime.getDayOfMonth();
		calendar.set(year, monthValue - 1, dayOfMonth);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Helper function to add a record to the input ProportionsFeed object.
	 */
	private void addRecordToProportionsFeed(String record, ProportionsFeed proportionsFeed) {
		String answerStatus = getStringPartFromLine(record, 3);
		switch (answerStatus) {
		case "MASTERED":
			proportionsFeed.incrementMastered();
			break;
		case "FAULTED":
			proportionsFeed.incrementFaulted();
			break;
		case "FAILED":
			proportionsFeed.incrementFailed();
			break;
		default:
			break;
		}
	}

	/**
	 * Remove all records about attempts from the file.
	 */
	public void resetStatistics() {
		clearFile();
	}
}
