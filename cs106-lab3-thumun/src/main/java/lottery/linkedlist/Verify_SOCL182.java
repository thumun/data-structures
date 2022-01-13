package lottery.linkedlist;

public class Verify_SOCL182 extends VerifyHelper {
	private String fileName;
	private String courseName;
	
	public static void main(String[] args) {
		VerifyHelper verifier = new Verify_SOCL182();
		verifier.run();
	}
	
	public Verify_SOCL182() {
		super();
		fileName = "socl182-lottery.csv";
		courseName = "SOCL182";
		addTestCase("INT's position in SOCL182 lottery is E1.", getFindArg("INT"));
		addTestCase("CHR is not on the lottery for SOCL182.", getFindArg("CHR"));
		addTestCase("RDC's position in SOCL182 lottery is W3.", getFindArg("RDC"));
		addTestCase("ICK's position in SOCL182 lottery is W5.", getFindArg("ICK"));
		
		String printOutput = "SOCL182 Lottery: all\r\n"
				+ "E1. INT 3 26\r\n"
				+ "E2. JEG 3 23\r\n"
				+ "E3. RFO 1 20\r\n"
				+ "W1. WSP 1 20\r\n"
				+ "W2. ZBR 1 20\r\n"
				+ "W3. RDC 1 17\r\n"
				+ "W4. BZN 2 12\r\n"
				+ "W5. ICK 4 11\r\n"
				+ "W6. GES 4 8\r\n"
				+ "W7. WEB 4 8";
		addTestCase(printOutput.replace("\r", ""), fileName + " --print " + courseName + " all");
	}
	
	private String getFindArg(String initials) {
		return fileName + " --find " + initials + " " + courseName;
	}
}
