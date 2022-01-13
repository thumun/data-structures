/**
* Lab 0 for CS106 - Java Introduction
*
* This program runs different methods as specified by the lab instructions.
* The purpose of each method is summarized in commented lines above each method.
*
* @author: Neha Thumu
* @version: February 23, 2021
*/


package basic;

public class Main {

	public static void main(String[] args) {		
		//System.out.println("Welcome to CS 106!  Enjoy Lab 0 Neha!");
		
		System.out.println("power(2, 3): " + power(2, 3));
		System.out.println("power(2, 0): " + power(2, 0));
		System.out.println();
		
		System.out.println("GCD(24, 18): " + GCD(24, 18));
		System.out.println("GCD(5, 2): " + GCD(5, 2));
		System.out.println();
		
		System.out.println("isPrime(149): " + isPrime(149)); // should output true
		System.out.println("isPrime(24): " + isPrime(24)); // should output false
		System.out.println("isPrime(0): " + isPrime(0)); // should output false 
		System.out.println();
		
		System.out.println("round(2.3): " + round(2.3)); // should output 2
		System.out.println("round(2.7): " + round(2.7)); // should output 3
		System.out.println();
		
		System.out.println("classYear(Standing.SENIOR): " + classYear(Standing.SENIOR));
		System.out.println("classYear(Standing.JUNIOR): " + classYear(Standing.JUNIOR));
		System.out.println("classYear(Standing.SOPHOMORE): " + classYear(Standing.SOPHOMORE));
		System.out.println("classYear(Standing.FIRST_YEAR): " + classYear(Standing.FIRST_YEAR));

	}
	
	/** 
	 * Takes the base, x, and the exponent, exp, to compute x^exp
	 * @param x (the base)
	 * @param exp (the exponent) 
	 * @return output (x raised to exp)
	 */
	public static int power (int x, int exp) { 
		if (exp != 0) {
			int output = 1; // the return variable 
			
			for (int i = 1; i <= exp; i++) { // x multiplied to itself 'exp' number of times
				output = output*x; 
			}
			
			return output; 
		}
		else {
			return 1;  // if exp = 0 then x ^ 0 = 1
		}
	}
	
	/** 
	 * Finds GCD by dividing the bigger number by the smaller until one is zero 
	 * @param a (first number input for comparison)
	 * @param b (second number input for comparison) 
	 * @return the new value of a/b (the GCD)
	 */
	public static int GCD (int a, int b) { 
		while (a != 0 && b != 0) {
			if (a > b) {
				a = a % b; // setting a as the remainder of original val of a divided by b
			}
			else {
				b = b % a; // setting b as the remainder of original val of b divided by a
			}
		}
		
		if (a == 0) {
			return b; 
		}
		else {
			return a; 
		}
	}
	
	/** 
	 * Checks if number is prime by dividing it by preceding numbers and seeing if divisible
	 * @param num
	 * @return true or false depending on if input is prime
	 */
	public static boolean isPrime (int num) {
		if (num <= 1) { // makes sure input is nonnegative and not 1 (since 1 is not prime) 
			return false; 
		}
		
		// checking only half b/c there wouldn't be factors beyond half; min factor is 2
		for (int i = 2; i <= num / 2; i++) { 
			if (num % i == 0) {
				return false; 
			}
		}
		
		return true;
	}

	/** 
	 * Rounds to the nearest integer 
	 * @param input
	 * @return rounded version of input 
	 */
	public static int round (double input) { 
		input = input + 0.5; // adding 0.5 to set up number for rounding
		return (int) input;
	}
	
	public enum Standing { 
		FIRST_YEAR, SOPHOMORE, JUNIOR, SENIOR
	}
	
	/**
	 * Returns info about class year as a string
	 * @param standing (class year as a word)
	 * @return class year depending on input 
	 */
	public static String classYear (Standing standing) { 
		String keyPhrase = "Class of 20"; // common phrase for all cases of output
		
		switch (standing) { // goes through each case and updates keyPhrase depending on case
			case FIRST_YEAR:
				keyPhrase = keyPhrase + "24";
				break;
			case SOPHOMORE:
				keyPhrase = keyPhrase + "23";
				break;
			case JUNIOR: 
				keyPhrase = keyPhrase + "22";
				break;
			case SENIOR:
				keyPhrase = keyPhrase + "21";
				break;
		}
		
		return keyPhrase; 
	}
	
}
