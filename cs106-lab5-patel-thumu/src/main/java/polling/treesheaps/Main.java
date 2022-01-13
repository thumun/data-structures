/**
* Lab 5 for CS106 
*
* This lab creates and implements a Linked Binary Tree. This is a binary tree where the 
* left and right children are trees. 
* The lab uses the Linked Binary Tree to organize polling data by storing information about 
* candidates and their polling numbers. 
* 
*
* @author: Avi Patel and Neha Thumu 
* @version: April 19, 2021
*/

package polling.treesheaps;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;

public class Main {

	public static void main(String[] args) {

		try {

			BinaryTree<PollingData> pollingEntries = new LinkedBinaryTree<PollingData>();

			// going through command line input to read each file
			for (int i = 0; i < args.length; i++) {
				CSVReaderHeaderAware reader = new CSVReaderHeaderAware(
						new FileReader(args[i]));
				// contents of file added to this ArrayList; overwritten each
				// time it loops
				ArrayList<String[]> dataEntries = new ArrayList<String[]>(
						reader.readAll());
				reader.close();

				// a Linked Binary Tree is created/edited based on the ArrayList
				for (int j = 0; j < dataEntries.size(); j++) {
					PollingData entry = new PollingData(dataEntries.get(j));
					pollingEntries.insert(entry);
					// System.out.println(entry);
				}
				System.out.println();
				// snapshot of tree
				System.out.println(pollingEntries);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		linkedBinaryTreeTests();

	}

	/**
	 * tests for the LinkedBinaryTree class
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public static void linkedBinaryTreeTests() {
		System.out.println();
		BinaryTree<Character> letterTree = new LinkedBinaryTree<Character>();

		System.out.println(
				"isEmpty(): " + String.valueOf(letterTree.isEmpty() == true));
		System.out.println("Initial size of tree: " + letterTree.size());
		letterTree.insert('A');
		letterTree.insert('C');
		letterTree.insert('G');
		letterTree.insert('B');
		letterTree.insert('D');
		letterTree.insert('G');
		letterTree.insert('F');
		letterTree.insert('E');
		letterTree.insert('H');
		letterTree.insert('I');
		System.out.println(
				"Root element of tree: " + letterTree.getRootElement());
		System.out.println("size:" + letterTree.size());
		System.out.println("tree should not be empty: "
				+ String.valueOf(letterTree.isEmpty() == false));
		System.out.println(letterTree);
	}
}
