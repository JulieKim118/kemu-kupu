package controller;

import application.Cash;
import application.FileSaveLocations;
import application.HelpBox;
import fileio.CashIO;
import fileio.HighestEarningsIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import quiz.AnswerAttemptTracker;
import quiz.AnswerStatusTracker;
import quiz.ResultsModel;
import quiz.ScoreTracker;

/**
 * This is a controller class for the Results View screen. 
 * It displays the result of the quiz taken. The informations displayed are: total score, 
 * the cash level image the user earned, the table for spelling results,and a graph of score progress.
 *
 * @author Julie Kim
 * @author Juwon Jung
 *
 */
public class ResultsView extends Controller {

	@FXML
	private Button playAgainButton;
	@FXML
	private Button backButton;
	@FXML
	private Button viewTreeButton;
	@FXML
	private Button helpButton;
	@FXML
	private Label scoreLabel;
	@FXML
	private Label levelLabel;
	@FXML
	private ImageView cashImage;
	@FXML
	private Label wordOne, wordTwo, wordThree, wordFour, wordFive;
	@FXML
	private Label wordOneAttemptLabel, wordTwoAttemptLabel, wordThreeAttemptLabel, wordFourAttemptLabel,
			wordFiveAttemptLabel;
	@FXML
	private ImageView wordOneStatus, wordTwoStatus, wordThreeStatus, wordFourStatus, wordFiveStatus;
	@FXML
	LineChart<String, Number> scoreChart;

	private int totalScore;
	private ResultsModel resultsModel;
	private AnswerAttemptTracker answerAttemptTracker;
	private AnswerStatusTracker answerStatusTracker;
	private Cash cash;

	/**
	 * Initially sets up the controller. It calls methods from the helper model
	 * class ResultsModel to get the values and image to display. It sets up the
	 * total score, the cash level, the word results and a line chart.
	 *
	 * @param scoreTracker ScoreTracker user data from the previous game screen that
	 *                     stores the score datas of questions.
	 * @param answerAttemptTracker AnswerAttemptTracker user data from the previous practice game screen
	 * 							   that stores the user attempts of the practice questions. 
	 * @param answerStatusTracker AnswerStatusTracker user data from the previous practice game screen
	 * 							   that stores the status of each questions in the practice. 
	 */

	public void setUp(ScoreTracker scoreTracker, AnswerAttemptTracker answerAttemptTracker,
			AnswerStatusTracker answerStatusTracker) {

		resultsModel = new ResultsModel(scoreTracker, answerAttemptTracker, answerStatusTracker);

		totalScore = scoreTracker.getTotalScore();
		scoreLabel.setText(String.valueOf(totalScore));
		cashImage.setImage(resultsModel.displayImage(totalScore));

		resultsModel.setChart(scoreChart);

		wordOne.setText(scoreTracker.getWord(1));
		wordTwo.setText(scoreTracker.getWord(2));
		wordThree.setText(scoreTracker.getWord(3));
		wordFour.setText(scoreTracker.getWord(4));
		wordFive.setText(scoreTracker.getWord(5));

		wordOneAttemptLabel.setText(resultsModel.getAttempt(1));
		wordTwoAttemptLabel.setText(resultsModel.getAttempt(2));
		wordThreeAttemptLabel.setText(resultsModel.getAttempt(3));
		wordFourAttemptLabel.setText(resultsModel.getAttempt(4));
		wordFiveAttemptLabel.setText(resultsModel.getAttempt(5));

		wordOneStatus.setImage(resultsModel.getStatusImage(1));
		wordTwoStatus.setImage(resultsModel.getStatusImage(2));
		wordThreeStatus.setImage(resultsModel.getStatusImage(3));
		wordFourStatus.setImage(resultsModel.getStatusImage(4));
		wordFiveStatus.setImage(resultsModel.getStatusImage(5));

		saveEarnings(totalScore);

	}

	/**
	 * Switches current scene to the topic list scene for another game.
	 * @param event ActionEvent from the play again button.
	 */
	public void playAgain(ActionEvent event) {
		switchScene(event, "TopicList.fxml");
	}

	/**
	 * Switches current scene to the My tree screen
	 * @param event ActionEvent from the view my tree button
	 */
	public void viewTree(ActionEvent event) {
		switchScene(event, "MyTree.fxml");
	}

	/**
	 * Saves the earnings of the score and cash from each game.
	 * @param totalScore Integer of total cash/score earned from each game
	 */
	private void saveEarnings(int totalScore) {
		saveCash(totalScore);
		recordEarningsToStatistics(totalScore);
	}

	/**
	 * Adds the total cash earned from each game into the total amount of cash stored
	 * in the cash file. Save and store the updated total amount of cash.
	 * @param totalScore Integer of total cash earned from each game.
	 */
	private void saveCash(int totalScore) {
		CashIO cashIO = new CashIO(FileSaveLocations.CASH);
		cash = cashIO.loadCash();
		cash.deposit(totalScore);
		cashIO.saveCash(cash);
	}

	/**
	 * Save the total Score of each game into the file quiz earnings.
	 * @param totalScore Integer of total score from each game.
	 */
	private void recordEarningsToStatistics(int totalScore) {
		HighestEarningsIO highestEarningsIO = new HighestEarningsIO(FileSaveLocations.QUIZ_EARNINGS);
		highestEarningsIO.recordQuizEarning(totalScore);
	}

	/**
	 * Opens the help window for the results screen
	 * @param event ActionEvent from the help icon button
	 */
	public void openHelpWindow(ActionEvent event) {
		String sceneName = "GameResults";
		HelpBox helpBox = new HelpBox(sceneName);
		helpBox.display();
	}
}
