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

public class VerifyFormat_ArrayDeque {
	
	public static void main(String[] args) {
		try
		{
			System.out.println("\n==============\nChecking Deque\n==============\n");
			Deque<String> dequeStr = new ArrayDeque<String>(9);
			dequeStr.addFirst("the");
			dequeStr.addFirst("quick");
			dequeStr.addLast("brown");
			dequeStr.addLast("fox");
			dequeStr.addFirst("jumps");
			checkOutputFormat(dequeStr.toString(), "(jumps, quick, the, brown, fox)");
			System.out.println("\n***************End of Third Check***************\n");
			dequeStr.addLast("over");
			dequeStr.addLast("the");
			dequeStr.addFirst("lazy");
			dequeStr.addLast("dog");
			checkOutputFormat(dequeStr.toString(), "(lazy, jumps, quick, the, brown, fox, over, the, dog)");
			System.out.println("\n***************End of Fourth Check***************\n");
		}
		catch(FullQueueException e) {
			System.out.println("FullQueueException was thrown... It should not have been thrown!");
			e.printStackTrace();
		} catch(EmptyQueueException e) {
			System.out.println("EmptyQueueException was thrown... It should not have been thrown!");
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
