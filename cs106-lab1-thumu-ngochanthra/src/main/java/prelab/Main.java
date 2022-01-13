package prelab;

import prelab.Student.classYear;
import prelab.Student.dean;
import prelab.Student.dorm;

/*
 * You should use this package for Pre-Lab 2 and Pre-Lab 3 Exercises.
 * Make sure you don't have errors in this package, since it will cause issues
 * with the autograder if there are any compilation issues.
 */

public class Main {

	public static void main(String[] args) {
		Student Will = new Student("Will", 20, classYear.SOPHOMORE, dorm.BARCLAY, true, dean.MICHAEL_ELIAS);
		System.out.println(Will.toString());
	}
}
