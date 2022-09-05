package fileio;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tree.Tree;

/**
 * Reads from and writes to the file about the user's tree.
 *
 * @author Jared Daniel Recomendable
 *
 */
public class TreeStatisticsIO extends FileIO {

	/**
	 * Creates a TreeStatisticsIO object with the filepath to the file to modify.
	 *
	 * @param filepath A String object containing the filepath to the file that the
	 *                 object deals with.
	 */
	public TreeStatisticsIO(String filepath) {
		super(filepath);
	}

	/**
	 * Save information about the user's tree.
	 *
	 * @param dateTime An OffsetDateTime object that contains information on the
	 *                 date and time on when the tree was saved.
	 * @param tree     The Tree object to save to file.
	 */
	public void saveTree(OffsetDateTime dateTime, Tree tree) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) + " ");
		stringBuilder.append(String.valueOf(tree.getHeight()) + " ");
		stringBuilder.append(String.valueOf(tree.getHealth()) + " ");
		stringBuilder.append(String.valueOf(tree.getWater()) + " ");
		stringBuilder.append(String.valueOf(tree.getNutrient()) + " ");
		stringBuilder.append(String.valueOf(tree.getChemical()));
		ArrayList<String> lines = new ArrayList<>();
		lines.add(stringBuilder.toString());
		overwriteLines(lines);
	}

	/**
	 * Retrieve the last saved date and time of the tree in file.
	 *
	 * @return An OffsetDateTime object containing information about the date and
	 *         time on when the tree was last saved.
	 */
	public OffsetDateTime getTreeLastSavedDateTime() {
		OffsetDateTime dateTime;
		ArrayList<String> lines = readLines();
		String treeRecord = lines.get(0);
		String dateTimeString = getStringPartFromLine(treeRecord, 0);
		dateTime = OffsetDateTime.parse(dateTimeString);
		return dateTime;
	}

	/**
	 * Retrieve the tree saved in file.
	 *
	 * @return A Tree object from the file and to be used in the application.
	 */
	public Tree loadTree() {
		Tree tree = new Tree();
		tree.setHeight(getTreeStatistic(1));
		tree.setHealth(getTreeStatistic(2));
		tree.setWater(getTreeStatistic(3));
		tree.setNutrient(getTreeStatistic(4));
		tree.setChemical(getTreeStatistic(5));
		return tree;
	}

	/**
	 * Get statistics about the tree, based on its position in the space-separated
	 * iine about the tree.
	 *
	 * @param index An integer representing the position of the statistics to
	 *              retrieve from the space-separated file.
	 * @return A double representing information about the particular statistic in
	 *         question.
	 */
	private double getTreeStatistic(int index) {
		double statistic;
		ArrayList<String> lines = readLines();
		String treeRecord = lines.get(0);
		String treeStatisticString = getStringPartFromLine(treeRecord, index);
		statistic = Double.valueOf(treeStatisticString);
		return statistic;
	}
}
