package deduplication.sqf;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;
/**
 * The voterData class stores entire dataset of rows from a given input voter txt file as an ArrayList data structure 
 * of Voter objects. We use various techniques, including allPairs, hash linear, and quicksort, to deduplicate our ArrayList of 
 * Voter objects. We include a toString method to print out the initial and resulting deduplicated ArrayLists of Voter objects. 
 * 
 * @author Avi Patel, Neha Thumu
 * @version May 12, 2021
 */

public class VoterData {

	private ArrayList<Voter> voterRows;
	private int rows;

	/**
	 * constructor for VoterData class. For the input parameters: voterFile
	 * 
	 * Reads the file and converts the data into objects of the Voter class.
	 * 
	 * @param String[] voterFile (file with data)
	 * @return N/A
	 */
	public VoterData(String voterFile) {

		/** Instance variables for voterData */
		this.voterRows = new ArrayList<Voter>(); // stores each Voter from the input file in an ArrayList 

		try {
			CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(
					new FileReader(voterFile));
			ArrayList<String[]> voterEntries = new ArrayList<String[]>(
					csvReader.readAll());
			csvReader.close();

			// making objects of Voter class based on each row of data file
			for (String[] v : voterEntries) {
				voterRows.add(new Voter(v));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	/**
	 * constructor for VoterData class. For the input parameters: voterFile and
	 * rows. Added rows in order to make it easier to read long files (and test
	 * part 3).
	 * 
	 * Reads the file and converts the data into objects of the Voter class.
	 * 
	 * @param voterFile: input txt file with voter data 
	 * @param rows: number of rows to be read from input file
	 * @return N/A
	 * 
	 */
	public VoterData(String voterFile, int rows) {
		this.rows = rows;
		this.voterRows = new ArrayList<Voter>();

		try {
			CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(
					new FileReader(voterFile));
			ArrayList<String[]> voterEntries = new ArrayList<String[]>(
					csvReader.readAll());
			csvReader.close();

			// making objects of Voter class based on each row of data file
			for (int i = 0; i < this.rows; i++) {
				voterRows.add(new Voter(voterEntries.get(i)));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * returns the voterRows as an arrayList of type Voter
	 * 
	 * @param N/A
	 * @return ArrayList<Voter> voterRows
	 */
	public ArrayList<Voter> getVoterRows() {
		return this.voterRows;
	}

	/**
	 * allPairs Deduplication method.
	 * 
	 * finding the duplicates by comparing them with the other items in the data
	 * set
	 * 
	 * @param N/A
	 * @return ArrayList<Voter> voterDeduplcated (data without duplicates)
	 */
	public ArrayList<Voter> allPairsDeduplication() {
		ArrayList<Integer> duplicateIndicies = new ArrayList<Integer>();
		ArrayList<Voter> voterDeduplicated = new ArrayList<Voter>();

		for (int i = 0; i < voterRows.size(); i++) {
			for (int j = i + 1; j < voterRows.size(); j++) {
				// compares if i+1 value is the same as i's value
				if (voterRows.get(i).compareTo(voterRows.get(j)) == 0) {
					// adds indices of duplicates
					duplicateIndicies.add(j);
				}
			}
		}

		// adds the values are not in the duplicate's indicies
		for (int i = 0; i < voterRows.size(); i++) {
			if (!duplicateIndicies.contains(i)) {
				voterDeduplicated.add(voterRows.get(i));
			}
		}

		return voterDeduplicated;
	}

	/**
	 * hash linear Deduplication method.
	 * 
	 * finding the duplicates by using a hash map. The key is the last name and
	 * first name of the object & the value is the object. The objects are added
	 * to the hash map based on if the key is not already present in the hash
	 * map.
	 * 
	 * @param N/A
	 * @return ArrayList<Voter> voterDeduplcated (data without duplicates)
	 */
	public ArrayList<Voter> hashLinearDeduplication() {
		int capacity = 1000003;
		ProbeHashMap<String, Voter> voterLinearHash = new ProbeHashMap<String, Voter>(
				capacity);
		voterLinearHash.createTable();

		ArrayList<Voter> voterDeduplicatedRows = new ArrayList<Voter>();

		// adds to hash map if the last name + first name of an object aren't
		// already present in the hash map
		for (int i = 0; i < voterRows.size(); i++) {
			if (voterLinearHash.get(voterRows.get(i).getLastName()
					+ voterRows.get(i).getFirstName()) == null) {
				voterLinearHash.put(
						voterRows.get(i).getLastName()
								+ voterRows.get(i).getFirstName(),
						voterRows.get(i));
			}
		}

		// adds each object in hash map to the deduplicated arraylist
		for (Entry<String, Voter> O : voterLinearHash.entrySet()) {
			voterDeduplicatedRows.add(O.getValue());
		}

		return voterDeduplicatedRows;
	}

	/**
	 * swaps two elements in voterRows based on index
	 * 
	 * @param i: index of first Voter to be swapped
	 * @param j: index of second Voter to be swapped 
	 * @return N/A
	 */
	public void swap(int i, int j) {
		Voter temp = this.voterRows.get(i);
		voterRows.set(i, voterRows.get(j));
		voterRows.set(j, temp);
	}

	/**
	 * sorts elements in voterRows ArrayList using quicksort algorithm
	 * 
	 * @param int left, int right
	 * @return N/A
	 */
	public void quickSort(int left, int right) {

		if (left >= right)
			return;
		int k = left;
		int j = right;
		// pivot value = middle of array
		Voter pivotValue = this.voterRows.get((left + right) / 2);
		while (k < j) {
			// compare left value with pivot
			while (this.voterRows.get(k).compareTo(pivotValue) < 0) {
				k++; // increase position
			}

			// compare right value with pivot
			while (pivotValue.compareTo(this.voterRows.get(j)) < 0) {
				j--; // decrease position
			}
			if (k <= j) { // if position k is less than or equal to j
				swap(k, j);
				k++;
				j--;
			}
		}

		quickSort(left, j);
		quickSort(k, right);

	}

	/**
	 * deduplication of quick sorted array
	 * 
	 * @param N/A
	 * @return ArrayList<Voter> voterDeduplcated (data without duplicates)
	 */
	public ArrayList<Voter> quicksortDeduplication() {
		Voter current = this.voterRows.get(0);
		ArrayList<Voter> voterDeduplicated = new ArrayList<Voter>();

		voterDeduplicated.add(current);

		for (int i = 1; i < voterRows.size(); i++) {

			// if not equal to current Voter then add to deduplicated ArrayList and current value updated
			if (current.compareTo(voterRows.get(i)) != 0) {
				voterDeduplicated.add(voterRows.get(i));
				current = voterRows.get(i);
			}
		}

		return voterDeduplicated;

	}

	/**
	 * toString method which prints relevant parameters (first and last names) for each voter in ArrayList
	 * 
	 * @param N/A
	 * @return each row of the voterRows (each Voter in the array)
	 */
	@Override
	public String toString() {
		String output = "";

		for (Voter v : this.voterRows) {
			output += v + "\n";
		}

		return output;

	}
}
