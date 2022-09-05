package controller;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import application.AlertBox;
import application.Cash;
import application.FileSaveLocations;
import application.HelpBox;
import enums.TreeLevel;
import enums.TreeStatus;
import fileio.CashIO;
import fileio.ItemStockIO;
import fileio.TreeStatisticsIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tree.Item;
import tree.ItemStock;
import tree.Tree;

/**
 * This is the controller class for My Tree.
 * Items bought from the shop can be used. The items will impact on the status, 
 * height or health of the tree. 
 * @author Juwon Jung
 * @author Jared Daniel Recomendable 
 *
 */
public class MyTreeController extends Controller {

	@FXML
	private Button back;
	@FXML
	private Button helpButton;
	@FXML
	private Label moneyLabel;
	@FXML
	private ImageView treeImage;
	@FXML
	private Label statusLabel;
	@FXML
	private Label heightLabel;
	@FXML
	private Label healthLabel;
	@FXML
    private Label itemLabelWater;
    @FXML
    private Button btnUseWater;
    @FXML
    private Label itemLabelWaterEx;
    @FXML
    private Button btnUseWaterEx;
    @FXML
    private Label itemLabelFertiliser;
    @FXML
    private Button btnUseFertiliser;
    @FXML
    private Label itemLabelFertiliserPlus;
    @FXML
    private Button btnUseFertiliserPlus;
    @FXML
    private Label itemLabelSunlight;
    @FXML
    private Button btnUseSunlight;
    @FXML
    private Label itemLabelInsecticide;
    @FXML
    private Button btnUseInsecticide;
    @FXML
    private Button btnAxe;
	@FXML
	private Button btnBuyItems;

	private Tree tree;
	private OffsetDateTime offSetDateTime;
	private Cash treeMoney;;
	private CashIO cashIO;
	private ItemStockIO itemStockIO;
	private ItemStock stock;
	private TreeStatisticsIO treeStatisticsIO;
	
	private Item itemWater;
	private Item itemWaterEx;
	private Item itemFertiliser;
	private Item itemFertiliserPlus;
	private Item itemSunlight;
	private Item itemInsecticide;
	
	private Map<String, Integer> stockNumbers;
	private HashMap<String, Label> useButtons;

	Image sproutImage = new Image(getClass().getResourceAsStream("/resources/Sprout_icon.png"));
	Image saplingImage = new Image(getClass().getResourceAsStream("/resources/Tree_1.png"));
	Image youngImage = new Image(getClass().getResourceAsStream("/resources/Tree_2.png"));
	Image grownImage = new Image(getClass().getResourceAsStream("/resources/Tree_3.png"));
	Image matureImage = new Image(getClass().getResourceAsStream("/resources/Tree_4.png"));
	Image bloomingImage = new Image(getClass().getResourceAsStream("/resources/Tree_final.png"));
	Image sproutImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Sprout_icon.png"));
	Image saplingImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_1.png"));
	Image youngImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_2.png"));
	Image grownImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_3.png"));
	Image matureImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_4.png"));
	Image bloomingImage2 = new Image(getClass().getResourceAsStream("/resources/unwell_Tree_final.png"));
	Image sproutImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Sprout.png"));
	Image saplingImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_1.png"));
	Image youngImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_2.png"));
	Image grownImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_3.png"));
	Image matureImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_4.png"));
	Image bloomingImage3 = new Image(getClass().getResourceAsStream("/resources/Dying_Tree_final.png"));
	Image deadSign = new Image(getClass().getResourceAsStream("/resources/Dead_sign.png"));
	private String sprout = "SPROUT";
	private String sapling = "SAPLING";
	private String young = "YOUNG";
	private String grown = "GROWN";
	private String mature = "MATURE";
	private String blooming = "BLOOMING";

	/**
	 * Returns to main menu when back button is pressed.
	 * @param event ActionEvent when back button is pressed
	 */
	@FXML
	void quitMyTree(ActionEvent event) {
		backToMain(event);
	}

	/**
	 * Navigates to shop screen, where items can be purchased.
	 * @param event ActionEvent when buy items button is pressed
	 */
	@FXML
    void buyItems(ActionEvent event) {
		switchScene(event, "MyTreeShop.fxml");
    }

	/**
	 * Alert Box appears when cut down tree (axe) button is pressed. If yes is selected
	 * the tree data is reset. 
	 * @param event ActionEvent when cut down tree button is pressed
	 */
    @FXML
    void cutDownTree(ActionEvent event) {
    	String header = "Are you sure you want to cut down \nyour tree?";
		String description = "Your tree will die.";
		AlertBox alertBox = new AlertBox(header, description);
		boolean result = alertBox.displayAndGetResult();
		if (result) {
	    	tree.kill();
	    	StatusHeightHealth();
	    	treeStatisticsIO.saveTree(offSetDateTime, tree);
		}
    }

    /**
     * Use item water.
     * @param event ActionEvent when use button for water is pressed
     */
    @FXML
    void useWater(ActionEvent event) {
    	useItem(itemWater);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    /**
     * Use item water extra.
     * @param event ActionEvent when use button for water extra is pressed
     */
    @FXML
    void useWaterEx(ActionEvent event) {
    	useItem(itemWaterEx);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    /**
     * Use item fertiliser.
     * @param event ActionEvent when use button for fertiliser is pressed
     */
    @FXML
    void useFertiliser(ActionEvent event) {
    	useItem(itemFertiliser);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    /**
     * TUse item fertiliser plus.
     * @param event ActionEvent when use button for fertiliser plus is pressed
     */
    @FXML
    void useFertiliserPlus(ActionEvent event) {
    	useItem(itemFertiliserPlus);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    /**
     * Use item sunlight.
     * @param event ActionEvent when use button for sunlight is pressed
     */
    @FXML
    void useSunlight(ActionEvent event) {
    	useItem(itemSunlight);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }

    /**
     * Use item insecticide.
     * @param event ActionEvent when use button for insecticide is pressed
     */
    @FXML
    void useInsecticide(ActionEvent event) {
    	useItem(itemInsecticide);    	
    	stockNumbers = itemStockIO.loadStockNumbers();
    	itemInventory(stockNumbers, useButtons);
    }
    
    /**
     * To use item, check if item is in stock. Get the health and height impact of the 
     * item, and update the health and height of the tree. Remove the item from stock
     * after use and save item stock. Update the status, height and health labels
     * @param item
     */
    public void useItem(Item item) {
    	if (stock.isInStock(item)) {
    		double healthImpact = item.getHealthImpact();
    		double health = tree.getHealth()+healthImpact;
    		if (health >= 6) {
    			tree.setHealth(6);
    		} else {
        		tree.setHealth(health);
    		}
    		double heightImpact = item.getHeightImpact();
    		double height = tree.getHeight()+heightImpact;
    		tree.setHeight(height);
    	}
    	stock.removeItem(item);
    	StatusHeightHealth();
    	saveItemStockAndMoney();
    }
    
    /**
     * Store total cash, save stock numbers and save tree statistics.
     */
    private void saveItemStockAndMoney() {
    	cashIO.saveCash(treeMoney);
    	itemStockIO.saveStockNumbers(stock, OffsetDateTime.now());
    	treeStatisticsIO.saveTree(offSetDateTime, tree);
    }

    /**
     * Set the status, height and health of the tree. Display image of tree. 
     * If the tree health is 0, display the dead image and disable all use item buttons.
     */
    private void StatusHeightHealth() {
    	if (tree.getHealth() == 0) {
    		disableUseButton(true);
    		treeImage.setImage(deadSign);
    		setStatusHeightHealth();
    	} else {
    		disableUseButton(false);
    		treeImage.setVisible(true);
    		String level = setStatusHeightHealth();
    		treeImage.setImage(displayImage(level));
    	}
    }
    
    /**
     * Disable all the use buttons.
     * @param disable Boolean if health equals zero
     */
    private void disableUseButton(boolean disable) {
    	btnUseWater.setDisable(disable);
		btnUseWaterEx.setDisable(disable);
		btnUseFertiliser.setDisable(disable);
		btnUseFertiliserPlus.setDisable(disable);
		btnUseSunlight.setDisable(disable);
		btnUseInsecticide.setDisable(disable);
    }
    
    /**
     * Set the status, height and health labels
     * @return String level
     */
    public String setStatusHeightHealth() {
    	TreeStatus treestatus = tree.getHealthStatus();
    	String health = treestatus.name();
    	healthLabel.setText(health);
    	String height = String.valueOf(tree.getHeight());
    	heightLabel.setText(height+"cm");
    	TreeLevel treeLevel = tree.getTreeLevel();
    	String level = treeLevel.name();
    	statusLabel.setText(level);
    	return level;
    }
    
    /**
     * Display the image according to the correct tree status and health level.
     * @param level String of tree status
     * @return Image of tree
     */
    public Image displayImage(String level) {
		if (level.equals(sprout)) {
			if (tree.getHealth() >= 5) {
				return sproutImage;
			} else if (tree.getHealth() >= 3) {
				return sproutImage2;
			} else {
				return sproutImage3;
			}	
		} else if (level.equals(sapling)) {
			if (tree.getHealth() >= 5) {
				return saplingImage;
			} else if (tree.getHealth() >= 3) {
				return saplingImage2;
			} else {
				return saplingImage3;
			}
		} else if (level.equals(young)) {
			if (tree.getHealth() >= 5) {
				return youngImage;
			} else if (tree.getHealth() >= 3) {
				return youngImage2;
			} else {
				return youngImage3;
			}
		} else if (level.equals(grown)) {
			if (tree.getHealth() >= 5) {
				return grownImage;
			} else if (tree.getHealth() >= 3) {
				return grownImage2;
			} else {
				return grownImage3;
			}
		} else if (level.equals(mature)) {
			if (tree.getHealth() >= 5) {
				return matureImage;
			} else if (tree.getHealth() >= 3) {
				return matureImage2;
			} else {
				return matureImage3;
			}
		} else {
			if (tree.getHealth() >= 5) {
				return bloomingImage;
			} else if (tree.getHealth() >= 3) {
				return bloomingImage2;
			} else {
				return bloomingImage3;
			}
		}
	}

    /**
     * Display the item stock in my items
     * @param stockNumbers Map with key: String of item, value: stock of each item
     * @param useButtons Map with key: String of item, value: item label
     */
    private void itemInventory(Map<String, Integer> stockNumbers, HashMap<String, Label> useButtons) {
    	for (Map.Entry<String, Integer> entry : stockNumbers.entrySet()) {
    	    for (HashMap.Entry<String, Label> entry1 : useButtons.entrySet()) {
    	    	if (entry1.getKey().equals(entry.getKey())) {
    	    		entry1.getValue().setText("x"+entry.getValue().toString());
    	    	}
    	    }
    	}
    }

    /**
     * Each use button label is assigned to a string using a HashMap
     * @param useButtons Map with key: String of item, value: item label
     */
    private void itemLabelHashMap(HashMap<String, Label> useButtons) {
    	useButtons.put("water", itemLabelWater);
    	useButtons.put("waterEx", itemLabelWaterEx);
    	useButtons.put("fertiliser", itemLabelFertiliser);
    	useButtons.put("fertiliserPlus", itemLabelFertiliserPlus);
    	useButtons.put("sunlight", itemLabelSunlight);
    	useButtons.put("insecticide", itemLabelInsecticide);
    }

    /** 
     * Initialise item labels to zero
     */
    private void initialiseItemLabels() {
    	itemLabelWater.setText("x"+0);
    	itemLabelWaterEx.setText("x"+0);
    	itemLabelFertiliser.setText("x"+0);
    	itemLabelFertiliserPlus.setText("x"+0);
    	itemLabelSunlight.setText("x"+0);
    	itemLabelInsecticide.setText("x"+0);
    }

    /**
     * Get duration of last time an item has been used
     * @param itemStockIO stock of item stored
     */
    private void getDuration(ItemStockIO itemStockIO) {
    	offSetDateTime = OffsetDateTime.now();
    	OffsetDateTime date = itemStockIO.getDateTimeSaved();
      	Duration duration = Duration.between(date, offSetDateTime);
    	long durationDays = duration.toDays();
        decreaseHealth(durationDays);
    }

    /**
     * Health of tree decreases in for each day. Update health label and display tree
     * image.
     * @param days duration of number of days
     */
    private void decreaseHealthDays(int days) {
    	double treeHealth = tree.getHealth();
		if (treeHealth-days <= 0) {
			tree.setHealth(0);
		} else {
    		tree.setHealth(treeHealth-days);
		}
		TreeStatus treestatus = tree.getHealthStatus();
		String name = treestatus.name();
		healthLabel.setText(name);
    }

    /**
     * Set decrease of health depending on the duration of days from last use of item
     * @param durationDays Time duration in days 
     */
    private void decreaseHealth(long durationDays) {
    	if (durationDays >= 6) {
    		decreaseHealthDays(6);
    	} else if (durationDays >= 5) {
    		decreaseHealthDays(5);
    	} else if (durationDays >= 4) {
    		decreaseHealthDays(4);
    	} else if (durationDays >= 3) {
    		decreaseHealthDays(3);
    	} else if (durationDays >= 2) {
    		decreaseHealthDays(2);
    	} else if (durationDays >= 1) {
    		decreaseHealthDays(1);
    	}
    }

    /**
     * Initliase the status, height and health of tree from the data stored in files.
     * Set up cash saved in file and initialise items and stock.
     */
    public void initialize() {
    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	treeStatisticsIO = new TreeStatisticsIO(FileSaveLocations.TREE_STATISTICS);
    	tree = treeStatisticsIO.loadTree();
    	getDuration(itemStockIO);

    	cashIO = new CashIO(FileSaveLocations.CASH);
    	treeMoney = cashIO.loadCash();
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);

    	StatusHeightHealth();
    	
    	stockNumbers = new LinkedHashMap<>();
    	stockNumbers = itemStockIO.loadStockNumbers();
    	useButtons = new HashMap<>();
    	itemLabelHashMap(useButtons);
    	initialiseItemLabels();
    	itemInventory(stockNumbers, useButtons);
    	
    	initializeItems();
    	initializeStock();
    }
    
    /**
     * Initialise each item with the name, cost, height impact and health impact.
     */
    private void initializeItems() {
    	itemWater          = new Item("water",             200, "", 100,   0, 0, 0, 0);
    	itemWaterEx        = new Item("waterEx",           300, "", 150,   0, 0, 0, 0);
    	itemFertiliser     = new Item("fertiliser",        400, "",  50,   1, 0, 0, 0);
    	itemFertiliserPlus = new Item("fertiliserPlus",    500, "", 100,   2, 0, 0, 0);
    	itemSunlight       = new Item("sunlight",          200, "",   0,   2, 0, 0, 0);
    	itemInsecticide    = new Item("insecticide",       600, "", 100,   4, 0, 0, 0);
    }
    
    /**
     * Initialise stock stored in inventory file
     */
    private void initializeStock() {
    	itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	stock = new ItemStock();
    	Map<String, Integer> stockNumbers = itemStockIO.loadStockNumbers();
    	for (String itemName : stockNumbers.keySet()) {
    		loadStock(stock, stockNumbers, itemName);
    	}
    }

    /**
     * Load stock of item stored in inventory file
     * @param stock Stock of item
     * @param stockNumbers quantity of item stored
     * @param itemName Name of item
     */
    private void loadStock(ItemStock stock, Map<String, Integer> stockNumbers, String itemName) {
    	int stockNumber = stockNumbers.get(itemName);
    	Item item = determineItem(itemName);
    	stock.addItem(item, stockNumber);
    }

    /**
     * Determine the name of item
     * @param itemName String Name of item
     * @return Name of item
     */
    private Item determineItem(String itemName) {
    	switch(itemName) {
    	case "water":
    		return itemWater;
    	case "waterEx":
    		return itemWaterEx;
    	case "fertiliser":
    		return itemFertiliser;
    	case "fertiliserPlus":
    		return itemFertiliserPlus;
    	case "sunlight":
    		return itemSunlight;
    	case "insecticide":
    		return itemInsecticide;
    	default:
    		return null;
    	}
    }
    
	/**
	 * Opens the help window for the My Tree screen
	 * @param event ActionEvent from the help icon button
	 */
	public void openHelpWindow(ActionEvent event) {
		String sceneName = "MyTree";
		HelpBox helpBox = new HelpBox(sceneName);
		helpBox.display();
	}

}
