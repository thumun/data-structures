package prelab;

import prelab.Student.classYear;
import prelab.Student.dean;

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
	
	public Student (String name, int age, classYear classYear, dorm dorm, boolean campusJob, dean dean) { 
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
	
	public classYear getClassYear (String input) {
		if (input.equals("First Year")) {
			return classYear.FIRST_YEAR;
		}
		else if(input.equals("Sophomore")) {
			return classYear.SOPHOMORE;
		}
		else if (input.equals("Junior")) {
			return classYear.JUNIOR;
		}
		else{
			return classYear.SENIOR;
		}
	}
	
	/*
	public dorm getDorm (String input) {
		if (input.equals("Barclay")) {
			return dorm.BARCLAY;
		}
		else if(input.equals("Sophomore")) {
			return classYear.SOPHOMORE;
		}
		else if (input.equals("Junior")) {
			return classYear.JUNIOR;
		}
		else{
			return classYear.SENIOR;
		}
	}
	*/
	
	public String getName () {
		return name; 
	}
	
	public String setName (String newName){
		name = newName;
		return name; 
	}
	
	@Override
	public String toString() {
		return "Their name is " + name + " and their class year is" + classYear;
	}
		
}