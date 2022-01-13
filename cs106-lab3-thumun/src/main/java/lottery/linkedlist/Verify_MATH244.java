package lottery.linkedlist;

public class Verify_MATH244 extends VerifyHelper {
	private String fileName;
	private String courseName;
	
	public static void main(String[] args) {
		VerifyHelper verifier = new Verify_MATH244();
		verifier.run();
	}
	
	public Verify_MATH244() {
		super();
		fileName = "math244-lottery.csv";
		courseName = "MATH244";
		addTestCase("BHB's position in MATH244 lottery is W1.", getFindArg("BHB"));
		addTestCase("UUC is not on the lottery for MATH244.", getFindArg("UUC"));
		addTestCase("ZZZ is not on the lottery for MATH244.", getFindArg("ZZZ"));
		addTestCase("TLI's position in MATH244 lottery is E4.", getFindArg("TLI"));
		
		String printOutput = "MATH244 Lottery: all\r\n"
				+ "E1. NLV 3 39\r\n"
				+ "E2. GCE 4 37\r\n"
				+ "E3. OEU 4 37\r\n"
				+ "E4. TLI 4 37\r\n"
				+ "W1. BHB 3 36\r\n"
				+ "W2. CWP 3 36\r\n"
				+ "W3. INO 3 36\r\n"
				+ "W4. CNG 4 34\r\n"
				+ "W5. IDN 4 34\r\n"
				+ "W6. CJS 3 31";
		addTestCase(printOutput.replace("\r", ""), fileName + " --print " + courseName + " all");
	}
	
	private String getFindArg(String initials) {
		return fileName + " --find " + initials + " " + courseName;
	}
}
