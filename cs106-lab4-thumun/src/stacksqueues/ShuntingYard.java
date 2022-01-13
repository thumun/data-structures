package stacksqueues;

import java.util.Scanner;

/**
 * Implements the shunting yard algorithm. See
 * http://en.wikipedia.org/wiki/Shunting-yard_algorithm
 */
public class ShuntingYard {

	/**
	 * turns a prefix equation into a postfix one
	 * 
	 * @param Scanner input (written in prefix terms
	 * @return String output (the equation written in postfix terms)
	 */
	public static String infix(Scanner input) {
		LinkedStack<String> operators = new LinkedStack<String>();
		ArrayDeque<String> expression = new ArrayDeque<String>();

		while (input.hasNext()) {
			if (input.hasNextDouble()) { // adding numbers to deque
				expression.addLast(String.valueOf(input.nextDouble()));

			}

			else if (input.hasNext(Postfix.OPERATOR)) { // checking operators
				String operator = input.next(Postfix.OPERATOR);

				if (operator.equals("(")) {
					operators.push(operator);
				} else if (operator.equals(")")) {
					// adding operators to deque until parenthesis
					while (!operators.isEmpty()
							&& !operators.peek().equals("(")) {
						expression.addLast(operators.pop());
					}
					// to get rid of the parenthesis
					operators.pop();
				} else {
					// adding operators based on PEMDAS
					while (!operators.isEmpty() && getPrecedence(
							operator) <= getPrecedence(operators.peek())) {
						expression.addLast(operators.pop());
					}
					operators.push(operator);
				}

			}

		}

		// adding any remaining operators in stack
		while (!operators.isEmpty()) {
			expression.addLast(operators.pop());
		}

		// converting to a string so readable with postfix function
		return changeString(expression);

	}

	/**
	 * comparing operators through their values
	 * 
	 * @param linked stack of strings (operators)
	 * @return boolean based on if the first operator is >= the second one
	 */
	public static boolean compare(LinkedStack<String> operators) {
		String first = operators.pop();
		String second = operators.pop();
		boolean output = false;

		if (getPrecedence(first) >= getPrecedence(second)) {
			output = true;
		}

		operators.push(second);
		operators.push(first);

		return output;

	}

	/**
	 * easy way to compare operators by assigning value to them (based on
	 * PEMDAS)
	 * 
	 * @param string operator
	 * @return integer based on their "value"
	 */
	public static int getPrecedence(String operator) {
		if (operator.equals("+") || operator.equals("-")) {
			return 0;
		} else if (operator.equals("*") || operator.equals("/")) {
			return 1;
		} else if (operator.equals("^")) {
			return 2;
		} else {
			return -1;
		}
	}

	/**
	 * converting the converted postfix equation to string (so it's readable)
	 * 
	 * @param deque of strings
	 * @return String output (the equation written in postfix terms)
	 */
	public static String changeString(ArrayDeque<String> expression) {
		String output = "";

		while (!expression.isEmpty()) {
			output += expression.removeFirst() + " ";
		}

		return output;
	}

}
