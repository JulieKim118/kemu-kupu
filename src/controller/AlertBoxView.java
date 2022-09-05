package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This the controller class for AlertBox.
 * It is a pop-up screen for confirmation of exiting screen.
 * @author Juwon Jung
 * @author Jared Daniel Recomendable
 *
 */
public class AlertBoxView {

	@FXML
	private Label header;
	@FXML
	private Label description;
	
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;
	
	private boolean result;
	
	public void setUp(String headerText, String descriptionText) {
		header.setText(headerText);
		description.setText(descriptionText);
	}
	
	/**
	 * When NO is pressed the pop-up screen closes.
	 */
	@FXML
	private void cancelAlert() {
		result = false;
		Stage stage = (Stage) btnOK.getScene().getWindow();
		stage.close();
	}

	/**
	 * When YES is pressed the pop-up screen closes.
	 */
	@FXML
	private void OKAlert() {
		result = true;
		Stage stage = (Stage) btnOK.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Gets result when NO and YES button is pressed.
	 * @return result
	 */
	public boolean getResult() {
		return result;
	}
	
	
}
