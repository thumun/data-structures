package propublica.datadesign;

/** 
 * This class represents the information of a person who has committed a crime. It organizes
 * the information based on Sex, Race, current Charge Degree, current Charge description, 
 * Decile score, Score text, reciding charge description, and reciding charge degree.
 * 
 * There are methods that return information about the above categories for an object
 * of the class. 
 * 
 * There are methods that help analyze the relationship between race and committing a
 * crime. 
 */

public class Person {
	
	public enum Sex{
		MALE, FEMALE
	};
	
	public enum Race{
		ASIAN, AFRICAN_AMERICAN, CAUCASIAN, HISPANIC, OTHER
	};
	public enum CChargeDegree{
		FELONY, MISDEMEANOR
	};
	
	public enum ScoreText{
		LOW, MEDIUM, HIGH
	};
	
	public enum RChargeDegree{
		F1, F2, F3, M1, M2, M3, NO_R_CHARGE
	};
	
	Sex sex;
	Race race;
	CChargeDegree cChargeDegree;
	ScoreText scoreText;
	RChargeDegree rChargeDegree;
	
	int decileScore;
	boolean twoYearRecid; // if table shows 1 = True, 0 = False
	String cChargeDesc; 
	String rChargeDesc;
	
	/** 
	 * constructor for information from table row as type String
	 * @param sex, race, cChargeDegree, cChargeDesc, decileScore, scoreText, twoYearRecid,
	 * 		 rChargeDegree,  rChargeDesc
	 * @return N/A
	 */
	public Person(String sex, String race, String cChargeDegree, String cChargeDesc, String decileScore, 
			String scoreText,String twoYearRecid, String rChargeDegree, String rChargeDesc) throws Exception {	
		setSex(sex); 
		setRace(race);
		setcChargeDegree(cChargeDegree);
		this.cChargeDesc = cChargeDesc;
		this.decileScore = Integer.parseInt(decileScore);
		setScoreText(scoreText);
		setTwoYearRecid(twoYearRecid);
		setrChargeDegree(rChargeDegree);
		this.rChargeDesc = rChargeDesc;
	}
	
	/** 
	 * constructor for information from table row as an array of Strings
	 * @param [] inputArray
	 * @return N/A
	 */
	public Person(String [] inputArray) throws Exception {
		setSex(inputArray[0]); 
		setRace(inputArray[1]);
		setcChargeDegree(inputArray[2]);
		this.cChargeDesc = inputArray[3];
		this.decileScore = Integer.parseInt(inputArray[4]);
		setScoreText(inputArray[5]);
		setTwoYearRecid(inputArray[6]);
		this.rChargeDesc = inputArray[7];
		setrChargeDegree(inputArray[8]);
	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for sex
	 * @param N/A
	 * @return the object's instance variable for sex 
	 */
	public Sex getSex() {
		return this.sex;
	}
	
	/** 
	 * Converts string input to enum Sex
	 * @param input (Matches string input with appropriate constant in enum. If other, throws error)
	 * @return N/A
	 * @throws exception if string input does not match "Male" or "Female" (precondition)
	 */
	public void setSex(String input) throws Exception {
		if (input == "Male") {
			sex = Sex.MALE;
		}
		else if(input == "Female"){
			sex = Sex.FEMALE;
		}
		else {
			// if input is not "Male" or "Female" then invalid & throws exception
			throw new Exception("Sex input not recognized"); 
		}
	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for race
	 * @param N/A
	 * @return the object's instance variable for race 
	 */
    public Race getRace() {
    	return this.race;
    }
    
	/** 
	 * Converts string input to enum Race
	 * @param input (Matches string input with appropriate constant in enum. If input not recognized, throws error)
	 * @return N/A
	 * @throws exception if string input does not match race listed in enum (precondition) 
	 */
	public void setRace(String input) throws Exception {
		if(input.equals("Asian")) {
			race = Race.ASIAN;
		}
		else if(input.equals("African-American")) {
			race = Race.AFRICAN_AMERICAN;
		}
		else if(input.equals("Caucasian")) {
			race = Race.CAUCASIAN;
		}
		else if(input.equals("Hispanic")) {
			race = Race.HISPANIC;
		}
		else if (input.equals("Other")){
			race = Race.OTHER;
		}
		else {
			// if input is not "Asian" or "African-American" or "Caucasian" or "Hispanic" or "Other"
			// then invalid & throws exception
			throw new Exception("Race input not recognized");
		}
	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for scoreText
	 * @param N/A
	 * @return the object's instance variable for scoretext 
	 */
	public ScoreText getScoreText() {
		return this.scoreText;
	}
	
	/** 
	 * Converts string input to enum ScoreText
	 * @param input (Matches string input with appropriate constant in enum. If input not recognized, throws error)
	 * @return N/A
	 * @throws exception if string input does not match "Low" or "Medium" or "High" (precondition) 
	 */
	public void setScoreText(String input) throws Exception {
		if(input.equals("Low")) {
			scoreText = ScoreText.LOW;
		}
		else if (input.equals("Medium")){
			scoreText = ScoreText.MEDIUM;
		}	
		else if(input.equals("High")){
			scoreText = ScoreText.HIGH; 
		}
		else {
			// if input is not "Low" or "Medium" or "High" then invalid & throws exception
			throw new Exception("Score text input not recognized");
		}

	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for cChargeDegree
	 * @param N/A
	 * @return the object's instance variable for cChargeDegree 
	 */
	public CChargeDegree getcChargeDegree() {
		return this.cChargeDegree;
	}	
	
	/** 
	 * Converts string input to enum CChargeDegree
	 * @param input (Matches string input with appropriate constant in enum. If input not recognized, throws error)
	 * @return N/A
	 * @throws exception if string input does not match "F" or "M" (precondition) 
	 */
	public void setcChargeDegree (String input) throws Exception {
		if (input.equals("F")) {
			cChargeDegree = CChargeDegree.FELONY;
		}
		else if(input.equals("M")) {
			cChargeDegree = CChargeDegree.MISDEMEANOR;
		}
		else {
			// if input is not "F" or "M" then invalid & throws exception
			throw new Exception("cChargeDegree input not recognized");
		}
	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for cChargeDesc
	 * @param N/A
	 * @return the object's instance variable for cChargeDesc (the description of their charges)
	 */
	public String getcChargeDesc() {
		return this.cChargeDesc;
	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for decile score
	 * @param N/A
	 * @return the object's instance variable for decile score
	 */
	public int getDecileScore() {
		return this.decileScore;
	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for twoYearRecid
	 * @param N/A
	 * @return the object's instance variable for twoYearRecid (as a boolean)
	 */
	public boolean getTwoYearRecid() {
		return this.twoYearRecid;
	}
	
	/** 
	 * Converts string input to boolean for twoYearRecid
	 * @param input (matches string input w/ number equivalent to boolean)
	 * @return N/A
	 * @throws exception if string input does not match "0" or "1" (precondition) 
	 */
	public void setTwoYearRecid(String input) throws Exception{
		if (input.equals("0")) {
			twoYearRecid = false;
		}
		else if (input.equals("1")) {
			twoYearRecid = true;
		}
		else {
			throw new Exception("twoYearRecid input not recognized");
		}
	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for rChargeDesc
	 * @param N/A
	 * @return the object's instance variable for rChargeDesc (the description of their recid charges)
	 */
	public String getrChargeDesc() {
		return this.rChargeDesc;
	}
	
	/** 
	 * When this method is called, it returns the object's instance variable for rChargeDegree
	 * @param N/A
	 * @return the object's instance variable for rChargeDegree 
	 */
	public RChargeDegree getrChargeDegree() {
		return this.rChargeDegree;
	}
	
	/** 
	 * Converts string input to enum RChargeCharge
	 * @param input (Matches string input with appropriate constant in enum. If input not recognized, throws error)
	 * @return N/A
	 * @throws exception if string input does not match one of the enums (precondition) 
	 */
	public void setrChargeDegree (String input) throws Exception {
		if (input.equals("(F1)")) {
			rChargeDegree = RChargeDegree.F1;
		}
		else if (input.equals("(F2)")){
			rChargeDegree = RChargeDegree.F2;
		}
		else if (input.equals("(F3)")){
			rChargeDegree = RChargeDegree.F3;
		}
		else if (input.equals("(M1)")){
			rChargeDegree = RChargeDegree.M1;
		}
		else if (input.equals("(M2)")){
			rChargeDegree = RChargeDegree.M2;
		}
		else if (input.equals("(M3)")){
			rChargeDegree = RChargeDegree.M3;
		}
		else if(input.equals("")) {
			rChargeDegree = RChargeDegree.NO_R_CHARGE;
		}
		else {
			// if input is not "F1" or "F2" or "F3" or "M1" or "M2" or "M3" or "" then invalid & throws exception
			throw new Exception("rChargeDegree input not recognized");
		}
	}
	
	/** 
	 * Checks if object of class person's race is Caucasian
	 * @param N/A
	 * @return boolean
	 */
	public boolean isWhite() {
    	if (this.race.equals(Race.CAUCASIAN)) {
    		return true; 
    	}
    	else {
    		return false; 
    	}
    }    
	
	/** 
	 * Checks if object of class person's race is Black
	 * @param N/A
	 * @return boolean
	 */
    public boolean isBlack() {
    	if (this.race.equals(Race.AFRICAN_AMERICAN)) {
    		return true; 
    	}
    	else {
    		return false; 
    	}
    }
    
	/** 
	 * Checks if object of class person has re-offended 
	 * @param N/A
	 * @return boolean
	 */
    public boolean hasReoffended() {
    	if (this.twoYearRecid == true) {
    		return true; 
    	}
    	else {
    		return false; 
    	}
    }   
    
    /** 
	 * Checks if object of class person is at low risk of committing a crime
	 * @param N/A
	 * @return boolean
	 */
    public boolean isLowRisk() { 
    	if (this.scoreText.equals(ScoreText.LOW)) {
    		return true; 
    	}
    	else {
    		return false; 
    	}
    }
    
    /** 
	 * Checks if object of class person is at high risk of committing a crime 
	 * @param N/A
	 * @return boolean
	 */
    public boolean isHighRisk() {
    	if (this.scoreText.equals(ScoreText.LOW)) { 
    		return false; 
    	}
    	else {
    		return true; 
    	}
    }
	
}
	
