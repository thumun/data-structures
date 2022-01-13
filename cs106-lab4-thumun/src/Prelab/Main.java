package Prelab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	// the phrase to token-ize and traverse
	static final String phrase = "2.0 true hello world ! :) 12 1 super false17 clap false 1.1";

	public static void main(String[] args) {
		// ArrayLists to store the tokens of respective type
		ArrayList<Boolean> bools = new ArrayList<Boolean>();
		ArrayList<Double> doubles = new ArrayList<Double>();
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ArrayList<String> strings = new ArrayList<String>();

		// show you a message about what tokens might look like
		System.out.println("Tokens for our scanner might look like "
				+ Arrays.toString(phrase.split(" ")));

		// create a scanner using the phrase, delimited by a space
		Scanner scanner = new Scanner(phrase).useDelimiter(" ");

		// loop through the scanner while there are tokens left
		while (scanner.hasNext()) {
			// TODO: implement tasks described in pre-lab!
			System.out.println("The next token is " + scanner.next());
			// TODO: delete
			// this
			// line
			// and
			// implement
			// your
			// work!
		}

		scanner.close();

		// print each list
		System.out.println("The boolean list is: " + bools.toString());
		System.out.println("The integer list is: " + ints.toString());
		System.out.println("The double list is: " + doubles.toString());
		System.out.println("The string list is: " + strings.toString());
	}
}
