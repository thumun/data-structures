package propublica.datadesign;

/**
 * DO NOT MAKE CHANGES TO THIS CLASS TO ENSURE YOUR WORK IS VERIFIED CORRECTLY!
 * This class runs Main class's main method and checks the values of the racialBiasTable.
 * It outputs the status, details, and a disclaimer about the verification.
 */

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class VerifyFormat_Lab2 {
	
	private static PrintStream originalOut = System.out;
	
	public static void main(String[] args) {
		
		double[] correctValues = new double[] { 0.235, 0.449, 0.477, 0.280 };
		double[] studentValues;
		String[] meanings = new String[] { "False Positive White", "False Postive Black",
											"False Negative White", "False Negative Black" };
		boolean doesPass = true;
		int count = 0;
		header("status", true);
		
		// run main, get values from student's table and put them in array
		try {
			
			// redirect OutStream and run main
			PrintStream stuOut = new PrintStream(new ByteArrayOutputStream());
			System.out.flush();
			System.setOut(stuOut);
			Main.main(new String[] {}); // command line not enabled for this lab
			
			// revert the print stream
			System.out.flush();
			System.setOut(originalOut);
			
			// verify that everything is good
			if(Main.racialBiasTable == null) { // check if the table exists
				doesPass = false;
				notPassing(++count, "racialBiasTable seems to be null " +
									"- did you initialize it to a new PropublicaDataTable?");
			} else {
				// get student table
				PropublicaDataTable data = Main.racialBiasTable;
				studentValues = new double[] { data.getHighNoReoffendWhite(), data.getHighNoReoffendBlack(),
						data.getLowRecidivateWhite(), data.getLowRecidivateBlack() };
				
				// check values within 0.001 for each index
				for(int index = 0; index < correctValues.length; index++) {
					if(!(correctValues[index]-0.001 <= studentValues[index]
							&& studentValues[index] <= correctValues[index]+0.001)) {
						
						doesPass = false;
						String message = "The value for \"" + meanings[index] + "\" should be "
											+ correctValues[index] + " +/- 0.001.   Instead, found " + studentValues[index];
						if(count == 0) {
							notPassing(++count, message);					
						} else {
							detail(++count, message);
						}
					}
				}
			}
		} catch (Exception e) {
			// revert print stream in case
			System.out.flush();
			System.setOut(originalOut);
			doesPass = false;
			print("NOT PASSING!");
			header("details");
			print("Error occurred while running main of class Main:\n");
			e.printStackTrace();
		}
		
		if(doesPass)
			print("Passed :)");
		
		header("FYI");
		String disclaimer = "This class does not check if the numbers were calculated correctly.\n"
							+ "However, submitting work with racialBiasTable values hard-coded will result in a zero.";
		print(disclaimer);
	}
	
	private static void print(String msg) { System.out.println(msg); }
	private static void header(String title) { header(title, false); }
	private static void header(String title, boolean first) {
		StringBuilder builder = new StringBuilder();
		if(!first)
			builder.append("\n\n");
		builder.append("**** ");
		builder.append(title.toUpperCase());
		builder.append(" ****\n");
		print(builder.toString());
	}
	private static void detail(int count, String msg) { print(count + ".) " + msg); }
	
	// should be called when found first time not passing
	private static void notPassing(int count, String reason) {
		print("NOT PASSING!");
		header("details");
		detail(count, reason);
	}
}
