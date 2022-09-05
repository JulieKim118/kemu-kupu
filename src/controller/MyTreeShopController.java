package controller;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import application.Cash;
import application.FileSaveLocations;
import application.HelpBox;
import fileio.CashIO;
import fileio.ItemStockIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tree.Item;
import tree.ItemStock;

/**
 * This is the controller class for My Tree Shop.
 * Items can be bought from the shop using the cash earned from the game quiz.
 * @author Juwon Jung
 * @author Jared Daniel Recomendable 
 *
 */
public class MyTreeShopController extends Controller {

	@FXML
    private Label moneyLabel;
    @FXML
    private Button btnBuyWater;
    @FXML
    private Button btnFertiliserPlus;
    @FXML
    private Button btnBuyExWater;
    @FXML
    private Button btnFertiliser;
    @FXML
    private Button BtnSunlight;
    @FXML
    private Button btnInsecticide;
    @FXML
    private Button btnLeft;
    @FXML
    private Button btnRight;
	@FXML
    private Button back;
	@FXML
	private Button helpButton;

	private OffsetDateTime offSetDateTime;
	private ItemStockIO itemStockIO;
	private CashIO cashIO;
	private Cash treeMoney;
	private ItemStock stock;
	private Item itemWater;
	private Item itemWaterEx;
	private Item itemFertiliser;
	private Item itemFertiliserPlus;
	private Item itemSunlight;
	private Item itemInsecticide;

	/**
	 * Returns to the my tree screen
	 * @param event ActionEvent when back button is pressed
	 */
    @FXML
    void quitShop(ActionEvent event) {
		switchScene(event, "MyTree.fxml");
    }
    
    /**
     * Buy item water.
     * @param event ActionEvent when buy button for water is pressed 
     */
    @FXML
    void buyWater(ActionEvent event) {
    	buyItem(itemWater);
    }

    /**
     * Buy item water extra.
     * @param event ActionEvent when buy button for water extra is pressed 
     */
    @FXML
    void buyWaterEx(ActionEvent event) {
    	buyItem(itemWaterEx);
    }

    /**
     * Buy item fertiliser.
     * @param event ActionEvent when buy button for fertiliser is pressed 
     */
    @FXML
    void buyFertiliser(ActionEvent event) {
    	buyItem(itemFertiliser);
    }

    /**
     * Buy item fertiliser.
     * @param event ActionEvent when buy button for fertiliser plus is pressed 
     */
    @FXML
    void buyFertiliserPlus(ActionEvent event) {
    	buyItem(itemFertiliserPlus);
    }

    /**
     * Buy item insecticide.
     * @param event ActionEvent when buy button for insecticide is pressed 
     */
    @FXML
    void buyInsecticide(ActionEvent event) {
    	buyItem(itemInsecticide);
    }

    /**
     * Buy item sunlight.
     * @param event ActionEvent when buy button for sunlight is pressed 
     */
    @FXML
    void buySunlight(ActionEvent event) {
    	buyItem(itemSunlight);
    }

    /**
     * Buy items if there is enough funds. Withdraw the cost of the item bought.
     * Store the changed total of cash and update cash label. Add the item to stock and 
     * store item quantity.
     * @param item
     */
    public void buyItem(Item item) {
    	if (treeMoney.hasEnoughFunds(item.getCost())) {
    		treeMoney.withdraw(item.getCost());
        	stock.addItem(item);
        	saveItemStockAndMoney();
        	updateCashLabel();
    	}
    }

    /**
     * Store item stock and cash to files inventory and cash.
     */
    private void saveItemStockAndMoney() {
    	CashIO cashIO = new CashIO(FileSaveLocations.CASH);
    	ItemStockIO itemStockIO = new ItemStockIO(FileSaveLocations.INVENTORY);
    	cashIO.saveCash(treeMoney);
    	itemStockIO.saveStockNumbers(stock, itemStockIO.getDateTimeSaved());
    }

    /**
     * Update the cash label
     */
    private void updateCashLabel() {
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);
    }

    /**
     * Initialise the cash stored in cash file and the item stock.
     */
    public void initialize() {
    	btnRight.setVisible(false);
    	btnLeft.setVisible(false);
    	cashIO = new CashIO(FileSaveLocations.CASH);
    	treeMoney = cashIO.loadCash();
    	String money = String.valueOf(treeMoney.getCash());
    	moneyLabel.setText(money);
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
	 * Opens the help window for the My Tree Shop screen
	 * @param event ActionEvent from the help icon button
	 */
	public void openHelpWindow(ActionEvent event) {
		String sceneName = "ItemShop";
		HelpBox helpBox = new HelpBox(sceneName);
		helpBox.display();
	}
}
