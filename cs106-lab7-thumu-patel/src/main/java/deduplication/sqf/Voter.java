package deduplication.sqf;

/**
 * Lab 7 for CS106
 *
 * This file represents the information pertaining to a voter. These attributes
 * help in organizing data related to a voter.
 * 
 * There are methods that return information about the above categories for an
 * object of the class.
 *
 * @author: Neha Thumu, Avi Patel
 * @version: May 12, 2021
 */

public class Voter {

	private String voterID;
	private int countyID;
	private String lastName;
	private String firstName;
	private String address;
	private boolean voterStatus;

	/**
	 * constructor for Voter class. Initializing parameters of object based on
	 * row from data
	 * 
	 * @param String[] row (row of data from file)
	 * @return N/A
	 */
	public Voter(String[] row) {

		this.voterID = row[0];
		this.countyID = Integer.valueOf(row[2]);
		this.lastName = row[3];
		this.firstName = row[4];
		this.address = row[11];
	}

	/**
	 * comparing the last name of one object with the last name of the other.
	 * Return depending on which is alphabetically greater. If the last names
	 * are equal than compares the first names.
	 * 
	 * @param Voter o
	 * @return int 0, -1, 1 (depending on equal, greater, less than)
	 */
	public int compareTo(Voter o) {

		if (this.lastName.equals(o.lastName)) {
			if (this.firstName.equals(o.firstName)) {
				return 0;
			}
			return this.firstName.compareTo(o.firstName);
		}
		return this.lastName.compareTo(o.lastName);
	}

	/**
	 * Getter for Voter's first name
	 * 
	 * @param N/A
	 * @return String first name of voter
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Getter for Voter's last name
	 * 
	 * @param N/A
	 * @return String last name of voter
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * prints last name of object and first name of object
	 * 
	 * @param N/A
	 * @return String lastName, firstName
	 */
	@Override
	public String toString() {
		return this.lastName + ", " + this.firstName;

	}

}
