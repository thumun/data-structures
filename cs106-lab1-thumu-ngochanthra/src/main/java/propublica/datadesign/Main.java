/**
* Lab 1 for CS106 - ProPublica Lab Pt.1
*
* This lab is setting up the methods/ideas that will be used in Lab 2. A class, Person, is created to hold the objects
* Objects of this class have different instance variables that gives more information about them (more info about this
* in class Person). There are also methods implemented to get specific information about said variables in the 
* form of a boolean. 
*
* @author: Neha Thumu and Mata Ngochanthra
* @version: March 2, 2021
*/


package propublica.datadesign;
import org.junit.Assert;

import propublica.datadesign.Person.RChargeDegree;

public class Main {
	// this should become the "Prediction Fails Differently for Black Defendants" table
	public static PropublicaDataTable racialBiasTable = null;
	
    public static void main( String[] args ) {
        // TODO: eventually set racialBiasTable to a new PropublicaDataTable with correct values.
    	try {
    	testConstructor(); 
    	testBools();
    	testStringArrConstructor();
    	testException();
    	}
    	catch (Exception e) {
    		System.out.println(e); 
    	}
    	
    }
    
	/** 
	 * method to test constructor in class Person 
	 * @param N/A
	 * @return N/A
	 */
    public static void testConstructor() throws Exception { // double check everything tested
    	// construct an object
    	Person Albert = new Person("Male", "Other", "F", "Aggravated Assault w/Firearm", "1", "Low", "0", "", "");
    	
    	// test the getter method for the sex field
    	System.out.println(Albert.getSex());
    	
    	// assertEquals will return no output if the Strings are equal
    	// otherwise, it will throw an Exception
    	Assert.assertEquals(Person.Sex.MALE, Albert.getSex());
    	
    	// other tests here 
    	Assert.assertEquals(Person.Race.OTHER, Albert.getRace());
    	Assert.assertEquals(Person.CChargeDegree.FELONY, Albert.getcChargeDegree());
    	Assert.assertEquals("Aggravated Assault w/Firearm", Albert.getcChargeDesc());
    	Assert.assertEquals(1, Albert.getDecileScore());
    	Assert.assertEquals(Person.ScoreText.LOW, Albert.getScoreText());
    	Assert.assertEquals(false, Albert.getTwoYearRecid());
    	Assert.assertEquals("", Albert.getrChargeDesc());
    	Assert.assertEquals(RChargeDegree.NO_R_CHARGE, Albert.getrChargeDegree());
    }
    
	/** 
	 * method to test methods that return booleans in class Person 
	 * @param N/A
	 * @return N/A
	 */
    public static void testBools() throws Exception{  // double check everything tested
    	Person Albert = new Person("Male", "Other", "F", "Aggravated Assault w/Firearm", "1", "Low", "0", "", "");
    	/*
    	System.out.println(Albert.isWhite());
    	System.out.println(Albert.isBlack());
    	System.out.println(Albert.hasReoffended());
    	System.out.println(Albert.isLowRisk()); 
    	System.out.println(Albert.isHighRisk()); 
    	*/
    	// add assert equals to check code 
    	Assert.assertEquals(false, Albert.isWhite());
    	Assert.assertEquals(false, Albert.isBlack());
    	Assert.assertEquals(false, Albert.hasReoffended());
    	Assert.assertEquals(true, Albert.isLowRisk());
    	Assert.assertEquals(false, Albert.isHighRisk());
    }
    
	/** 
	 * method to test constructor that takes array of Strings input in class Person 
	 * @param N/A
	 * @return N/A
	 */
    public static void testStringArrConstructor() throws Exception{  // double check everything tested
    	String[] row1 = new String[] {"Male", "Other", "F",
    	"Aggravated Assault w/Firearm", "1", "Low", "0", "", ""};
    	Person Will = new Person(row1);
    	/*
    	System.out.println(Will.isWhite());
    	System.out.println(Will.isBlack());
    	System.out.println(Will.hasReoffended());
    	System.out.println(Will.isLowRisk()); 
    	System.out.println(Will.isHighRisk()); 
    	*/
    	
    	// add assert equals to check code  
    	// testing boolean methods
    	Assert.assertEquals(false, Will.isWhite());
    	Assert.assertEquals(false, Will.isBlack());
    	Assert.assertEquals(false, Will.hasReoffended());
    	Assert.assertEquals(true, Will.isLowRisk());
    	Assert.assertEquals(false, Will.isHighRisk());
    	
    	// testing constructor
    	Assert.assertEquals(Person.Race.OTHER, Will.getRace());
    	Assert.assertEquals(Person.CChargeDegree.FELONY, Will.getcChargeDegree());
    	Assert.assertEquals("Aggravated Assault w/Firearm", Will.getcChargeDesc());
    	Assert.assertEquals(1, Will.getDecileScore());
    	Assert.assertEquals(Person.ScoreText.LOW, Will.getScoreText());
    	Assert.assertEquals(false, Will.getTwoYearRecid());
    	Assert.assertEquals("", Will.getrChargeDesc());
    	Assert.assertEquals(RChargeDegree.NO_R_CHARGE, Will.getrChargeDegree());
    	}
    
	/** 
	 * testing exceptions (with wrong inputs) 
	 * @param N/A
	 * @return N/A
	 */
    public static void testException() { // double check if everything tested 
    	try {	// checking sex 
    		String[] row48 = new String[] {"FMale", "aAfrican-American", "F", "Uttering a Forged Instrument", 
    		    	"8", "High", "0", "DOC/Fighting/Threatening Words", "(M3)"};
    		Person error = new Person(row48);
    	}
    	catch(Exception e) {
    		System.out.println(e);	
    	}
    	
    	try {	// checking race 
    		String[] row48 = new String[] {"Male", "aAfrican-American", "F", "Uttering a Forged Instrument", 
    		    	"8", "High", "0", "DOC/Fighting/Threatening Words", "(M3)"};
    		Person error = new Person(row48);
    	}
    	catch(Exception e) {
    		System.out.println(e);	
    	}
    	
    	try {	// checking current charge degree 
    		String[] row48 = new String[] {"Male", "African-American", "MF", "Uttering a Forged Instrument", 
    		    	"8", "High", "0", "DOC/Fighting/Threatening Words", "(M3)"};
    		Person error = new Person(row48);
    	}
    	catch(Exception e) {
    		System.out.println(e);	
    	}
    	
    	try {	// checking decile score 
    		String[] row48 = new String[] {"Male", "African-American", "F", "Uttering a Forged Instrument", 
    		    	"a8", "High", "0", "DOC/Fighting/Threatening Words", "(M3)"};
    		Person error = new Person(row48);
    	}
    	catch(Exception e) {
    		System.out.println(e);	
    	}
    	
    	try {	// checking score
    		String[] row48 = new String[] {"Male", "African-American", "F", "Uttering a Forged Instrument", 
    		    	"8", "LowHigh", "0", "DOC/Fighting/Threatening Words", "(M3)"};
    		Person error = new Person(row48);
    	}
    	catch(Exception e) {
    		System.out.println(e);	
    	}
    	
    	try {	// checking two year recid 
    		String[] row48 = new String[] {"Male", "African-American", "F", "Uttering a Forged Instrument", 
    		    	"8", "High", "a0", "DOC/Fighting/Threatening Words", "(M3)"};
    		new Person(row48);
    	}
    	catch(Exception e) {
    		System.out.println(e);	
    	}
    	
    	try {	// checking recid charge degree
    		String[] row48 = new String[] {"Male", "African-American", "F", "Uttering a Forged Instrument", 
    		    	"8", "High", "0", "DOC/Fighting/Threatening Words", "(MO3)"};
    		Person error = new Person(row48);
    	}
    	catch(Exception e) {
    		System.out.println(e);	
    	}
    }
}
