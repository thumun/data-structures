package deduplication.sqf;

/**
 * Main method where we can pass in voter data files to deduplicate them. Additionally, we perform tests on our deduplication methods below
 * using different voter data files. 
 *
 * @author Avi Patel, Neha Thumu
 * @version May 12, 2021
 */

public class Main {

	/** main method */
	public static void main(String[] args) {

		VoterData v = new VoterData("vote_files/" + args[0]);

		v.hashLinearDeduplication();

		System.out.println("Records given:" + v.getVoterRows().size());
		System.out.println(
				"Attributes checked:" + "LAST_NAME" + "," + "FIRST_NAME");
		System.out.println("Duplicates found:" + (v.getVoterRows().size()
				- v.hashLinearDeduplication().size()));

		System.out.println();
		
		// Our own testing:

		// changing this to different files to test deduplication methods
		VoterData test1 = new VoterData("vote_files/SWVF_45_66_short.txt");
		compareDeduplicationMethods(test1);
	
	}

	/**
	 * Making sure that the different deduplication methods are all working. By
	 * making sure that each method removes the same number of duplicates.
	 * 
	 * @param v: VoterData we are testing deduplication methods on
	 * @return N/A
	 */
	public static void compareDeduplicationMethods(VoterData v) {

		System.out.println("Initial Size: " + v.getVoterRows().size());
		System.out.println("Final Size: " + v.hashLinearDeduplication().size());

		System.out.println();
		System.out.println("Initial Size: " + v.getVoterRows().size());
		System.out.println("Final Size: " + v.allPairsDeduplication().size());

		System.out.println();
		System.out.println("Initial Size: " + v.getVoterRows().size());
		v.quickSort(0, v.getVoterRows().size() - 1);
		System.out.println("Final Size: " + v.quicksortDeduplication().size());
	}

}
