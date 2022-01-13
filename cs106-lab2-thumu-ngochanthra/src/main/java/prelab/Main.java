package prelab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;

import prelab.Student.classYear;
import prelab.Student.dean;
import prelab.Student.dorm;

/*
 * You should use this package for Pre-Lab 2 and Pre-Lab 3 Exercises.
 * Make sure you don't have errors in this package, since it will cause issues
 * with the autograder if there are any compilation issues.
 */

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO complete your pre-labs!
		
		ArrayList<String[]> rows = new ArrayList<String[]>();
		rows.add(new String[] {"Will", "20", "Sophomore", "Barclay", "Yes", "Michael Elias"});
		rows.add(new String[] {"Tim", "20", "Sophomore", "Barclay", "Yes", "Michael Elias"});

		
		AllStudents temp = new AllStudents();
		temp.addStringArray(rows);
		System.out.println(temp); // gotta add enum to string for it to work..
		
		//Student Will = ["Will", 20, classYear.SOPHOMORE, dorm.BARCLAY, true, dean.MICHAEL_ELIAS];
		
		
		/* Header-aware reader of StudentProfile.csv
		Make sure StudentProfile.csv is accessible or provide the proper path Read and write to a file may throw an exception e.g., FileNotFound etc,
		make sure you handle such errors properly */ 
		
		/*
		CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(new FileReader("StudentProfile.csv"));
		
		// ArrayList containing each row of the csv as a String array 
		ArrayList<String[]> dataReadRows = new ArrayList<String[]>(csvReader.readAll());
		
		csvReader.close();
		*/
	}

}
