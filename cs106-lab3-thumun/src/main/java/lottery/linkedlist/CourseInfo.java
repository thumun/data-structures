package lottery.linkedlist;

/*
 * @author CS 106 TA
 * 
 * Stores the information about a course such as
 * its name, enrollment and waitlist capacities, and lottery preferences.
 */

public class CourseInfo {
	// member variables
	private String courseName;		// name of the course
	private int enrollCapacity;		// capacity for enrollment
	private int waitlistCapacity;	// capacity for waitlist
	// preference points for class years
	//with indices 1 to 4 mapping to first-year to senior
	private int[] yearPreference;
	// preference points for major and minors
	// with index-subject correspondence of 0:H, 1:N, 2:S
	private int[] majorPreference;
	private int[] minorPreference;
	private int biCoPreference; // preference points for students in Bi-Co
	// preference points for students previously lotteried out
	private int previousLotteryPreference;
	
	/**
	 * Constructor that parses the given information about the course
	 * and initializes the fields. The argument is typically the first
	 * row of the lottery CSV files.
	 * @param weightInfo	Course lottery specification as described in Section 3.1	
	 */
	public CourseInfo(String[] weightInfo) {
		// split and save first into name, enroll capacity, and waitlist capacity
		String[] basicInfo = weightInfo[0].split("/");
		courseName = basicInfo[0];
		enrollCapacity = Integer.parseInt(basicInfo[1]);
		waitlistCapacity = Integer.parseInt(basicInfo[2]);
		
		// parse and initialize other variables using helper or built-in methods
		parseYearPrefs(weightInfo[1]);
		majorPreference = parseStudyPreference(weightInfo[2]);
		minorPreference = parseStudyPreference(weightInfo[3]);
		biCoPreference = Integer.parseInt(weightInfo[4]);
		previousLotteryPreference = Integer.parseInt(weightInfo[5]);
	}
	
	/**
	 * Parses the string for majors and minors with the format described
	 * in "Data" Section of Data Description document.
	 * @param studyPref		String containing field:points,
	 * 						separated by "/" for each major.
	 * @return	The list containing points for each field of study,
	 * 			with index 1 for "H", index 2 for "N", and index 3 for "S".
	 */
	private int[] parseStudyPreference(String studyPref) {
		int[] preferences = new int[3];	// new array to return
		// separate the fields and point values based on "/"
		String[] fieldPrefs = studyPref.split("/");
		
		// iterate through and assign values
		for(int index = 0; index < fieldPrefs.length; index++) {
			String fieldPref = fieldPrefs[index];
			
			if(fieldPref.equals(""))
				continue;	// skip empty entries if any
			
			// separate pref into field (index 0) and points (index 1)
			String[] pair = fieldPref.split(":");
			
			// set points based on index-to-field correspondence.
			if(pair[0].equals("H"))
				preferences[0] = Integer.parseInt(pair[1]);
			else if(pair[0].equals("N"))
				preferences[1] = Integer.parseInt(pair[1]);
			else //if(pair[0].equals("S"))
				preferences[2] = Integer.parseInt(pair[1]);
		}
		return preferences;
	}
	
	/**
	 * Sets the yearPreference points by parsing the csv's distribution
	 * of points for respective class year.
	 * @param yearPref	course distribution of preference points for class years
	 */
	private void parseYearPrefs(String yearPref) {
		yearPreference = new int[4]; // array with an index for each class year
		
		// split each year's points into indices
		String[] yearPoints = yearPref.split("/");
		
		// parse and save each index of yearPoints to yearPreference
		for(int index = 0; index < yearPoints.length; index++)
			yearPreference[index] = Integer.parseInt(yearPoints[index]);
	}
	
	// getters
	public String getCourseName() { return courseName; }
	public int getEnrollCapacity() { return enrollCapacity; }
	public int getWaitlistCapacity() { return waitlistCapacity; }
	public int[] getYearPreference() { return yearPreference; }
	public int[] getMajorPreference() { return majorPreference; }
	public int[] getMinorPreference() { return minorPreference; }
	public int getBiCoPreference() { return biCoPreference; }
	public int getPreviousPreference() { return previousLotteryPreference; }
}
