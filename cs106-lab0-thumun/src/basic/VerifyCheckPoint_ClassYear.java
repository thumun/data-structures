package basic;

import java.lang.reflect.*;
import java.util.ArrayList;

/*
 * DO NOT MAKE CHANGES TO THIS FILE to ensure your work is verified correctly.
 * If your code does not pass, then you should change your code instead of this file.
 * 
 * Students are not expected to understand this code.
 * However, they should submit work that gives a passing status after running this class.
 * 
 * This class checks for enum Standing and its values as specified in the instructions,
 * the existence and signature of the method "classYear",
 * and the output format of the method "classYear" in Main. 
 */

public class VerifyCheckPoint_ClassYear extends VerifyHelper {

	private Class<?>[][] compatibleParamTypes = null; // to be determined after finding enum
	private Class<?>[] compatibleReturnTypes = new Class<?>[] {String.class};
	
	// consturctor
	public VerifyCheckPoint_ClassYear() {
		super("classYear");
		setCompatibleTypes(compatibleParamTypes, compatibleReturnTypes);
	}
	
	public static void main(String[] args) {
		VerifyCheckPoint_ClassYear runner = new VerifyCheckPoint_ClassYear();
		runner.run();
	}
	
	/*
	 * prints out verification of the enum Standing, method classYear, and the output format.
	 * 
	 * This method checks whether the Standing enum exists, is public,
	 * and has the four required constant values as specified in the lab instructions.
	 * It also checks for the parameter, return type, and modifiers of the classYear method
	 * as well as its output if both Standing and classYear verifications pass.
	 * 
	 * It prints out the status with details about any errors, if any.
	 */
	@Override
	public void run() {
		// initially assume enum does not meet the criteria
		boolean existEnum, hasGoodModifiersEnum, hasEnoughEnum, hasConstants;
		existEnum = hasGoodModifiersEnum = hasEnoughEnum = hasConstants = false;
		// if there are more than 4 constants in the enum, format checking depends on the first declared. 
		int numConstants = 0;
		Object enumConstant = null; // argument for classYear to be passed with invoke() for format checking
		Method classYearMethod = null; // method to call invoke() for format checking
		
		// look for the Standing enum declared as a member of Main
		Class<?> standingEnum = null;
		for(Class<?> member : Main.class.getClasses()) {
			if(member.getCanonicalName().equals("basic.Main.Standing")) {
				standingEnum = member;
				break;
			}
		}
		
		// check the enum
		if(standingEnum != null && standingEnum.isEnum()) {
			existEnum = true;
			
			// set parameter type for gatherInfo()
			compatibleParamTypes = new Class<?>[][] {{standingEnum}};
			setCompatibleTypes(compatibleParamTypes, compatibleReturnTypes);
			
			// check if it's public. static does not have to be explicitly declared in Main?
			int modifiers = standingEnum.getModifiers();
			if(Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers))
				hasGoodModifiersEnum = true;
			
			// extract only the constants out of the enum
			Field[] constants = standingEnum.getFields();
			ArrayList<Field> enumConstants = new ArrayList<Field>();
			for(Field constant : constants) {
				if(constant.isEnumConstant())
					enumConstants.add(constant);
			}
			numConstants = enumConstants.size();
			
			// check the number and existence of constants
			if(numConstants >= 4) {
				hasEnoughEnum = true;
				boolean[] existConstants = new boolean[4]; // keep track of whether the constant was found
				String[] standingNames = new String[] {"FIRST_YEAR", "SOPHOMORE", "JUNIOR", "SENIOR"};
				for(Field constant : enumConstants) {
					for(int index = 0; index < standingNames.length; index++) {
						if(standingNames[index].equals(constant.getName())) {
							existConstants[index] = true;
							break;
						}
					}
				}
				hasConstants = true;
				// check if any of the constants were not found
				for(boolean existConstant : existConstants) {
					if(!existConstant) {
						hasConstants = false;
						break;
					}
				}
				
				// save the first constant to pass as an argument for invoke()
				enumConstant = standingEnum.getEnumConstants()[0];
			}
		}
		
		// check the method
		classYearMethod = gatherInfo();
		
		header("status", true);
		boolean isEnumGood = existEnum && hasGoodModifiersEnum && hasEnoughEnum && hasConstants;
		boolean isMethodGood = existMethod && isGoodModifier && hasEnoughParam && isGoodParam && isGoodReturn;
		
		// boolean values for checking output format pattern
		boolean isRightLength, hasPattern, matchesPattern;
		isRightLength = hasPattern = matchesPattern = false;
		
		// save exception while running code for detail
		Exception exceptionInvoked = null;
		
		// check output format
		if(isEnumGood && isMethodGood) {
			// try to get the output
			try {
				String format = "Class of 20XX";
				String result = (String) classYearMethod.invoke(null, enumConstant);
				if(format.length() == result.length())
					isRightLength = true;
				if(result.substring(0,11).equals("Class of 20")) {
					matchesPattern = true;
					hasPattern = true;
				} else if(result.indexOf("Class of 20") != -1)
					hasPattern = true;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// already checked access and arguments?
				// TODO: check if any of these exceptions can actually arise
				exceptionInvoked = e;
			}
		}
		
		// print status and details as necessary
		if(exceptionInvoked == null && isRightLength && matchesPattern) {
			print("Passed :)");
		} else {
			print("NOT PASSING!");
			header("details");
			int count = 1;
			// problems with Standing enum
			if(!isEnumGood) {
				if(!existEnum) {
					detail(count++, "Could not find \"Standing\" enum in Main.\n\tMake sure it's spelled correctly, is a member of Main class, and is declared public.");
				} else {
					if(!hasGoodModifiersEnum)
						detail(count++, "Make sure the enum is declared public and NOT static.");
					if(!hasEnoughEnum) 
						detail(count++, "Are there (at least) 4 constants in the Standing enum?");
					if(!hasConstants)
						detail(count++, "Do you have the constants FIRST_YEAR, SOPHOMORE, JUNIOR, and SENIOR in the enum?");
				}
			}
			// problems with classYear method
			if(!isMethodGood) {
				if(!existMethod)
					detail(count++, "The \"classYear\" method was not found. Is it spelled correctly?");
				else {
					if(!isGoodModifier)
						detail(count++, "Make sure your your methods are declared public AND static.");
					if(!hasEnoughParam)
						detail(count++, "Do you have the exact number of parameters specified in the instructions?");
					if(!isGoodParam)
						detail(count++, "Check the data type of your parameter(s).\n\tIt should be of type Standing (the enum).");
					if(!isGoodReturn)
						detail(count++, "Check your return data type!");
				}
			}
			// problems with output format
			if(isEnumGood && isMethodGood) {
				if(numConstants > 4) {
					String formatMessage = "If the first constant declared in the enum is"
											+ "\n\tnot one of the four specified in the instructions,"
											+ "\n\tformat checking may have a problem."
											+ "\n\tPlease make sure the first enum is one of"
											+ "\n\tthe following: FIRST_YEAR, SOPHOMORE, JUNIOR, SENIOR.";
					detail(count++, formatMessage);
				}
				if(exceptionInvoked != null) {
					detail(count++, "Following error occured while trying to run the method!");
					exceptionInvoked.printStackTrace();
				} else {
					if(!isRightLength)
						detail(count++, "The output seems to be too long or too short.\n\tTry comparing your output length with the length of \"Class of 20XX\"");
					if(!hasPattern)
						detail(count++, "Did not find \"Class of 20\" in the output.");
					if(!matchesPattern)
						detail(count++, "Is \"Class of 20\" the first " + ("Class of 2020".length()) + " characters of the output?");
				}
			} else
				detail(count++, "The format could not be checked due to issues with Standing enum or classYear method.\n\tPlease make the corrections according to the Verify output and try again.");
		}
		
		header("fyi");
		String disclaimer = "This class checks for the signature of the method \"classYear\""
				+ "\n\tspecifically the name of the method, modifiers, return type, and parameters."
				+ "\nThis does not mean it necessarily checks for the most appropriate types."
				+ "\n\tit only checks for types that are compatible with the autograder."
				+ "\n\tyou should think about and choose the most appropriate data type."
				+ "\n It also does not check for correctness; only the string format"
				+ "\n\ti.e. whether the first 11 characters are \"Class of 20\"."
				+ "\nYou are responsible for testing for correctness and ensuring it's error-free.";
		print(disclaimer);
	}
}
