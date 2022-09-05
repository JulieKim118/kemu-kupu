package application;

import controller.HelpBoxView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelpBox {
	
	String sceneName = "";
	
	public HelpBox(String sceneName) {
		this.sceneName = sceneName;
	}

	public void display() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HelpBox.fxml"));
		try {
			Parent root = (Parent) loader.load();
			HelpBoxView controller = loader.getController();
			controller.setUp(sceneName);

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
