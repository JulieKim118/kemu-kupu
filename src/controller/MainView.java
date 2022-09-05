package controller;

import java.time.OffsetDateTime;

import application.AlertBox;
import application.FileSaveLocations;
import application.HelpBox;
import fileio.CashIO;
import fileio.HighestEarningsIO;
import fileio.ItemStockIO;
import fileio.StatisticsIO;
import fileio.TreeStatisticsIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tree.ItemStock;
import tree.Tree;

/**
 * This is a controller class for Main Menu.
 *
 * @author Julie Kim
 *
 */
public class MainView extends Controller {

	@FXML
	private Button gameButton;
	@FXML
	private Button quitButton;
	@FXML
	private Button practiceButton;
	@FXML
	private Button statisticsButton;
	@FXML
	private Button myTreeButton;
	@FXML
	private Button resetBinButton;
	@FXML
	private Button helpButton;

	private OffsetDateTime offSetDateTime;
	private CashIO cashIO;
	private ItemStockIO itemStockIO;
	private TreeStatisticsIO treeStatisticsIO;
	private Tree tree;
	private ItemStock itemStock;
	private StatisticsIO statisticsIO;
	private HighestEarningsIO highestEarningsIO;

	/**
	 * Starts game module by switching scene to the topic list scene.
	 *
	 * @param play ActionEvent from play game button
	 */
	public void playGame(ActionEvent play) {
		switchScene(play, "TopicList.fxml");
	}

	/**
	 * Starts practice module by switching scene to the practice topics scene. 
	 * @param play ActionEvent from practice button
	 */
	public void playPractice(ActionEvent play) {
		switchScene(play, "PracticeTopic.fxml");
	}

	/**
	 * Switches screen to the My Tree view
	 * @param event ActionEvent from the tree icon button
	 */
	public void viewMyTree(ActionEvent event) {
		switchScene(event, "MyTree.fxml");
	}

	/**
	 * Switches screen to the statistics My Vocabulary view
	 * @param event ActionEvent from the statistics icon button
	 */
	public void viewStatistics(ActionEvent event) {
		switchScene(event, "MyVocabulary.fxml");
	}

	/**
	 * Resets all user data stored in files including my tree, cash balance and academic statistics history
	 * @param event ActionEvent from the rubbish bin icon button
	 */
	public void resetAll(ActionEvent event) {
		String header = "Are you sure you want to reset?";
		String description = "You will lose all your data.";
		AlertBox alertBox = new AlertBox(header, description);
		boolean result = alertBox.displayAndGetResult();
		if (result) {
			offSetDateTime = OffsetDateTime.now();
			cashIO = new CashIO(FileSaveLocations.CASH);
			itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
			treeStatisticsIO = new TreeStatisticsIO(FileSaveLocations.TREE_STATISTICS);
			statisticsIO = new StatisticsIO(FileSaveLocations.STATISTICS);
			highestEarningsIO = new HighestEarningsIO(FileSaveLocations.QUIZ_EARNINGS);
			cashIO.resetCash();
			tree = new Tree();
			treeStatisticsIO.saveTree(offSetDateTime, tree);
			itemStock = new ItemStock();
			itemStockIO.saveStockNumbers(itemStock, offSetDateTime);
			statisticsIO.resetStatistics();
			highestEarningsIO.resetHighestEarnings();
		}
	}

	/**
	 * Opens the help window for main menu
	 * @param event ActionEvent from the help icon button
	 */
	public void openHelpWindow(ActionEvent event) {
		String sceneName = "MainMenu";
		HelpBox helpBox = new HelpBox(sceneName);
		helpBox.display();
	}

	/**
	 * Exits and shuts down window when the quit button is pressed.
	 *
	 * @param quit ActionEvent from quit button
	 */
	public void quitGame(ActionEvent quit) {
		System.exit(0);
	}

}
