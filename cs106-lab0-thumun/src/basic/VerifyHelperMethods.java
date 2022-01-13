package basic;

/*
 * This class contains helper methods that assist generating the output.
 */

public abstract class VerifyHelperMethods {
	protected void print(String message) { System.out.println(message); }
	protected void header(String title) { header(title, false); }
	protected void header(String title, boolean first) {
		StringBuilder builder = new StringBuilder();
		if(!first)
			builder.append("\n\n");
		builder.append("**** ");
		builder.append(title.toUpperCase());
		builder.append(" ****\n");
		print(builder.toString());
	}
	protected void detail(int count, String msg) { print(count + ".) " + msg); }
}
