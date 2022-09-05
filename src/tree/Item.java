package tree;

/**
 * Stores properties about an in-game item that can be used in the in-game tree.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class Item {
	private String name;
	private int cost;
	private String imagePath;
	private double heightImpact;
	private double healthImpact;
	private double waterImpact;
	private double nutrientImpact;
	private double chemicalImpact;

	/**
	 * Creates an Item object.
	 *
	 * @param name           A String containing the name of the item.
	 * @param cost           An Integer representing the cost of the item.
	 * @param imagePath      A String containing the path to an image that
	 *                       represents the item.
	 * @param heightImpact   A double representing how much height it can add to a
	 *                       tree when used on it.
	 * @param healthImpact   A double representing how much health it can add to a
	 *                       tree when used on it.
	 * @param waterImpact    A double representing how much water it can add to a
	 *                       tree when used on it.
	 * @param nutrientImpact A double representing how much nutrients it can add to
	 *                       a tree when used on it.
	 * @param chemicalImpact A double representing how much chemicals it can add to
	 *                       a tree when used on it.
	 */
	public Item(String name, int cost, String imagePath, double heightImpact, double healthImpact, double waterImpact, double nutrientImpact, double chemicalImpact) {
		this.name = name;
		this.cost = cost;
		this.imagePath = imagePath;
		this.heightImpact = heightImpact;
		this.healthImpact = healthImpact;
		this.waterImpact = waterImpact;
		this.nutrientImpact = nutrientImpact;
		this.chemicalImpact = chemicalImpact;
	}

	/**
	 * Returns true when the input object has the same name and cost as this item,
	 * and false otherwise.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null) return false;
		if (object.getClass() != getClass()) return false;
		Item other = (Item) object;
		return other.getName().equals(getName())
				&& (other.getCost() == getCost());
	}

	/**
	 * Returns the hash code of this item, dependent upon its name and cost.
	 */
	@Override
	public int hashCode() {
		int result = 0;
		int randomNumber = 13;
		result += name.hashCode() * randomNumber;
		result += cost * 2;
		return result;
	}

	/**
	 * Returns the name of the item.
	 *
	 * @return A String representing the name of the item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the cost of the item.
	 *
	 * @return An Integer representing the cost of the item.
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Returns the filepath to the image file representing the item.
	 *
	 * @return A String containing the filepath to the image file representing the
	 *         item.
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Returns the amount of height the tree increases when this item is used on it.
	 *
	 * @return An Integer representing how much the tree can increase in height.
	 */
	public double getHeightImpact() {
		return heightImpact;
	}

	/**
	 * Returns the amount of health the tree increases when this item is used on it.
	 *
	 * @return An Integer representing how much the tree can increase in health.
	 */
	public double getHealthImpact() {
		return healthImpact;
	}

	/**
	 * Returns the amount of water the tree increases when this item is used on it.
	 *
	 * @return An Integer representing how much the tree can increase in water.
	 */
	public double getWaterImpact() {
		return waterImpact;
	}

	/**
	 * Returns the amount of nutrients the tree increases when this item is used on
	 * it.
	 *
	 * @return An Integer representing how much the tree can increase in nutrients.
	 */
	public double getNutrientImpact() {
		return nutrientImpact;
	}

	/**
	 * Returns the amount of chemicals the tree increases when this item is used on
	 * it.
	 *
	 * @return An Integer representing how much the tree can increase in chemicals.
	 */
	public double getChemicalImpact() {
		return chemicalImpact;
	}

	/**
	 * Sets the name of the item.
	 *
	 * @param name A String containing the name of the item.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the cost of the item.
	 *
	 * @param cost A Integer representing the cost of the item.
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Sets the filepath to the image representing the item.
	 *
	 * @param imagePath A String containing the filepath to the image file
	 *                  representing the item.
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Sets the amount of height the tree increases when this item is used on it.
	 *
	 * @param heightImpact A double representing how much to increase the height of
	 *                     the tree by when this item is used on it.
	 */
	public void setHeightImpact(double heightImpact) {
		this.heightImpact = heightImpact;
	}

	/**
	 * Sets the amount of health the tree increases when this item is used on it.
	 *
	 * @param healthImpact A double representing how much to increase the health of
	 *                     the tree by when this item is used on it.
	 */
	public void setHealthImpact(double healthImpact) {
		this.healthImpact = healthImpact;
	}

	/**
	 * Sets the amount of water the tree increases when this item is used on it.
	 *
	 * @param waterImpact A double representing how much to increase the water of
	 *                    the tree by when this item is used on it.
	 */
	public void setWaterImpact(double waterImpact) {
		this.waterImpact = waterImpact;
	}

	/**
	 * Sets the amount of nutrients the tree increases when this item is used on it.
	 *
	 * @param nutrientImpact A double representing how much to increase the
	 *                       nutrients of the tree by when this item is used on it.
	 */
	public void setNutrientImpact(double nutrientImpact) {
		this.nutrientImpact = nutrientImpact;
	}

	/**
	 * Sets the amount of chemicals the tree increases when this item is used on it.
	 *
	 * @param chemmicalImpact A double representing how much to increase the amount
	 *                        of chemicals of the tree by when this item is used on
	 *                        it.
	 */
	public void setChemicalImpact(double chemicalImpact) {
		this.chemicalImpact = chemicalImpact;
	}
}
