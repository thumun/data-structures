package propublica.datadesign;

/**
 * DO NOT MAKE CHANGES TO THIS CLASS!
 * This class stores the false positive and false negative rates of recidivism prediction for white and black defendants
 * and provides a way to output the table via overriding the toString method.
 */

class PropublicaDataTable{
	
	private double falsePWhite;
	private double falsePBlack;
	private double falseFWhite;
	private double falseFBlack;
	
	//constructor
	public PropublicaDataTable(double HighNoReoffendWhite, double HighNoReoffendBlack, double LowRecidivateWhite, double LowRecidivateBlack) {
		falsePWhite = HighNoReoffendWhite;
		falsePBlack = HighNoReoffendBlack;
		falseFWhite = LowRecidivateWhite;
		falseFBlack = LowRecidivateBlack;
	}
	
	// getters
	public double getHighNoReoffendWhite() { return falsePWhite; }
	public double getHighNoReoffendBlack() { return falsePBlack; }
	public double getLowRecidivateWhite() { return falseFWhite; }
	public double getLowRecidivateBlack() { return falseFBlack; }
	
	private String formatPercent(double decimalInput) {
		return String.format("%.1f", decimalInput*100) + "%";
	}
	
	@Override
	public String toString() {
		return "" 
				+ "                                                ┌─────────────────────┬──────────────────────────┐\n"
				+ "                                                │        White        │     African-American     │\n"
				+ "┌───────────────────────────────────────────────┼─────────────────────┼──────────────────────────┤\n"
				+ "│    Didn't Re-Offend, but Labeled High Risk    │        " + formatPercent(getHighNoReoffendWhite()) + "        │           " + formatPercent(getHighNoReoffendBlack()) + "          │\n"
				+ "├───────────────────────────────────────────────┼─────────────────────┼──────────────────────────┤\n"
				+ "│    Did    Re-Offend, but Labeled Low  Risk    │        " + formatPercent(getLowRecidivateWhite()) + "        │           " + formatPercent(getLowRecidivateBlack()) + "          │\n"
				+ "└───────────────────────────────────────────────┴─────────────────────┴──────────────────────────┘\n" ;
	}
	
}