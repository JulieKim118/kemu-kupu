package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts Main application.
 *
 * @author Juwon Jung
 *
 */
public class Main extends Application {
	/**
	 * Starts application from main menu screen.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml")); // EDITTED
			Scene main = new Scene(root);
			main.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Display scene
			primaryStage.setScene(main);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
