/**
* Lab 2 for CS106 - ProPublica Lab Pt.2
*
* This lab is using the variables, methods, and objects created in Lab 1. A class, People,
*  is created to organize objects of the Person class. People also calculates percentages 
*  for the data table based on data from the objects of the Person class.  
*
* @author: Neha Thumu and Mata Ngochanthra
* @version: March 9, 2021
*/

package propublica.datadesign;
import java.io.FileReader;
import java.util.ArrayList;
import com.opencsv.CSVReaderHeaderAware;


public class Main {
	// this should become the "Prediction Fails Differently for Black Defendants" table
	
	public static PropublicaDataTable racialBiasTable; // should be +/- 0.1
	public static PropublicaDataTable racialBiasTableRecid;
	
    public static void main( String[] args ){
    	try {
    	
    	// reading data from csv and adding it to obj of class People
    	CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader("compas-scores.csv"));
    	ArrayList<String[]> myEntries = new ArrayList<String[]>(reader.readAll());
    	reader.close();
    	
    	People people = new People();
    	people.addStringArray(myEntries); // converting arraylist into Person objects 
    
    	
    	racialBiasTable = new PropublicaDataTable(people.percentageWhiteHighNoRe(), 
    			people.percentageBlackHighNoRe(), people.percentageWhiteLowRe(), 
    			people.percentageBlackLowRe());
    	System.out.println("When consider minor crimes as recidivism (#4.2)");
    	System.out.println(racialBiasTable);
    	
    	racialBiasTableRecid = new PropublicaDataTable(people.newPercentageWhiteHighNoRe(), 
    			people.newPercentageBlackHighNoRe(), people.newPercentageWhiteLowRe(), 
    			people.newPercentageBlackLowRe());
    	System.out.println("When not consider minor crimes as recidivism (#5)");
    	System.out.println(racialBiasTableRecid);
    
    	
    	//testAdding();
    	
    	}
    	catch (Exception e){
    		System.out.println(e.getMessage());
    	}
    	
    }
    
    /** 
	 * testing if addStringArray is working 
	 * @param ArrayList<String []> dataRow
	 * @return N/A
	 */
    public static void testAdding() throws Exception {
    	ArrayList<String[]> rows = new ArrayList<String[]>();
		rows.add(new String[] {"Male", "Other", "F", "Aggravated Assault w/Firearm", "1", 
				"Low", "0", "", ""});
		rows.add(new String[] {"Male", "Asian", "F", "Aggravated Assault w/Firearm", "1", 
				"Low", "0", "", ""});

		People testPerson = new People();
		testPerson.addStringArray(rows);
		
		System.out.println(testPerson); // checking if input working 
    }
    
    /** 
	 * checking if the data is being read & processed (becoming obj of type person)
	 * @param ArrayList<String[]> entries (data from csv file)
	 * @return N/A
	 */
    public void testFillEntries(ArrayList<String[]> entries) {
		ArrayList<String[]> failEntries = new ArrayList<String[]>();
		
		
    	for (int i = 0; i < entries.size(); i++) {
    		
    		try {
    		Person person = new Person(entries.get(i)); // testing if inputs valid by making obj
    		
    		}
    		catch(Exception e) {
    			failEntries.add(entries.get(i));
    		}
    		
    		/*
    		for (int j = 0; j < myEntries.get(i).length; j++) {
    			System.out.println(myEntries.get(i)[j]);
    		}
    		*/
    		//System.out.println(myEntries.get(i));
    	}
    	
    	for (int i = 0; i < failEntries.size(); i++) {	// personal check to see which did not work
    		for (int j = 0; j < failEntries.get(i).length; j++) {
    			System.out.println(failEntries.get(i)[j]);
    		}
    	}
    	
    }
    
    /** 
	 * check recid charge degree: if it is minor crime, put them in a list of not consider recidivism
	 * @param People people [object of type People]
	 * @return notRecid [array of those who did not recide in our opinion]
	 */
    public static ArrayList<Person> checkRecidCrime(People people) throws Exception{
    	ArrayList<Person> defendants = new ArrayList<Person>();
    	defendants = people.getPeople();
    	ArrayList<Person> notRecid = new ArrayList<Person>();
    	
		for (int row = 0; row < defendants.size(); row++) {
			if (defendants.get(row).hasReoffended()) {
				if (defendants.get(row).rChargeDegree.equals(Person.RChargeDegree.M1) || 
						defendants.get(row).rChargeDegree.equals(Person.RChargeDegree.M2) ||
						defendants.get(row).rChargeDegree.equals(Person.RChargeDegree.M3) ||
						defendants.get(row).rChargeDegree.equals(Person.RChargeDegree.MO3)||
						defendants.get(row).rChargeDegree.equals(Person.RChargeDegree.CO3)) {
						
					notRecid.add(defendants.get(row));
					}
				}
			}
		
		return notRecid;
		}
    
	}
