package propublica.datadesign;
import java.util.ArrayList;

/** 
 * This class is used to represent the objects of the Person class. As such, is named People.
 * 
 * There are methods to organize data into Person objects. 
 * 
 * There are also methods that help analyze the bias at play in predicting crime. 
 */

public class People {
	ArrayList<Person> people;
	
	public People() {
		this.people  = new ArrayList<Person>();
	}
	
	
	
    /** 
	 * converts an array of strings (data from table) into objects of class Person
	 * @param ArrayList<String []> dataRow
	 * @return N/A
	 */
	public void addStringArray(ArrayList<String []> dataRow) throws Exception{
		for (int row = 0; row < dataRow.size(); row++) {
			try {
				Person Temp = new Person(dataRow.get(row)); 
				people.add(Temp);
			}
			catch (Exception e) {
				System.out.println("This row is invalid so skipping");
			}
		}
	}
	
    /** 
	 * iterating through the Person objects and printing data line by line
	 * @param N/A
	 * @return N/A
	 */
	@Override
	public String toString() { 
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < people.size(); i++) { 
			output.append(people.get(i).toString() + "\n");
		}
		return output.toString();
	}

    /** 
	 * get the array list called people
	 * @param N/A
	 * @return N/A
	 */
	public ArrayList<Person> getPeople () {
		return people;
	}
	
	
    /** 
	 * Calculate the percentage of HIGH RISK white defendants who didn't re-offend
	 * @param N/A
	 * @return the number of HIGH RISK white defendants who didn't re-offend 
	 * divided by the number of ALL white defendants who didn't re-offend
	 */
	public double percentageWhiteHighNoRe() {
		double whiteNoRe = 0;
		double whiteHighNoRe = 0;
		//check all person in csv file
    	for(int i = 0; i < people.size(); i++) {
    		if(people.get(i).isWhite()) {
    			if(!people.get(i).hasReoffended()) {
    				whiteNoRe++;
    				if(people.get(i).isHighRisk()) {
    					whiteHighNoRe++;
    				}
    			}
    		}
    	}
    	return (double) (whiteHighNoRe/whiteNoRe);   	
    }	
    
    /** 
	 * Calculate the percentage of HIGH RISK black defendants who didn't re-offend
	 * @param N/A
	 * @return the number of HIGH RISK black defendants who didn't re-offend 
	 * divided by the number of ALL black defendants who didn't re-offend
	 */
	public double percentageBlackHighNoRe() {
		double blackNoRe = 0;
		double blackHighNoRe = 0;
		//check all person in csv file
    	for(int i = 0; i < people.size(); i++) {
    		if(people.get(i).isBlack()) {
    			if(!people.get(i).hasReoffended()) {
    				blackNoRe++;
    				if(people.get(i).isHighRisk()) {
    					blackHighNoRe++;
    				}
    			}
    		}
    	}
    	return (double)(blackHighNoRe/blackNoRe);   	
    }	

    /** 
	 * Calculate the percentage of LOW RISK white defendants who did re-offend
	 * @param N/A
	 * @return the number of LOW RISK white defendants who did re-offend 
	 * divided by the number of ALL white defendants who did re-offend
	 */
	public double percentageWhiteLowRe() {
		double whiteRe = 0;
		double whiteLowRe = 0;
		//check all person in csv file
    	for(int i = 0; i < people.size(); i++) {
    		if(people.get(i).isWhite()) {
    			if(people.get(i).hasReoffended()) {
    				whiteRe++;
    				if(people.get(i).isLowRisk()) {
    					whiteLowRe++;
    				}
    			}
    		}
    	}
    	return (double)(whiteLowRe/whiteRe);   	
    }	
    
    /** 
	 * Calculate the percentage of LOW RISK black defendants who did re-offend
	 * @param N/A
	 * @return the number of LOW RISK black defendants who did re-offend 
	 * divided by the number of ALL black defendants who did re-offend
	 */
	public double percentageBlackLowRe() {
		double blackRe = 0;
		double blackLowRe = 0;
		//check all person in csv file
    	for(int i = 0; i < people.size(); i++) {
    		if(people.get(i).isBlack()) {
    			if(people.get(i).hasReoffended()) {
    				blackRe++;
    				if(people.get(i).isLowRisk()) {
    					blackLowRe++;
    				}
    			}
    		}
    	}
    	return (double)(blackLowRe/blackRe);   	
    }
	
	
	
	/** 
	 * Calculate the percentage of HIGH RISK white defendants who didn't re-offend, 
	 * also add person who did minor crime
	 * @param N/A
	 * @return the number of HIGH RISK white defendants who didn't re-offend 
	 * divided by the number of ALL white defendants who didn't re-offend
	 */
	public double newPercentageWhiteHighNoRe() {
		double whiteNoRe = 0;
		double whiteHighNoRe = 0;
		//check all person in csv file
    	for(int i = 0; i < people.size(); i++) {
    		if(people.get(i).isWhite()) {
    			if(!people.get(i).hasReoffended()||
    					people.get(i).rChargeDegree.equals(Person.RChargeDegree.M1) || 
						people.get(i).rChargeDegree.equals(Person.RChargeDegree.M3) ||
						people.get(i).rChargeDegree.equals(Person.RChargeDegree.MO3)||
						people.get(i).rChargeDegree.equals(Person.RChargeDegree.CO3)) {
    				whiteNoRe++;
    				if(people.get(i).isHighRisk()) {
    					whiteHighNoRe++;
    				}
    			}
    		}
    	}
    	return (double) (whiteHighNoRe/whiteNoRe);   	
    }	
    
    /** 
	 * Calculate the percentage of HIGH RISK black defendants who didn't re-offend, 
	 * also add person who did minor crime
	 * @param N/A
	 * @return the number of HIGH RISK black defendants who didn't re-offend 
	 * divided by the number of ALL black defendants who didn't re-offend
	 */
	public double newPercentageBlackHighNoRe() {
		double blackNoRe = 0;
		double blackHighNoRe = 0;
		//check all person in csv file
    	for(int i = 0; i < people.size(); i++) {
    		if(people.get(i).isBlack()) {
    			if(!people.get(i).hasReoffended()||
    					people.get(i).rChargeDegree.equals(Person.RChargeDegree.M1) || 
						people.get(i).rChargeDegree.equals(Person.RChargeDegree.M3) ||
						people.get(i).rChargeDegree.equals(Person.RChargeDegree.MO3)||
						people.get(i).rChargeDegree.equals(Person.RChargeDegree.CO3)) {
    				blackNoRe++;
    				if(people.get(i).isHighRisk()) {
    					blackHighNoRe++;
    				}
    			}
    		}
    	}
    	return (double)(blackHighNoRe/blackNoRe);   	
    }	
	
	

    /** 
	 * Calculate the percentage of LOW RISK white defendants who did re-offend, 
	 * but didn't count minor crime
	 * @param N/A
	 * @return the number of LOW RISK white defendants who did re-offend 
	 * divided by the number of ALL white defendants who did re-offend
	 */
	public double newPercentageWhiteLowRe() {
		double whiteRe = 0;
		double whiteLowRe = 0;
		//check all person in csv file
    	for(int i = 0; i < people.size(); i++) {
    		if(people.get(i).isWhite()) {
    			if(people.get(i).hasReoffended()) {
    				whiteRe++;
    				if(people.get(i).isLowRisk()) {
    					if (!people.get(i).rChargeDegree.equals(Person.RChargeDegree.M1) && 
    						!people.get(i).rChargeDegree.equals(Person.RChargeDegree.M3) &&
    						!people.get(i).rChargeDegree.equals(Person.RChargeDegree.MO3)&&
    						!people.get(i).rChargeDegree.equals(Person.RChargeDegree.CO3)){
    					whiteLowRe++;
    					}
    				}
    			}
    		}
    	}
    	return (double)(whiteLowRe/whiteRe);   	
    }	
    
    /** 
	 * Calculate the percentage of LOW RISK black defendants who did re-offend, 
	 * but didn't count minor crime
	 * @param N/A
	 * @return the number of LOW RISK black defendants who did re-offend 
	 * divided by the number of ALL black defendants who did re-offend
	 */
	public double newPercentageBlackLowRe() {
		double blackRe = 0;
		double blackLowRe = 0;
		//check all person in csv file
    	for(int i = 0; i < people.size(); i++) {
    		if(people.get(i).isBlack()) {
    			if(people.get(i).hasReoffended()) {
    				blackRe++;
    				if(people.get(i).isLowRisk()) {
    					if (!people.get(i).rChargeDegree.equals(Person.RChargeDegree.M1) && 
    						!people.get(i).rChargeDegree.equals(Person.RChargeDegree.M3) &&
    						!people.get(i).rChargeDegree.equals(Person.RChargeDegree.MO3)&&
    						!people.get(i).rChargeDegree.equals(Person.RChargeDegree.CO3)){
    					blackLowRe++;
    					}
    				}
    			}
    		}
    	}
    	return (double)(blackLowRe/blackRe);   	
    }
	
}
