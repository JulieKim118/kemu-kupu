package controller;

import java.util.ArrayList;

import application.FileSaveLocations;
import fileio.HighestEarningsIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for the Highest Earnings view of the Statistics screen.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class HighestEarningsView extends Controller {
	@FXML
	private AnchorPane container1;
	@FXML
	private AnchorPane container2;
	@FXML
	private AnchorPane container3;
	@FXML
	private AnchorPane container4;
	@FXML
	private AnchorPane container5;

	@FXML
	private Label score1Label;
	@FXML
	private Label score2Label;
	@FXML
	private Label score3Label;
	@FXML
	private Label score4Label;
	@FXML
	private Label score5Label;

	/**
	 * Called as soon as the window is shown.
	 */
	@FXML
	private void initialize() {
		HighestEarningsIO highestEarningsIO = new HighestEarningsIO(FileSaveLocations.QUIZ_EARNINGS);
		ArrayList<String> highestEarningsStrings = highestEarningsIO.getHighestQuizEarningsAsStrings();
		int numberOfScores = highestEarningsStrings.size();
		resetLabelVisibility(numberOfScores);
		fillUpLabels(numberOfScores, highestEarningsStrings);
	}

	/**
	 * Determines the visibility of the containers, depending on whether they can be
	 * filled up or not.
	 *
	 * @param numberOfScores
	 */
	private void resetLabelVisibility(int numberOfScores) {
		container1.setVisible(false);
		container2.setVisible(false);
		container3.setVisible(false);
		container4.setVisible(false);
		container5.setVisible(false);

		if (numberOfScores > 0) {
			container1.setVisible(true);
		}
		if (numberOfScores > 1) {
			container2.setVisible(true);
		}
		if (numberOfScores > 2) {
			container3.setVisible(true);
		}
		if (numberOfScores > 3) {
			container4.setVisible(true);
		}
		if (numberOfScores > 4) {
			container5.setVisible(true);
		}

	}

	/**
	 * Fill up the labels with the content, if any.
	 */
	private void fillUpLabels(int numberOfScores, ArrayList<String> scoresString) {
		if (numberOfScores > 0) {
			score1Label.setText(scoresString.get(0));
		}
		if (numberOfScores > 1) {
			score2Label.setText(scoresString.get(1));
		}
		if (numberOfScores > 2) {
			score3Label.setText(scoresString.get(2));
		}
		if (numberOfScores > 3) {
			score4Label.setText(scoresString.get(3));
		}
		if (numberOfScores > 4) {
			score5Label.setText(scoresString.get(4));
		}
	}

	/**
	 * Navigate to the Spelling Performance view.
	 */
	@FXML
	private void goToSpellingPerformance(ActionEvent event) {
		switchScene(event, "SpellingPerformance.fxml");
	}

	/**
	 * Navigate to the My Vocabulary view.
	 */
	@FXML
	private void goToMyVocabulary(ActionEvent event) {
		switchScene(event, "MyVocabulary.fxml");
	}
}
