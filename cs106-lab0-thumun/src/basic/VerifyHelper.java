package basic;

import java.lang.reflect.*;

/*
 * DO NOT MAKE CHANGES TO THIS FILE to ensure your work is verified correctly.
 * If your code does not pass, then you should change your code instead of this file.
 * 
 * This class is the parent of checking the methods power, GCD, isPrime, round, and classYear.
 * 
 * It checks compatibility with the autograder. Specifically, it checks the existence of
 * a method with the given name, its signature and data types of the parameters and return values.
 * Students are expected to get a passing status when they run the Verify files before submitting.
 * 
 * The children of this class should call the constructor with its method name,
 * and set the appropriate parameter and return types.
 * 
 * If the method being checked is overloaded, then it only checks one of the methods. 
 */

public abstract class VerifyHelper extends VerifyHelperMethods {
	
	// member variables
	private String methodName;
	private Class<?>[][] paramTypes;		// each index n should be an array of compatible data types for the nth parameter
	private Class<?>[] returnType;			// compatible data types for the return type.
	protected boolean existMethod, isGoodModifier, hasEnoughParam, isGoodParam, isGoodReturn;
	
	// common number data types accessible from children classes
	protected final Class<?>[]
			NUMERIC = {int.class, double.class, long.class, float.class},
			WHOLE = {int.class, long.class},
			DECIMAL = {double.class, float.class};
	
	// constructor
	public VerifyHelper(String methodName) {
		this.methodName = methodName;
		paramTypes = null;
		returnType = null;
		existMethod = isGoodModifier = hasEnoughParam = isGoodParam = isGoodReturn = false;
	}
	
	// setter for parameter types and return type
	protected void setCompatibleTypes(Class<?>[][] compatibleParams, Class<?>[] compatibleReturns) {
		paramTypes = compatibleParams;
		returnType = compatibleReturns;
	}
	
	/*
	 * Runs the verification for the method
	 * 
	 * Sets the boolean member variables for the method specified by methodName,
	 * and prints the result. This method should be called after setting compatible types.
	 */
	protected void run() {
		gatherInfo();
		printResult();
	}
	
	/*
	 * Checks method existence, signature, and data types, and sets the boolean member variables.
	 */
	protected Method gatherInfo() {
		for(Method method : Main.class.getDeclaredMethods()) {
			if(method.isBridge()) { continue; }					// skip under-the-hood generated method
			
			// check if method name is one given
			if(method.getName().equals(methodName)) {
				existMethod = true;
			
				// check public and static modifiers of method
				int modifiers = method.getModifiers();
				if(Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers))
					isGoodModifier = true;
				
				// check parameter number and data type
				if(paramTypes != null) {
					Class<?>[] methodParamTypes = method.getParameterTypes();
					if(methodParamTypes.length == paramTypes.length) {
						boolean[] foundParam = new boolean[methodParamTypes.length];
						hasEnoughParam = true;
						for(int index = 0; index < paramTypes.length; index++) {
							Class<?> methodParamType = methodParamTypes[index];
							// check if the student's parameter type is in the array of compatible types
							for(Class<?> cls : paramTypes[index]) {
								if(cls.equals(methodParamType)) {
									foundParam[index] = true;
									break;
								}
							}
						}
						// make sure all of the parameters are good
						isGoodParam = true;
						for(boolean val : foundParam) {
							if(!val) {
								isGoodParam = false;
								break;
							}
						}
					}
				}
				
				// check if student's return type is in the array of compatible types
				Class<?> methodReturnType = method.getReturnType();
				for(Class<?> cls : returnType) {
					if (methodReturnType.equals(cls)) {
						isGoodReturn = true;
						break;
					}
				}
				
				return method;		// end if the wanted method is checked
			}
		}
		return null;
	}
	
	/*
	 * Prints the status, details (if not passing), and disclaimer
	 */
	private void printResult() {
		// Give status at the top
		header("status", true);
		if(existMethod && isGoodModifier && hasEnoughParam && isGoodParam && isGoodReturn) {
			print("Passed :)");
		} else {
			print("NOT PASSING!");
			
			// details about possible causes of not passing 
			header("details");
			int count = 1;
			if(!existMethod) {
				detail(count++, "The \"" + methodName + "\" method was not found. Is it spelled correctly?");
			} else {
				if(!isGoodModifier)
					detail(count++, "Make sure your your methods are declared public AND static.");
				if(!hasEnoughParam)
					detail(count++, "Do you have the exact number of parameters specified in the instructions?");
				if(!isGoodParam)
					detail(count++, "Check the data type of your parameter(s).\n\tAlso, where applicable, use the primitives (e.g. int) and not the wrapper class (e.g. Integer).");
				if(!isGoodReturn)
					detail(count++, "Check your return data type!");
			}
		}
		
		// disclaimer about the verify
		header("FYI");
		String disclaimer = "This class checks for the signature of the method \"" + methodName + "\""
							+ "\n- specifically the name of the method, modifiers, return type, and parameters."
							+ "\nThis does not mean it necessarily checks for the most appropriate types."
							+ "\n- it only checks for types that are compatible with the autograder."
							+ "\n- you should think about and choose the most appropriate data type."
							+ "\nAlso does not check for correctness. Make sure your work is tested and error-free!";
		print(disclaimer);
	}
}
