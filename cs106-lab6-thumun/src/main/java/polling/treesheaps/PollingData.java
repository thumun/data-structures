package polling.treesheaps;

/**
 * PollingData class which organizes the data from the CSV (input)
 * 
 * @author Avi Patel, Neha Thumu
 * @version April 17, 2021
 */

public class PollingData implements Comparable<PollingData> {
	String lastName;
	String fullName;
	double peoplePolled;

	/**
	 * constructor for PollingData class. Sets lastName, fullName, and
	 * peoplePolled variables
	 * 
	 * @param String[] data (row in the csv file)
	 * @return N/A
	 */
	public PollingData(String[] data) {
		lastName = data[0];
		fullName = data[1];
		peoplePolled = Double.parseDouble(data[2]);
	}

	/**
	 * compares the polling numbers of one object with the polling numbers of
	 * the other and return the higher value. If same polling number, compares
	 * the last name of one object with the last name of the other and return a
	 * value dictating which one is alphabetically before the other
	 * 
	 * @param PollingData o (object of PollingData class)
	 * @return integer value of -1, 1, or 0 depending on the higher value
	 */
	@Override
	public int compareTo(PollingData o) {
		if (this.peoplePolled > o.peoplePolled) {
			return 1;
		} else if (this.peoplePolled < o.peoplePolled) {
			return -1;
		} else {
			return this.lastName.compareTo(o.lastName);
		}
	}

	/**
	 * returns object in form of it's full name and people polled number
	 * 
	 * @param N/A
	 * @return a String containing the object's full name: object's peoplePolled
	 */
	@Override
	public String toString() {
		return this.fullName + ":" + this.peoplePolled;
	}

}
