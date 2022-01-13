package prelab;

import prelab.Student.classYear;
import prelab.Student.dean;
import prelab.Student.dorm;

public class Student { 
	// test
	
	public enum classYear{
		FIRST_YEAR, SOPHOMORE, JUNIOR, SENIOR
	}; 
	public enum dorm{
		BARCLAY, TRITTON, JONES, GUMMERE, APARTMENTS, LEEDS, KIM, 
		LLOYD, QHOUSE, DRINKER, BCC, COMFORT, LUNT
	}; 
	public enum dean{
		MICHAEL_ELIAS, KATRINA_GLANZER, MARTHA_DENNEY, 
		THERESA_TENSUAN, KELLY_WILCOX, BRIAN_CUZZOLINA
	};
	
	String name; 
	int age; 
	classYear classYear;
	dorm dorm;
	boolean campusJob;
	dean dean;
	
	public Student(String [] inputArray) throws Exception {
		name = inputArray[0];
		age = Integer.parseInt(inputArray[1]);
		setClassYear(inputArray[2]);
		setDorm(inputArray[3]);
		setCampusJob(inputArray[4]);
		setDean(inputArray[5]);
	}
	
	
	/*public Student (String name, int age, classYear classYear, dorm dorm, boolean campusJob, dean dean) { 
		this.name = name; 
		this.age = age; 
		this.classYear = classYear;
		this.dorm = dorm;
		this.campusJob = campusJob;
		this.dean = dean;
	}
	
	/*
	public Student (String name, String age, String classYear, String dorm, String campusJob, String dean) { 
		this.name = name; 
		this.age = Integer.parseInt(age); 
		this.classYear = getClassYear(classYear);
		this.dorm = dorm;
		this.campusJob = Boolean.parseBoolean(campusJob);
		this.dean = dean;
	}
	*/
	
	public classYear setClassYear (String input) throws Exception{
		if (input.equals("First Year")) {
			return classYear.FIRST_YEAR;
		}
		else if(input.equals("Sophomore")) {
			return classYear.SOPHOMORE;
		}
		else if (input.equals("Junior")) {
			return classYear.JUNIOR;
		}
		else if (input.equals("Senior")){
			return classYear.SENIOR;
		}
		else {
			throw new Exception("Class year not recognized");
		}
	}
	
	public dorm setDorm (String input) throws Exception{
		if (input.equals("Barclay")) {
			return dorm.BARCLAY;
		}
		else if(input.equals("Tritton")) {
			return dorm.TRITTON;
		}
		else if(input.equals("Tritton")) {
			return dorm.TRITTON;
		}
		else if(input.equals("Jones")) {
			return dorm.JONES;
		}
		else if(input.equals("Gummere")) {
			return dorm.GUMMERE;
		}
		else if(input.equals("Apartments")) {
			return dorm.APARTMENTS;
		}
		else if(input.equals("Leeds")) {
			return dorm.LEEDS;
		}
		else if(input.equals("Kim")) {
			return dorm.KIM;
		}
		else if(input.equals("Lloyd")) {
			return dorm.LLOYD;
		}
		else if(input.equals("QHouse")) {
			return dorm.QHOUSE;
		}
		else if(input.equals("BCC")) {
			return dorm.BCC;
		}
		else if(input.equals("Comfort")) {
			return dorm.COMFORT;
		}
		else if(input.equals("Lunt")) {
			return dorm.LUNT;
		}
		else{
			throw new Exception("Dorm input not recognized");
		}
	}
	
	public boolean setCampusJob(String input) throws Exception{
		if (input.equals("Yes")) {
			return true; 
		}
		else if (input.equals("No")) {
			return false;
		}
		else {
			throw new Exception("Campus job input not recognized");
		}
	}
	
	public dean setDean (String input) throws Exception{
		if (input.equals("Michael Elias")) {
			return dean.MICHAEL_ELIAS;
		}
		else if(input.equals("Katrina Glanzer")) {
			return dean.KATRINA_GLANZER;
		}
		else if(input.equals("Martha Denney")) {
			return dean.MARTHA_DENNEY;
		}
		else if(input.equals("Theresa Tensuan")) {
			return dean.THERESA_TENSUAN;
		}
		else if(input.equals("Kelly Wilcox")) {
			return dean.KELLY_WILCOX;
		}
		else if(input.equals("Brian Cuzzolina")) {
			return dean.BRIAN_CUZZOLINA;
		}
		
		else{
			throw new Exception("Dean not recognized");
		}
	}
	
	public String getName () {
		return name; 
	}
	
	public String setName (String newName){
		name = newName;
		return name; 
	}
	
	@Override
	public String toString() {
		return "Their name is " + name + " and their class year is" + campusJob;
	}
	
	public String classYeartoString(classYear classYear) {
		if (classYear.equals(classYear.FIRST_YEAR)) {
			return "First Year";
		}
		else if(classYear.equals(classYear.SOPHOMORE)) {
			return "Sophomore";
		}
		else if(classYear.equals(classYear.JUNIOR)) {
		return "Sophomore";
		}
		else {
			return "Senior";
		}
		
	public String deantoString(dean dean) {
		if (dean.equals(dean.MICHAEL_ELIAS)) {
			return "MICHAEL_ELIAS";
		}
		else if(dean.equals(dean.KATRINA_GLANZER)) {
			return "KATRINA_GLANZER";
		}
		else if(dean.equals(dean.MARTHA_DENNEY)) {
			return "MARTHA_DENNEY";
		}
		else if(dean.equals(dean.THERESA_TENSUAN)) {
			return "THERESA_TENSUAN";
		}
		else if(dean.equals(dean.KELLY_WILCOX)) {
			return "KELLY_WILCOX";
		}
		else {
			return "BRIAN_CUZZOLINA";
		}
		
	}
}