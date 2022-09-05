package controller;

import java.util.ArrayList;

import application.HelpBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import quiz.AnswerAttemptTracker;
import quiz.AnswerStatusTracker;
import quiz.ResultsModel;
import quiz.ScoreTracker;

/**
 * This is a controller class for the practice results screen. 
 * This class initialises the practice results screen, displaying the user's result for the practice. 
 * It displays the words used in practice, the user's attempt results for each words (mastered, faulted, failed) 
 * and the incorrect attempt of spelling by the user. 
 * 
 * @author Julie Kim
 *
 */
public class PracticeResultsView extends Controller {
	
	@FXML
	private Button viewUpButton, viewDownButton, repracticeButton, backButton, helpButton;
	@FXML
	private Label wordOne, wordTwo, wordThree, wordFour, wordFive;
	@FXML
	private Label wordOneAttemptLabel, wordTwoAttemptLabel, wordThreeAttemptLabel, wordFourAttemptLabel, wordFiveAttemptLabel;
	@FXML
	private ImageView wordOneStatus, wordTwoStatus, wordThreeStatus, wordFourStatus, wordFiveStatus;
	
	private ResultsModel resultsModel; 
	private ScoreTracker scoreTracker;
	private int numberOfQuestions;
	private String[] wordsTested;
	private String[] wordsAttempt;
	private Image[] wordsStatusImage;	
	private ArrayList<Label> wordsTestedLabel = new ArrayList<Label>();
	private ArrayList<Label> wordsAttemptLabel = new ArrayList<Label>();
	private ArrayList<ImageView> wordsStatusImageView = new ArrayList<ImageView>();
	
	/**
	 *Initially sets up the controller. It calls methods from the helper model
	 * class ResultsModel to get the user attempts if failed and status image to display. 
	 * It sets up the display of the user's practice results. 
	 *
	 * @param scoreTracker ScoreTracker user data from the previous game screen that
	 *                     stores the score datas of questions
	 * @param answerAttemptTracker AnswerAttemptTracker user data from the previous practice game screen
	 * 							   that stores the user attempts of the practice questions. 
	 * @param answerStatusTracker AnswerStatusTracker user data from the previous practice game screen
	 * 							   that stores the status of each questions in the practice. 
	 * @param numberOfQuestions int number of questions in practice
	 */
	public void setUp(ScoreTracker scoreTracker, AnswerAttemptTracker answerAttemptTracker, AnswerStatusTracker answerStatusTracker, int numberOfQuestions) {
		this.resultsModel = new ResultsModel(scoreTracker, answerAttemptTracker, answerStatusTracker);
		this.scoreTracker = scoreTracker;
		
		// Call function to store @FXML feature variables in an array
		initialiseArrayList();
		
		this.numberOfQuestions = numberOfQuestions;
		wordsTested = new String[numberOfQuestions];
		wordsAttempt = new String[numberOfQuestions];
		wordsStatusImage = new Image[numberOfQuestions];
		
		// Store the tested words, the user's attempts, and the attempt result status in an array
		for (int i = 0; i < numberOfQuestions; i++) {
			wordsTested[i] = scoreTracker.getWord(i+1);
			wordsAttempt[i] = resultsModel.getAttempt(i+1);
			wordsStatusImage[i] = resultsModel.getStatusImage(i+1);
		}
		
		viewUpButton.setDisable(true);
		
		if(numberOfQuestions < 6) {
			viewDownButton.setDisable(true);
		}
		
		//initial display of the screen
		setScreenOne();
	}
	
	/**
	 * Changes the results display from screen one to two and vice versa
	 * @param event ActionEvent from the viewUp and viewDown buttons. 
	 */
	public void changeWordList(ActionEvent event) {
		Button buttonClicked = (Button) event.getSource();
		String buttonID = buttonClicked.getId();
		
		if(buttonID.equals("viewUpButton")) {
			setScreenOne();		
		} else {
			setScreenTwo();
		}
	}
	
	/**
	 * Sets screen one to display the result of first five words in the practice. 
	 */
	private void setScreenOne() {
		
		viewUpButton.setDisable(true);
		if (numberOfQuestions > 5) {
			viewDownButton.setDisable(false);
		}
		
		//For words 1~5, set text of labels of words tested, and the user's attempt
		//Set corresponding status image to each word attempts. 
		for (int j = 0; j < wordsTestedLabel.size(); j++) {
			wordsTestedLabel.get(j).setText(wordsTested[j]);
			wordsAttemptLabel.get(j).setText(wordsAttempt[j]);
			wordsStatusImageView.get(j).setVisible(true);
			wordsStatusImageView.get(j).setImage(wordsStatusImage[j]);
		}
	}
	
	/**
	 * Sets screen two to display the result of sixth to tenth words in the practice. 
	 */
	public void setScreenTwo() {
		
		viewUpButton.setDisable(false);
		viewDownButton.setDisable(true);
		
		//For remaining words, set text of labels of words tested, and the user's attempt
		//and set corresponding status image to each word attempts. 
		for (int k = 5; k < wordsTested.length; k++) {
			wordsTestedLabel.get(k-5).setText(wordsTested[k]);
			wordsAttemptLabel.get(k-5).setText(wordsAttempt[k]);
			wordsStatusImageView.get(k-5).setImage(wordsStatusImage[k]);
		}
		
		//If there is lesser than ten words, set remaining spaces as blank by setting labels and images empty. 
		int numberOfWordsInScreenTwo = numberOfQuestions - wordsTestedLabel.size();
		for (int m = numberOfWordsInScreenTwo; m < wordsTestedLabel.size(); m++) {
			wordsTestedLabel.get(m).setText("");
			wordsAttemptLabel.get(m).setText("");
			wordsStatusImageView.get(m).setVisible(false);
		}
	}
	
	/**
	 * This method initialises the array list to store the @FXML variables for convenient call. 
	 */
	private void initialiseArrayList() {
		wordsTestedLabel.add(wordOne);
		wordsTestedLabel.add(wordTwo);
		wordsTestedLabel.add(wordThree);
		wordsTestedLabel.add(wordFour);
		wordsTestedLabel.add(wordFive);
		wordsAttemptLabel.add(wordOneAttemptLabel);
		wordsAttemptLabel.add(wordTwoAttemptLabel);
		wordsAttemptLabel.add(wordThreeAttemptLabel);
		wordsAttemptLabel.add(wordFourAttemptLabel);
		wordsAttemptLabel.add(wordFiveAttemptLabel);
		wordsStatusImageView.add(wordOneStatus);
		wordsStatusImageView.add(wordTwoStatus);
		wordsStatusImageView.add(wordThreeStatus);
		wordsStatusImageView.add(wordFourStatus);
		wordsStatusImageView.add(wordFiveStatus);
	}
	
	/**
	 * This method switches scene to the practice topic list screen for another practice. 
	 * @param event ActionEvent from the practice again button
	 */
	public void practiceAgain(ActionEvent event) {
		switchScene(event, "PracticeTopic.fxml");
	}
	
	/**
	 * Opens the help window for the practice results screen. 
	 * @param event ActionEvent from the help icon Button. 
	 */
	public void openHelpWindow(ActionEvent event) {
		String sceneName = "PracticeResults";
		HelpBox helpBox = new HelpBox(sceneName);
		helpBox.display();
	}

}
