package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * This is a controller class for the Help Box pop up screen. 
 * 
 * @author Julie Kim
 *
 */
public class HelpBoxView {
	
	@FXML
	private ImageView helpImage;
	@FXML
	private Button closeButton;
	
	/**
	 * This method sets up the help box screen with the particular help labels indicated image for the screen. 
	 * @param sceneName receive the scene name that is calling the help box
	 */
	public void setUp(String sceneName) {
		Image helpImageSource = new Image(getClass().getResourceAsStream("/resources/"+sceneName+"_Help.png"));
		helpImage.setImage(helpImageSource);
	}
	
	/**
	 * This method closes the help window when the close button is pressed.
	 * @param event
	 */
	public void closeHelpWindow(ActionEvent event) {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}

}
