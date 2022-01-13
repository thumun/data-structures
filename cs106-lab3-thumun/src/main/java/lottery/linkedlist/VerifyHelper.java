package lottery.linkedlist;

// one day I'll comment even if I'm sleepy writing code...

// TODO: if an exception is raised while running Main, VerifyHelper stops :( fix it one day!!!

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VerifyHelper {

	protected class TestCase {
		private String fileName;
		private boolean isFind;	// true: --find, false: --print
		private String[] args;
		private boolean isPassing;
		private String expectedOutput;
		private Exception exceptionMain;
		
		protected TestCase(String expected, String cmdLnArg) {
			exceptionMain = null;
			isPassing = false;
			expectedOutput = expected.strip();
			String[] arguments = cmdLnArg.split(" ");
			if(arguments.length < 4) {
				String argumentErrorMessage = "Test case must be given 4+ arguments but found " + arguments.length;
				throw new IllegalArgumentException(argumentErrorMessage);
			}
			
			fileName = arguments[0];
			isFind = false;
			if(arguments[1].equals("--find") || arguments[1].equals("--print")) {
				if(arguments[1].equals("--find"))
					isFind = true;
			} else {
				String argumentErrorMessage = "Instead of --find or --print flag, found " + arguments[1];
				throw new IllegalArgumentException(argumentErrorMessage);
			}
			
			args = new String[] {arguments[0], arguments[1], arguments[2], arguments[3]};
		}
		
		protected String[] getCmdLnArguments() { return args; }
		protected String getFileName() { return fileName; }
		protected String getCourseName() {
			String courseName = (isFind) ? args[3] : args[2];
			return courseName;
		}
		protected boolean isFind() { return isFind; }
		protected boolean isPassing() { return isPassing; }
		protected void setPassing() { setPassing(true); }
		protected void setPassing(boolean val) { isPassing = val; }
		protected String getExpected() { return expectedOutput; }
		protected void setException(Exception e) { exceptionMain = e; }
		protected boolean hasException() { return exceptionMain != null; }
		protected Exception getException() { return exceptionMain; }
		// call only for find
		protected String getStudentName() { return args[2]; }
	}
	
	// field variables for VerifyHelper
	private ArrayList<TestCase> testCases;
	private boolean doesPass;
	private int detailIndex;
	private boolean flagged;
	private Exception mainException;
	
	// constructor
	public VerifyHelper() {
		doesPass = true;
		flagged = false;
		detailIndex = 1;
		testCases = new ArrayList<TestCase>();
		mainException = null;
	}
	
	protected void addTestCase(String expectedOutput, String commandLineArgument) {
		testCases.add(new TestCase(expectedOutput, commandLineArgument));
	}
	
	protected void checkStatus() {
		mainException = null;
		for(TestCase testCase : testCases) {
			// get output from running main
			String studentOutput = getOutput(testCase.getCmdLnArguments());
			
			// check if output is inside and set passing status
			if(studentOutput == null) {
				if(mainException != null) {
					testCase.setException(mainException);
					mainException = null;
				}
				continue;	// skip ones with Exceptions
			}
			
			String[] studentLines = studentOutput.split("\n");
			if(testCase.isFind() && studentLines.length > 10) {
				flagged = true;
			}
			
			if(studentOutput.indexOf(testCase.getExpected()) != -1) {
				if(testCase.isFind())
					testCase.setPassing();
				else { // --print, make sure didn't go over capacity
					studentOutput = studentOutput.substring( studentOutput.indexOf(testCase.getExpected()) );
					int expectedNumLines = testCase.getExpected().split("\n").length;
					
					// probably replace with better logic in the future?
					// TODO: maybe also put into a helper function since printDetailsPrint uses similar logic
					if(studentLines.length <= expectedNumLines) {
						testCase.setPassing();
					} else { // more lines than expected lines, check first line after expected output
						String extraLine = studentLines[expectedNumLines];
						String[] extraLineWords = extraLine.strip().split(" ");
						if(extraLineWords.length > 0) {
							String firstWord = extraLineWords[0];
							if(firstWord.length() > 1) {
								if(!(firstWord.charAt(0) == 'E' && firstWord.charAt(0) == 'W') || !isNumber(firstWord.charAt(0)))
									testCase.setPassing();
							}
						}
					}
				}
			}
			if(!testCase.isPassing()) { doesPass = false; }
		}
		doesPass = doesPass && checkNoExceptionMain();
	}
	
	private boolean isNumber(char ch) {
		return '0' <= ch && ch <= '9';
	}
	
	protected void run() {
		checkStatus();
		header("STATUS", true);
		String notPassingMessage = "NOT PASSING!\nPlease make corrections and run again.";
		String status = (doesPass) ? "Passed :)" : notPassingMessage;
		print(status);
		
		if(!checkNoExceptionMain()) {
			header("MAIN METHOD EXCEPTION DECLARATION");
			print("The main method should be handling exceptions, not throwing them.");
			print("Please make sure your main method does not declare that it throws exceptions.");
		}
		
		if(!doesPass) {
			printDetails();
		}
		
		header("NOTES");
		detailIndex = 1;
		String about = "This Verify checks for the sample output given in example-output folder."
						+ "You should submit work that passes the UNMODIFIED version of Verify files.";
		print(about);
		if(flagged) {
			detail("Your at least one of your --find output seems to have printed more than 10 lines.");
			print("\tYour output will be flagged for manual review to ensure you didn't print all possible positions.");
			print("\tIf your output just has a lot of extraneous print statements, you can ignore this message.");
		}
		
		header("STATUS, AGAIN");
		print(status);
	}
	
	protected void printDetails() {
		for(TestCase testCase : testCases) {
			if(!testCase.isPassing()) {
				detailIndex = 1;
				header("TESTCASE ARGUMENTS: " + joinString(testCase.getCmdLnArguments()));
				if(testCase.isFind())
					printDetailsFind(testCase);
				else
					printDetailsPrint(testCase);
			}
		}
	}
	
	public boolean getStatus() { return doesPass; }
	public boolean isFlagged() { return flagged; }
	
	private boolean checkCapitalization(String expected, String[] studentLines) {
		String[] expectedWords = expected.split("\\s+");
		for(String line : studentLines) {
			String[] lineWords = line.split("\\s+");
			boolean foundExpected = true;
			for(String word : expectedWords) {
				boolean foundWord = arrayContainsIgnoreCase(lineWords, word);
				if(!foundWord) {
					foundExpected = false;
					break;
				}
			}
			if(foundExpected)
				return true;
		}
		return false;
	}
	
	private boolean checkWhiteSpace(String expected, String[] studentLines) {
		String[] expectedWords = expected.split("\\s+");
		for(String line : studentLines) {
			String[] lineWords = line.split("\\s+");
			boolean foundExpected = true;
			for(String word : expectedWords) {
				boolean foundWord = arrayContains(lineWords, word);
				if(!foundWord) {
					foundExpected = false;
					break;
				}
			}
			if(foundExpected)
				return true;
		}
		return false;
	}
	
	/*
	 * formats:
	 * <studentName>'s position in <courseName> lottery is {E | W}<n>.
	 * <studentName> is not on the lottery for <courseName>.
	 */
	protected void printDetailsFind(TestCase testCase) {
		if(testCase.hasException()) {
			detail("Following exception occurred while running main: " + testCase.getException().getClass().getCanonicalName());
			print("\tPlease try running the command line arguments and debug with the stack trace.");
		} else {
			String studentOutput = getOutput(testCase.getCmdLnArguments());
//			boolean onLottery = (testCase.getExpected().contains("not")) ? false : true;
			String expectedOutput = testCase.getExpected();
			print("Did not find \"" + expectedOutput + "\".");
			print("Here are some possible reasons:");
			detail("Does running your program with above command line arguments print the expected output?");
			detail("Is the output on its own line?");
			detail("Do you have a puncutation error? (period, apostrophe, etc.)");
			String[] studentLines = studentOutput.split("\n");
			if(!checkWhiteSpace(expectedOutput, studentLines))
				detail("Check the whitespace (space, tabs, etc.) in your output.");
			if(!checkCapitalization(expectedOutput, studentLines)) {
				detail("Check the capitalization in your output.");
			}
			print("\nExpected output first, then your output:");
			print("\t\"" + expectedOutput + "\"");
			printStudentOutput(studentOutput);
		}
	}
	
	/*
	 * format:
	 * header
	 * 	<course-name> Lottery: <mode>
	 * lines
	 * 	{E | W}<n>. <student-initials> <class-year> <points>
	 */
	protected void printDetailsPrint(TestCase testCase) {
		if(testCase.hasException()) {
			detail("Following exception occurred while running main: " + testCase.getException().getClass().getCanonicalName());
			print("\tPlease try running the command line arguments and debug with the stack trace.");
		} else {
			String studentOutput = getOutput(testCase.getCmdLnArguments());
			String expectedOutput = testCase.getExpected();
			
			// look for header - TODO: wait this is the same logic as printDetailsFind... extract into a helper function?
			String[] expectedLines =expectedOutput.split("\n");
			String header = expectedLines[0];
			if(!studentOutput.contains(header)) {
				String[] studentLines = studentOutput.split("\n");
				print("Could not find the header: " + header);
				print("Here are some possible reasons:");
				detail("Does running your program with above command line arguments print the expected output?");
				detail("Do you have a puncutation error? (colon, etc.)");
				detail("Is the output on its own line?");
				if(checkWhiteSpace(header, studentLines))
					detail("Do you have extra whitespaces (spaces, tabs, etc.)?");
				if(checkCapitalization(header, studentLines))
					detail("Is there a capitalization error?");
			} else {
				print("Found header, checking the rest...");
				print("Here are some possible reasons:");
				String[] studentLines = studentOutput.substring(studentOutput.indexOf(header)).split("\n");
				
				// TODO: similar logic as a code block in checkStatus()... extract into a helper function?
				
				if(studentLines.length < expectedLines.length) {
					detail("There seem to be less lines than expected... did your list end too early?");
				} else if(studentLines.length >= expectedLines.length) {
					for(int index = 0; index < expectedLines.length; index++) {
						if(!studentLines[index].equals(expectedLines[index])) {
							detail("Mismatch between expected (first below) and your (second below) outputs:");
							print("\t\"" + expectedLines[index] + "\"");
							print("\t\"" + studentLines[index] + "\"");
						}
					}
					
					// if there's more lines, check the line after the end of the list
					if(studentLines.length > expectedLines.length) { 
						String extraLine = studentLines[expectedLines.length];
						String[] extraLineWords = extraLine.strip().split(" ");
						if(extraLineWords.length > 0) {
							String firstWord = extraLineWords[0];
							if(firstWord.length() > 0) {
								if(firstWord.charAt(0) == 'E' || firstWord.charAt(0) == 'W') {
									detail("Found a line that starts with E or W after when list should have ended.");
									print("\tDid your list terminate correctly?");
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void printStudentOutput(String studentOutput) { printStudentOutput(studentOutput.split("\n")); }
	private void printStudentOutput(String[] studentLines) {

		for(int index = 0; index < studentLines.length; index++) {
			String line = studentLines[index];
			if(index == 0)
				line = "\"" + line;
			if(index == studentLines.length-1)
				line = line + "\"";
			print("\t" + line);
		}
	}
	
	protected String joinString(String[] contents) {
		StringBuilder builder = new StringBuilder();
		for(String content : contents) {
			builder.append(content);
			builder.append(" ");
		}
		return builder.toString().strip();
	}
	
	
	protected boolean arrayContains(String[] array, String inquiry) {
		for(String candidate : array) {
			if(candidate.equals(inquiry))
				return true;
		}
		return false;
	}
	
	protected boolean arrayContainsIgnoreCase(String[] array, String inquiry) {
		for(String candidate : array) {
			if(candidate.equalsIgnoreCase(inquiry))
				return true;
		}
		return false;
	}
	
	protected String getOutput(String[] args) {
		// set up redirecting output
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		PrintStream studentOut = new PrintStream(outputBuffer);
		System.setOut(studentOut);
	 
		
		// run the student's Main
		try {
			// TODO: Main.main's exception catching doesn't work!!! :(((
			Main.main(args);
		} catch (Exception e) {
			mainException = e;	// save exception for later;
			return null;
		}
		
		// restore the original PrintStream
		System.out.flush();
		System.setOut(originalOut);
		
		// process buffer
		String studentOutput = outputBuffer.toString();
		if(studentOutput.indexOf('\r') != -1) // probably windows, remove \r
			studentOutput = studentOutput.replace("\r", "");
		
		return studentOutput.strip();
	}
	
	protected boolean checkNoExceptionMain() {
		try {
			Method mainMethod = Main.class.getMethod("main", String[].class);
			if(mainMethod.getGenericExceptionTypes().length != 0) {
				print("the Main method should not throw an exception!");
				return false;
			}
			return true;
		} catch (NoSuchMethodException e) {
			print("Could not find the \"main\" method with String array parameter in Main.java!");
		} catch (SecurityException e) {	// TODO: should look into what this method does...
//			print("\"main\" not declared public perhaps? Ran into " + e.getClass().getCanonicalName());
			e.printStackTrace();
		}
		return false;
	}
	
	// laziness won over bad practice again :(
	protected void print(String message) { System.out.println(message); }
	
	protected void detail(String msg) { detail(msg, 0); } 
	protected void detail(String msg, int numTabs) {
		StringBuilder builder = new StringBuilder();
		for(int num = 0; num < numTabs; num++) {
			builder.append('\t');
		}
		builder.append(detailIndex++);
		builder.append(".) ");
		builder.append(msg);
		print(builder.toString());
	}
	
	protected void header(String title) { header(title, false); }
	protected void header(String title, boolean isFirst) {
		if(!isFirst)
			print("\n\n");
		StringBuilder mainBuilder = new StringBuilder();
		mainBuilder.append("**** ");
		mainBuilder.append(title);//.toUpperCase());
		mainBuilder.append(" ****");
		String headerMiddle = mainBuilder.toString();
		StringBuilder edgeBuilder = new StringBuilder();
		for(int index = 0; index < headerMiddle.length(); index++) {
			edgeBuilder.append("*");
		}
		String headerEdge = edgeBuilder.toString();
		print(headerEdge);
		print(headerMiddle);
		print(headerEdge);
		print(""); // new line
	}
	
	public static void main(String[] args) { 
		System.out.println("This class is solely to help other Verify files work.");
		System.out.println("You do not need to run this class.");
//		VerifyHelper x = new VerifyHelper();
//		x.checkNoExceptionMain();
	}
}
