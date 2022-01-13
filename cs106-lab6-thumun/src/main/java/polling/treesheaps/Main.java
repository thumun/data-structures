/**
* Lab 6 for CS106 
*
* This lab creates and implements an Array Heap. 
* 
* The lab uses the Array Heap to organize polling data by storing information about 
* candidates and their polling numbers. 
* 
*
* @author: Neha Thumu 
* @version: April 30, 2021
*/

package polling.treesheaps;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReaderHeaderAware;

public class Main {

	public static void main(String[] args) {

		try {
			// reading polling data from given file and adding to array list
			ArrayHeap<PollingData> pollingEntries = new ArrayHeap<PollingData>();

			CSVReaderHeaderAware reader = new CSVReaderHeaderAware(
					new FileReader(args[0]));
			ArrayList<String[]> dataEntries = new ArrayList<String[]>(
					reader.readAll());
			reader.close();

			// making a heap of candidates to be sorted by their polling numbers
			for (int j = 0; j < dataEntries.size(); j++) {
				PollingData entry = new PollingData(dataEntries.get(j));
				pollingEntries.insert(entry);
			}

			// System.out.println(pollingEntries);

			// printing candidates from largest polling numbers to smallest
			while (pollingEntries.size() > 0) {
				System.out.println(pollingEntries.removeMax());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Tests for ArrayHeap class:

		testArrayHeap();
		testArrayHeapLetters();
		testHeapSort();
	}

	/**
	 * testing the methods of the arrayHeap class
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public static void testArrayHeap() {
		ArrayHeap<Integer> test = new ArrayHeap<>();

		Integer[] arr = { -2, 3, 9, -7, 1, 2, 6, -3 };

		System.out.println(""); // line break
		System.out.println("testArrayHeap(): ");

		for (int i = 0; i < arr.length; i++) {
			test.insert(arr[i]);
		}

		System.out.println("initial tree: \n" + test.toString());

		System.out.println("removeMax(): " + test.removeMax() + "\n");

		System.out.println("after removeMax(): \n" + test.toString());

		System.out.println("removeMax(): " + test.removeMax() + "\n");

		System.out.println("after removeMax(): \n" + test.toString());

	}

	/**
	 * testing the methods of the arrayHeap class w/ letters
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public static void testArrayHeapLetters() {
		System.out.println("testArrayHeapLetters(): ");
		ArrayHeap<Character> letterHeap = new ArrayHeap<Character>();
		letterHeap.insert('A');
		letterHeap.insert('C');
		letterHeap.insert('G');
		letterHeap.insert('B');
		letterHeap.insert('D');
		letterHeap.insert('G'); // inserting again, will still both copies
		letterHeap.insert('F');
		letterHeap.insert('E');
		letterHeap.insert('H');
		letterHeap.insert('I');
		System.out.println("size:" + letterHeap.size());
		System.out.println(letterHeap);
	}

	/**
	 * testing the sort() method of the arrayHeap class
	 * 
	 * @param E element (type dependent on input)
	 * @return N/A
	 */
	public static void testHeapSort() {
		System.out.println(""); // line break
		System.out.println("testHeapSort(): ");

		Integer[] arr = { -2, 3, 9, -7, 1, 2, 6, -3 };
		ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(arr));

		ArrayHeap<Integer> heap = new ArrayHeap<Integer>(array);
		System.out.println(heap); // should print the same heap as before (9, 1
									// 6, ...)
		heap.sort();
		System.out.println(array);

	}
}
