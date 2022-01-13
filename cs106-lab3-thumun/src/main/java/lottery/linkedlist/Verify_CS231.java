package lottery.linkedlist;

public class Verify_CS231 extends VerifyHelper {
	private String fileName;
	private String courseName;
	
	public static void main(String[] args) {
		VerifyHelper verifier = new Verify_CS231();
		verifier.run();
	}
	
	public Verify_CS231() {
		super();
		fileName = "lottery-csh231.csv";
		courseName = "CS231";
		addTestCase("CWL's position in CS231 lottery is E25.", getFindArg("CWL"));
		addTestCase("DWL is not on the lottery for CS231.", getFindArg("DWL"));
		addTestCase("CHO's position in CS231 lottery is E5.", getFindArg("CHO"));
		addTestCase("WMS is not on the lottery for CS231.", getFindArg("WMS"));
		
		String printOutput = "CS231 Lottery: all\r\n"
				+ "E1. NAV 3 8\r\n" + "E2. AJM 2 7\r\n" + "E3. ASF 2 7\r\n"
				+ "E4. BCJ 2 7\r\n" + "E5. CHO 2 7\r\n" + "E6. GMW 2 7\r\n"
				+ "E7. LEM 2 7\r\n" + "E8. LIL 3 7\r\n" + "E9. MSW 2 7\r\n"
				+ "E10. XBR 2 7\r\n" + "E11. ALS 3 6\r\n" + "E12. BMH 4 6\r\n"
				+ "E13. QLO 3 6\r\n" + "E14. AJM 2 5\r\n" + "E15. AME 4 5\r\n"
				+ "E16. AOW 1 5\r\n" + "E17. ATB 1 5\r\n" + "E18. BDE 1 5\r\n"
				+ "E19. BDF 1 5\r\n" + "E20. BMC 1 5\r\n" + "E21. CCP 1 5\r\n"
				+ "E22. CMC 2 5\r\n" + "E23. CVP 2 5\r\n" + "E24. CVS 2 5\r\n"
				+ "E25. CWL 2 5\r\n" + "E26. DAD 2 5\r\n" + "E27. DMP 3 5\r\n"
				+ "E28. EMW 2 5\r\n" + "E29. FLS 3 5\r\n" + "E30. GLA 2 5\r\n"
				+ "E31. GWM 2 5\r\n" + "E32. HJC 1 5\r\n" + "W1. JDEP 2 5\r\n"
				+ "W2. JPG 1 5\r\n" + "W3. LOL 1 5\r\n" + "W4. LWM 1 5\r\n"
				+ "W5. MIJ 2 5\r\n" + "W6. MOM 3 5\r\n" + "W7. MWL 1 5\r\n"
				+ "W8. PBN 1 5\r\n" + "W9. QPL 2 5\r\n" + "W10. RTA 2 5\r\n"
				+ "W11. SCZ 1 5\r\n" + "W12. SEE 1 5\r\n" + "W13. SLM 1 5\r\n"
				+ "W14. SLM 2 5\r\n" + "W15. SVM 1 5\r\n" + "W16. TEC 1 5\r\n"
				+ "W17. XOL 2 5\r\n" + "W18. YEZ 1 5\r\n" + "W19. YUN 1 5\r\n"
				+ "W20. ABC 2 4";
		addTestCase(printOutput.replace("\r", ""), fileName + " --print " + courseName + " all");
	}
	
	private String getFindArg(String initials) {
		return fileName + " --find " + initials + " " + courseName;
	}
}
