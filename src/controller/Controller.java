package controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

/**
 * 
 * This class is the parent class for all scene controller classes.
 * It implements the basic switch scene method, and back to main method, which are universally used in controllers.
 * @author Julie Kim
 *
 */

public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 *
	 * Switches scene to new scene by calling specified fxml file. 
	 * 
	 * @param event			The scene ActionEvent of a button click that invokes switch scene
	 * @param nextScene		The String name of a fxml file that is the intended next scene to be switched to
	 */
	public void switchScene(ActionEvent event, String nextScene) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/" + nextScene));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Switches current scene to the Main Menu scene. 
	 * @param back The ActionEvent from clicking the back button
	 */
	public void backToMain(ActionEvent back) {
		switchScene(back, "MainMenu.fxml");
	}
	
}
