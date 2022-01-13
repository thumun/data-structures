/**
* This class creates the nodes for the linked list. These nodes contain information 
* about the students from the lotteries. Their data is organized based on their major(s)/minor(s),
* institution, status (enrollment), name, class year, if they got the previous lottery, 
* and their point total (likeliness of getting a position on the lottery) 
* 
* The methods return information in the above categories and organize information based on 
* their input types. 
*
* @author: Neha Thumu 
* @version: March 21, 2021
*/

package lottery.linkedlist;

import java.util.ArrayList;

public class StudentNode implements Comparable<StudentNode> {
	/**
	 * enum to categorize major/minor
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public enum Programs {
		H, N, S
	};

	/**
	 * enum to categorize student's institution
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public enum Institution {
		BMC, HC, SWAT
	};

	/**
	 * enum to categorize status of enrollment
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public enum Status {
		ENROLLED, WAITLISTED
	};

	Status status;
	String studentName;
	int classYear;
	ArrayList<Programs> majors = new ArrayList<Programs>();
	ArrayList<Programs> minors = new ArrayList<Programs>();
	Institution institution;
	boolean previousLottery;
	int points;

	StudentNode previous = null;
	StudentNode next = null;

	/**
	 * constructor for info from csv file w/ lottery results as an array of
	 * Strings
	 * 
	 * @param String[] data
	 * @return N/A
	 */
	public StudentNode(String[] data) throws Exception {
		studentName = data[0];
		classYear = Integer.parseInt(data[1]);
		setMajor(data[2]);
		setMinor(data[3]);
		setInstitution(data[4]);
		setPreviousLottery(data[5]);
		points = 0;
	}

	/**
	 * constructor for info from csv file w/ lottery results as an array of
	 * Strings & points
	 * 
	 * @param String[] data, int points
	 * @return N/A
	 */
	public StudentNode(String[] data, int points) throws Exception {
		studentName = data[0];
		classYear = Integer.parseInt(data[1]);
		setMajor(data[2]);
		setMinor(data[3]);
		setInstitution(data[4]);
		setPreviousLottery(data[5]);
		this.points = points;

	}

	/**
	 * constructor for node (setting previous and next)
	 * 
	 * @param StudentNode previous, StudentNode next
	 * @return N/A
	 */
	public StudentNode(StudentNode previous, StudentNode next) {
		this.previous = previous;
		this.next = next;
	}

	/**
	 * returns object's instance variable for studentName
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public String getName() {
		return this.studentName;
	}

	/**
	 * returns object's instance variable for points
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * returns object's instance variable for classYear
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public int getClassYear() {
		return this.classYear;
	}

	/**
	 * returns object's instance variable for enrollment status
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public String getStatus() {
		if (this.status.equals(Status.ENROLLED)) {
			return "enrolled";
		} else {
			return "waitlisted";
		}
	}

	/**
	 * converts string input to major/minor info if has multiple, make an array
	 * based on major(s)
	 * 
	 * @param input (Matches string input with appropriate constant in enum. If
	 *              other, throws error)
	 * @return N/A
	 * @throws exception if input not valid major ("H", "N", or "S")
	 */
	private void setMajor(String input) throws Exception {
		String[] strMajor = input.split("/");
		for (int i = 0; i < strMajor.length; i++) {

			if (strMajor[i].equals("H")) {
				majors.add(Programs.H);
			} else if (strMajor[i].equals("N")) {
				majors.add(Programs.N);
			} else if (strMajor[i].equals("S")) {
				majors.add(Programs.S);
			} else if (strMajor[i].equals("")) {
			} else {
				throw new Exception(strMajor[i] + ": Invalid Major");
			}
		}
	}

	/**
	 * converts string input to major/minor info if has multiple, make an array
	 * based on minor(s)
	 * 
	 * @param input (Matches string input with appropriate constant in enum. If
	 *              other, throws error)
	 * @return N/A
	 * @throws exception if input not valid minor ("H", "N", or "S")
	 */
	private void setMinor(String input) throws Exception {
		String[] strMinor = input.split("/");
		for (int i = 0; i < strMinor.length; i++) {

			if (strMinor[i].equals("H")) {
				minors.add(Programs.H);
			} else if (strMinor[i].equals("N")) {
				minors.add(Programs.N);
			} else if (strMinor[i].equals("S")) {
				minors.add(Programs.S);
			} else if (strMinor[i].equals("")) {
			} else {
				throw new Exception(strMinor[i] + ": Invalid Minor");
			}
		}
	}

	/**
	 * converts string input to enum Institution
	 * 
	 * @param input (Matches string input with appropriate constant in enum. If
	 *              other, throws error)
	 * @return N/A
	 * @throws exception if input not valid institution ("HC", "BMC", or "SWAT")
	 */
	private void setInstitution(String input) throws Exception {
		if (input.equals("HC")) {
			institution = (Institution.HC);
		} else if (input.equals("BMC")) {
			institution = (Institution.BMC);
		} else if (input.equals("SWAT")) {
			institution = (Institution.SWAT);
		} else {
			throw new Exception(input + ": Invalid Institution");
		}
	}

	/**
	 * converts string input to enum status
	 * 
	 * @param input (Matches string input with appropriate constant in enum. If
	 *              other, throws error)
	 * @return N/A
	 */
	public void setStatus(Status enrolled) {
		this.status = enrolled;
	}

	/**
	 * converts string to boolean detailing if student got into previous lottery
	 * 
	 * @param input (Matches string input with appropriate constant in enum. If
	 *              other, throws error)
	 * @return N/A
	 * @throws exception if input not yes/no
	 */
	private void setPreviousLottery(String input) throws Exception {
		if (input.equals("Yes")) {
			previousLottery = true;
		} else if (input.equals("No")) {
			previousLottery = false;
		} else {
			throw new Exception(input + ": Invalid Previous Lottery");
		}
	}

	/**
	 * calculates and sets number of points a student node has
	 * 
	 * @param CourseInfo courseInfo (object of that class)
	 * @return N/A
	 */
	public void setPoints(CourseInfo courseInfo) {
		points = 0;
		points += courseInfo.getYearPreference()[classYear - 1];

		for (int i = 0; i < majors.size(); i++) {
			if (majors.get(i) == Programs.H) {
				points += courseInfo.getMajorPreference()[0];
			} else if (majors.get(i) == Programs.N) {
				points += courseInfo.getMajorPreference()[1];
			} else if (majors.get(i) == Programs.S) {
				points += courseInfo.getMajorPreference()[2];
			}
		}

		for (int i = 0; i < minors.size(); i++) {
			if (minors.get(i) == Programs.H) {
				points += courseInfo.getMinorPreference()[0];
			} else if (minors.get(i) == Programs.N) {
				points += courseInfo.getMinorPreference()[1];
			} else if (minors.get(i) == Programs.S) {
				points += courseInfo.getMinorPreference()[2];
			}
		}

		if (institution == Institution.BMC || institution == Institution.HC) {
			points += courseInfo.getBiCoPreference();
		}

		if (previousLottery) {
			points += courseInfo.getPreviousPreference();
		}
	}

	/**
	 * compares two nodes in order to find the greater one. Points is primary
	 * consideration then student name and class year
	 * 
	 * @param StudentNode o (the node that will be compared w/ 'this' node)
	 * @return 1, -1, or 0 based on if two objects are greater than, less than,
	 *         or equal to
	 */
	@Override
	public int compareTo(StudentNode o) {
		if (this.points > o.points) {
			return 1;
		} else if (this.points < o.points) {
			return -1;
		} else if (this.studentName.compareTo(o.studentName) < 0) {
			// flipped the signs b/c g comes before w in lexographic order
			return 1;
		} else if (this.studentName.compareTo(o.studentName) > 0) {
			return -1;
		} else if (this.classYear < o.classYear) {
			// earlier class year before later
			return 1;
		} else if (this.classYear > o.classYear) {
			return -1;
		}
		return 0;
	}

	/**
	 * setting value of next
	 * 
	 * @param StudentNode next
	 * @return N/A
	 */
	public void setNext(StudentNode next) {
		this.next = next;
	}

	/**
	 * setting value of previous
	 * 
	 * @param StudentNode previous
	 * @return N/A
	 */
	public void setPrevious(StudentNode previous) {
		this.previous = previous;
	}

	/**
	 * getting the next node
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public StudentNode getNext() {
		return next;
	}

	/**
	 * getting previous node
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public StudentNode getPrevious() {
		return previous;
	}

}
