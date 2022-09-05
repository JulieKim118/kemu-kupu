package quiz;

import enums.AnswerStatus;
import enums.CashAmount;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;

/**
 * This is a helper model class that provides methods for the Results View controller class. 
 * It has the methods to return tree level and its according image based on user's total score. 
 * It implements the line chart to be displayed in the results screen. 
 * 
 * @author Julie Kim
 *
 */
public class ResultsModel {

	private ScoreTracker scoreTracker;
	private CashAmount cashAmount;

	//Get image from file to display for tree levels
	Image oneCoinImage = new Image(getClass().getResourceAsStream("/resources/Coin.png"));
	Image twoCoinImage = new Image(getClass().getResourceAsStream("/resources/Coin_2.png"));
	Image threeCoinImage = new Image(getClass().getResourceAsStream("/resources/Coin_3.png"));
	Image fourCoinImage = new Image(getClass().getResourceAsStream("/resources/Coin_4.png"));
	Image lotCoinImage = new Image(getClass().getResourceAsStream("/resources/Coin_5.png"));
	Image maxCoinImage = new Image(getClass().getResourceAsStream("/resources/Coin_6.png"));
	Image masteredIcon = new Image(getClass().getResourceAsStream("/resources/Mastered_icon.png"));
	Image faultedIcon = new Image(getClass().getResourceAsStream("/resources/Faulted_icon.png"));
	Image failedIcon = new Image(getClass().getResourceAsStream("/resources/Failed_icon.png"));
	private AnswerAttemptTracker answerAttemptTracker;
	private AnswerStatusTracker answerStatusTracker;

	/**
	 * Constructor receives the Score Tracker user data from the results view controller, from the games module
	 * @param scoreTracker	Score Tracker instance with user data of the results of the quiz game. 
	 */
	public ResultsModel(ScoreTracker scoreTracker, AnswerAttemptTracker answerAttemptTracker, AnswerStatusTracker answerStatusTracker) {
		this.scoreTracker = scoreTracker;
		this.answerAttemptTracker = answerAttemptTracker;
		this.answerStatusTracker = answerStatusTracker;
	}

	/**
	 * Returns the tree level name accordingly to the user's score of the quiz. 
	 * @param score	The total score of the user's quiz
	 * @return String level	The tree level of the user's last quiz game. 
	 */
	private CashAmount determineLevel(int score) {
		if (score < 60) {
			cashAmount = CashAmount.ONECOIN;
		} else if (score < 160) {
			cashAmount = CashAmount.TWOCOINS;
		} else if (score < 300) {
			cashAmount = CashAmount.THREECOINS;
		} else if (score < 650) {
			cashAmount = CashAmount.FOURCOINS;
		} else if (score < 1000) {
			cashAmount = CashAmount.LOTSCOINS;
		} else {
			cashAmount = CashAmount.MAXCOINS;
		}
		return cashAmount;
	}

	/**
	 * Returns the image of the user's last quiz game result accordingly to the total score. 
	 * @param level	The string name of the tree level. 
	 * @return Image	image of the correct tree level
	 */

public Image displayImage(int score) {
		
		cashAmount = determineLevel(score);
		
		if (cashAmount == CashAmount.ONECOIN) {
			return oneCoinImage;
		} else if (cashAmount == CashAmount.TWOCOINS) {
			return twoCoinImage;
		} else if (cashAmount == CashAmount.THREECOINS) {
			return threeCoinImage;
		} else if (cashAmount == CashAmount.FOURCOINS) {
			return fourCoinImage;
		} else if (cashAmount == CashAmount.LOTSCOINS) {
			return lotCoinImage;
		} else {
			return maxCoinImage;
		}
	}

	/**
	 * Sets up the line chart that displays cumulative score after each word
	 * Displays the progress of the score through the quiz
	 * @param chart LineChart with x,y axis properties
	 */
	public void setChart(LineChart<String, Number> chart) {
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.getData().add(new XYChart.Data<String, Number>(scoreTracker.getWord(1), scoreTracker.getScore(1)));
		series.getData()
				.add(new XYChart.Data<String, Number>(scoreTracker.getWord(2), scoreTracker.getCumulativeScore(2)));
		series.getData()
				.add(new XYChart.Data<String, Number>(scoreTracker.getWord(3), scoreTracker.getCumulativeScore(3)));
		series.getData()
				.add(new XYChart.Data<String, Number>(scoreTracker.getWord(4), scoreTracker.getCumulativeScore(4)));
		series.getData()
				.add(new XYChart.Data<String, Number>(scoreTracker.getWord(5), scoreTracker.getCumulativeScore(5)));
		chart.getData().add(series);
		Node line = series.getNode().lookup(".chart-series-line");
		line.setStyle("-fx-stroke: #2e979b");
	}
	
	public String getAttempt(int questionNumber) {
		if (answerStatusTracker.getAnswerStatus(questionNumber) == AnswerStatus.FAILED) {
			return answerAttemptTracker.getAnswerAttempt(questionNumber);
		} else {
			return "";
		}
	}
	
	public Image getStatusImage(int questionNumber) {
		if (answerStatusTracker.getAnswerStatus(questionNumber) == AnswerStatus.MASTERED) {
			return masteredIcon;
		} else if (answerStatusTracker.getAnswerStatus(questionNumber) == AnswerStatus.FAULTED) {
			return faultedIcon;
		} else {
			return failedIcon;
		}
	}
}
