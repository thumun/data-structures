/*
 * * DO NOT MAKE CHANGES TO THIS FILE (press '+' on the line number to see the entire message)
 * Making changes to this file may not properly allow you to check your format for the autograder.
 * 
 * This class checks if your format is as specified in the lab instructions.
 * You should run this class before you commit/push your work to ensure your work is gradable.
 * 
 */

package stacksqueues;

import java.util.EmptyStackException;

public class VerifyFormat_LinkedStack {

	public static void main(String[] args) {
		
		System.out.println("=====================\nChecking LinkedStack \n=====================\n");
		try {
			Stack<Integer> stackTest = new LinkedStack<Integer>();
			stackTest.push(5);
			stackTest.push(2);
			stackTest.push(3);
			checkOutputFormat(stackTest.toString(), "(5, 2, 3)");
			System.out.println("\n***************End of First Check***************\n");
			stackTest.pop();
			stackTest.pop();
			stackTest.push(0);
			stackTest.push(9);
			stackTest.push(-6);
			stackTest.push(14);
			checkOutputFormat(stackTest.toString(), "(5, 0, 9, -6, 14)");
			System.out.println("\n***************End of Second Check***************\n");
		} catch(EmptyStackException e) {
			System.out.println("EmptyStackException was thrown... It should not have been thrown!");
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("An exception was thrown: " + e.getClass().getCanonicalName());
			e.printStackTrace();
		}
	}
	
	public static void checkOutputFormat(String studentStr, String correctAns) {
		System.out.print("Expected:\t");
		System.out.println("\"" + correctAns + "\"");
		System.out.print("Your out:\t");
		System.out.println("\"" + studentStr+ "\"");
		if(correctAns.equals(studentStr)) {
			System.out.println("Your toString() format seems correct!");
		} else {
			System.out.println("Compare the expected output with your output,"
								+ " make corrections, and try again.");
			
			char[] charactersToCheck = new char[] {',', ' ', '(', ')'};
			for(Character character : charactersToCheck)
				checkCharacter(character, studentStr);
			
			int myLength = correctAns.split(",").length;
			int yourLength = studentStr.split(",").length;
			if(myLength != yourLength) {
				System.out.println("\tIs the number of elements correct?");
				String direction = (yourLength > myLength) ? "more" : "less";
				System.out.println("\t\tYou seem to have " + direction + " items.");
			}
		}
	}
	
	public static void checkCharacter(char character, String output) { 
		if(output.indexOf(character) == -1) {
			System.out.println("\tYour output seems to be missing the character '" + character + "'");
			System.out.println("\t\tPlease fix this error and try again.");
		}
	}
}
