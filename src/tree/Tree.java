package tree;

import enums.TreeLevel;
import enums.TreeStatus;

/**
 * Stores properties about the in-game tree.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class Tree {
	private double height;
	private double health;

	private double water;
	private double nutrient;
	private double chemical;

	private double waterThreshold;
	private double nutrientThreshold;
	private double chemicalThreshold;

	/**
	 * Creates a tree object, with the initial default properties.
	 */
	public Tree() {
		kill();
	}

	/**
	 * Resets the tree to default properties.
	 */
	public void kill() {
		startAfresh();
		updateThresholds();
	}

	/**
	 * Defines the default properties of the tree during reset.
	 */
	private void startAfresh() {
		height = 1;
		health = 6;
		water = 0;
		nutrient = 0;
		chemical = 0;
	}

	/**
	 * Calculates the thresholds that the tree can contain. Exceeding these
	 * thresholds is intended to put the tree in an unhealthy state, but this action
	 * has to be explicitly carried out by the controller class.
	 */
	private void updateThresholds() {
		int level = getLevel();
		waterThreshold = calculateNewThreshold(100, 20);
		nutrientThreshold = calculateNewThreshold(50, 30);
		chemicalThreshold = calculateNewThreshold(30, 10);
	}

	/**
	 * Get an internally-used level based on its height.
	 *
	 * @return
	 */
	public int getLevel() {
		return (int) Math.ceil(height);
	}

	/**
	 * Calculates the new threshold value for a particular tree property, based on
	 * its starting value and how much increase it is intended to have per level.
	 *
	 * @param startingValue    A double representing the starting value of the tree
	 *                         property.
	 * @param increasePerLevel A double representing how much the increase in
	 *                         threshold value should be per level.
	 * @return
	 */
	private double calculateNewThreshold(double startingValue, double increasePerLevel) {
		int level = getLevel();
		double totalIncreaseInThreshold = (level - 1) * increasePerLevel;
		return startingValue + totalIncreaseInThreshold;
	}

	/**
	 * Returns the height property of the tree.
	 *
	 * @return A double representing the height of the tree.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Returns the tree level of the tree, based on its height.
	 *
	 * @return A TreeLevel enumeration representing the tree level.
	 */
	public TreeLevel getTreeLevel() {
		if (height < 240) {
			return TreeLevel.SPROUT;
		} else if (height < 640) {
			return TreeLevel.SAPLING;
		} else if (height < 1200) {
			return TreeLevel.YOUNG;
		} else if (height < 2600) {
			return TreeLevel.GROWN;
		} else if (height < 4000) {
			return TreeLevel.MATURE;
		} else {
			return TreeLevel.BLOOMING;
		}
	}

	/**
	 * Returns the health property of the tree.
	 *
	 * @return A double representing the health of the tree.
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * Returns the tree status of the tree, based on its health.
	 *
	 * @return A TreeStatus enumeration representing the tree status.
	 */
	public TreeStatus getHealthStatus() {
		if (health == 6) {
			return TreeStatus.EXCELLENT;
		} else if (health == 5) {
			return TreeStatus.VERYGOOD;
		} else if (health == 4) {
			return TreeStatus.AVERAGE;
		} else if (health == 3) {
			return TreeStatus.CONCERNING;
		} else if (health == 2) {
			return TreeStatus.POOR;
		} else if (health == 1) {
			return TreeStatus.DYING;
		} else {
			return TreeStatus.DEAD;
		}
	}

	/**
	 * Returns how much water the tree has.
	 *
	 * @return A double representing the amount of water the tree has.
	 */
	public double getWater() {
		return water;
	}

	/**
	 * Returns how much nutrients the tree has.
	 *
	 * @return A double representing the amount of nutrients the tree has.
	 */
	public double getNutrient() {
		return nutrient;
	}

	/**
	 * Returns how much chemicals the tree has.
	 *
	 * @return A double representing the amount of chemicals the tree has.
	 */
	public double getChemical() {
		return chemical;
	}

	/**
	 * Returns how much water the tree can contain before it is deemed unhealthy.
	 *
	 * @return A double representing the water threshold value of the tree.
	 */
	public double getWaterThreshold() {
		updateThresholds();
		return waterThreshold;
	}

	/**
	 * Returns how much nutrients the tree can contain before it is deemed
	 * unhealthy.
	 *
	 * @return A double representing the nutrients threshold value of the tree.
	 */
	public double getNutrientThreshold() {
		updateThresholds();
		return nutrientThreshold;
	}

	/**
	 * Returns how much chemicals the tree can contain before it is deemed
	 * unhealthy.
	 *
	 * @return A double representing the chemical threshold value of the tree.
	 */
	public double getChemicalThreshold() {
		updateThresholds();
		return chemicalThreshold;
	}

	/**
	 * Sets the height property of the tree.
	 *
	 * @param height A double representing the intended new height of the tree.
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Sets the health property of the tree.
	 *
	 * @param health A double representing the intended new health of the tree.
	 */
	public void setHealth(double health) {
		this.health = health;
	}

	/**
	 * Sets the amount of water the tree has.
	 *
	 * @param water A double representing the intended new amount of water the tree
	 *              has.
	 */
	public void setWater(double water) {
		this.water = water;
	}

	/**
	 * Sets the amount of nutrients the tree has.
	 *
	 * @param nutrient A double representing the intended new amount of nutrients
	 *                 the tree has.
	 */
	public void setNutrient(double nutrient) {
		this.nutrient = nutrient;
	}

	/**
	 * Sets the amount of chemicals the tree has.
	 *
	 * @param chemical A double representing the intended new amount of chemicals
	 *                 the tree has.
	 */
	public void setChemical(double chemical) {
		this.chemical = chemical;
	}

	/**
	 * Increases the height of the tree based on the input height specified.
	 *
	 * @param height A double representing how much to increase the tree's height
	 *               by.
	 */
	public void changeHeight(double height) {
		this.height += height;
	}

	/**
	 * Increases the health of the tree based on the input health specified.
	 *
	 * @param health A double representing how much to increase the tree's health
	 *               by.
	 */
	public void changeHealth(double health) {
		this.health += health;
	}

	/**
	 * Increases the amount of water the tree has.
	 *
	 * @param water A double representing how much to water to give to the tree.
	 */
	public void changeWater(double water) {
		this.water += water;
	}

	/**
	 * Increases the amount of nutrients the tree has.
	 *
	 * @param nutrient A double representing how much to nutrients to give to the
	 *                 tree.
	 */
	public void changeNutrient(double nutrient) {
		this.nutrient += nutrient;
	}

	/**
	 * Increases the amount of chemicals the tree has.
	 *
	 * @param chemical A double representing how much to chemicals to give to the
	 *                 tree.
	 */
	public void changeChemical(double chemical) {
		this.chemical += chemical;
	}
}
