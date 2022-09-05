package application;

import controller.AlertBoxView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Creates Alert Boxes.
 * @author Juwon Jung
 *
 */
public class AlertBox {

	String header;
	String description;
	
	/**
	 * Initialise the alertBox.
	 * @param header
	 * @param description
	 */
	public AlertBox(String header, String description) {
		this.header = header;
		this.description = description;
	}

	/**
	 * Load alertBox. Show stage and wait until yes or no is selected.
	 * @return close the stage
	 */
	public boolean displayAndGetResult() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlertBox.fxml"));
		try {
			Parent root = (Parent) loader.load();
			AlertBoxView controller = loader.getController();
			controller.setUp(header, description);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);

			stage.showAndWait();
			boolean result = controller.getResult();
			return result;


		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
