package stacksqueues;

import java.io.StringReader;
import java.util.Scanner;

/**
 * Starter code for computing arithmetic expressions in post-fix notation.
 *
 * @author Nick Howe, Sara Mathieson, + Neha Thumu
 * @version TODO
 */
public class Postfix {

	/** Pattern that matches on words */
	public static final String WORD = "[a-zA-Z]*\\b";

	/** Pattern that matches on arithmetic operators */
	public static final String OPERATOR = "[^\\w]";

	/** Main method to evaluate expression */
	public static void main(String[] args) {

		// tell the user how to run the program
		if (args.length == 0) {
			System.err.println("Usage: java Postfix <expr>");

		} else {
			System.out.println("input: " + args[0]);
			Scanner input = new Scanner(new StringReader(args[0]));

			// Below is a complicated regular expression that will split the
			// input string at various boundaries.
			input.useDelimiter("(\\s+" // whitespace
					+ "|(?<=[a-zA-Z])(?=[^a-zA-Z])" // word->non-word
					+ "|(?<=[^a-zA-Z])(?=[a-zA-Z])" // non-word->word
					+ "|(?<=[^0-9\\056])(?=[0-9\\056])" // non-number->number
					+ "|(?<=[0-9\\056])(?=[^0-9\\056])" // number->non-number
					+ "|(?<=[^\\w])(?=[^\\w]))"); // symbol->symbol

			// infix testing below
			/*
			 * String infix = ShuntingYard.infix(input); Scanner forpostfix =
			 * new Scanner(new StringReader(infix));
			 * forpostfix.useDelimiter("(\\s+" // whitespace +
			 * "|(?<=[a-zA-Z])(?=[^a-zA-Z])" // word->non-word +
			 * "|(?<=[^a-zA-Z])(?=[a-zA-Z])" // non-word->word +
			 * "|(?<=[^0-9\\056])(?=[0-9\\056])" // non-number->number +
			 * "|(?<=[0-9\\056])(?=[^0-9\\056])" // number->non-number +
			 * "|(?<=[^\\w])(?=[^\\w]))"); // symbol->symbol
			 * 
			 */
			// double finalResult = postfix(forpostfix); // version to test
			// infix to postfix
			double finalResult = postfix(input); // version for testing postfix
			System.out.println("result: " + finalResult);

			testShuntingYard("1+2*3/(4-5)");
		}
	}

	public static void testShuntingYard(String arg) {
		System.out.println("testing ShuntingYard with input:" + arg);
		Scanner input = new Scanner(new StringReader(arg));

		// Below is a complicated regular expression that will split the
		// input string at various boundaries.
		input.useDelimiter("(\\s+" // whitespace
				+ "|(?<=[a-zA-Z])(?=[^a-zA-Z])" // word->non-word
				+ "|(?<=[^a-zA-Z])(?=[a-zA-Z])" // non-word->word
				+ "|(?<=[^0-9\\056])(?=[0-9\\056])" // non-number->number
				+ "|(?<=[0-9\\056])(?=[^0-9\\056])" // number->non-number
				+ "|(?<=[^\\w])(?=[^\\w]))"); // symbol->symbol

		String postfix = ShuntingYard.infix(input);
		Scanner scanner = new Scanner(new StringReader(postfix));
		double finalResult = postfix(scanner); // version for testing postfix
		System.out.println("result: " + finalResult);
	}

	public static void test(Scanner input) {
		while (input.hasNext()) {
			if (input.hasNextDouble()) {
				double num = input.nextDouble();

				System.out.println("Number: " + num);

			} else if (input.hasNext(WORD)) {
				System.out.println("Word: " + input.next(WORD));
			} else if (input.hasNext(OPERATOR)) {

				String operator = input.next(OPERATOR);

				System.out.println("Operator: " + operator);
			} else {
				System.out.println("Unknown: " + input.next());
			}
		}

	}

	/**
	 * solves an equation written in terms of a postfix
	 * 
	 * @param scanner input [the equation]
	 * @return the result of the equation as a double
	 */
	public static double postfix(Scanner input) {
		// loop to run through all the tokens of the input

		LinkedStack<Double> calculator = new LinkedStack<Double>();

		while (input.hasNext()) {
			if (input.hasNextDouble()) {
				double num = input.nextDouble();

				calculator.push(num);
				System.out.println("Number: " + num);

			} else if (input.hasNext(WORD)) {
				System.out.println("Word: " + input.next(WORD));
			} else if (input.hasNext(OPERATOR)) {
				// logic for using operators on operands
				Double second = calculator.pop();
				Double first = calculator.pop();
				String operator = input.next(OPERATOR);

				if (operator.equals("+")) {
					calculator.push(first.doubleValue() + second.doubleValue());
				} else if (operator.equals("-")) {
					calculator.push(first.doubleValue() - second.doubleValue());

				} else if (operator.equals("*")) {
					calculator.push(first.doubleValue() * second.doubleValue());

				} else if (operator.equals("/")) {
					calculator.push(first.doubleValue() / second.doubleValue());

				} else if (operator.equals("^")) {
					calculator.push(Math.pow(first.doubleValue(),
							second.doubleValue()));

				} else {
					return 0;
				}

				System.out.println("Operator: " + operator);
			} else {
				System.out.println("Unknown: " + input.next());
			}
		}
		return (double) calculator.pop();
	}
}
