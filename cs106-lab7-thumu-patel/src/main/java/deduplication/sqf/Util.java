package deduplication.sqf;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReaderHeaderAware;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.LinePlot;

/**
 * Lab 7 for CS106
 *
 * This file's contains the pre-lab/part 1. Which calculates the runtime for
 * fib(n) and plots this.
 *
 * This file also calculates the runtime for our three deduplication methods
 * (quickSort, hash linear, and all pairs). Then it plots these methods in order
 * to compare them. The rows are the x-axis and the time taken is on the y-axis.
 * 
 *
 * @author:  Avi Patel, Neha Thumu
 * @version: May 12, 2021
 */

public class Util {
	public static void main(String[] args) {

		// Part 1: Pre-lab runtime warmup:
		fibRunTime();

		// Part 3:
		deduplicationTests();
	}

	/**
	 * Pre-lab. Testing the runtime of fib(n)
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public static void fibRunTime() {
		int n = 40;

		int[] xvals = new int[n];
		double[] yvals = new double[n];

		for (int i = 0; i < n; i++) {
			long start = startTimer();
			int result = fib(i);
			long end = endTimer();
			long timeSec = secondsElapsed(start, end);

			// convert from ns to s
			double elapsedTimeSec = (double) timeSec / 1000000000;

			xvals[i] = i;
			yvals[i] = elapsedTimeSec;
		}

		DoubleColumn column1 = DoubleColumn.create("n", xvals);
		DoubleColumn column2 = DoubleColumn.create("time (seconds)", yvals);

		Table table = Table.create("for plot");
		table.addColumns(column1, column2);
		Plot.show(LinePlot.create("runtime vs n for fib recursive method",
				table, "n", "time (seconds)"));
	}

	/**
	 * run-time analysis of deduplication methods: All Pairs, Hash linear, and
	 * QuickSort
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public static void deduplicationTests() {

		try {
			CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(
					new FileReader("vote_files/SWVF_1_22_med.txt"));
			ArrayList<String[]> voterEntries = new ArrayList<String[]>(
					csvReader.readAll());
			csvReader.close();
			System.out.println(voterEntries.size());

			int max = 100000; // maximum number of rows plotted
			int step = 500; // step size for rows

			int numRows = (max / step) + 1; // array maximum size for rows

			// initialize arrays to store rows and run-times of each
			// deduplication method for plotting
			int[] rows = new int[numRows];
			double[] allPairs = new double[numRows];
			double[] hashLinear = new double[numRows];
			double[] quickSort = new double[numRows];
			int count = 0;

			// set starting point to zero for all run-time deduplication graphs
			rows[count] = 0;
			allPairs[count] = 0;
			hashLinear[count] = 0;
			quickSort[count] = 0;
			count++;

			// determine the run-times of deduplication methods for each input
			// row size
			// iterating based on max rows and step
			for (int i = 1; i < max; i += step) {
				VoterData v = new VoterData("vote_files/SWVF_1_22_med.txt", i);

				// calculating the run-time for allPairs deduplication method
				long s1 = startTimer();
				v.allPairsDeduplication();
				long e1 = endTimer();
				long tS1 = secondsElapsed(s1, e1);
				// converting to seconds
				double elapsedTime1 = (double) tS1 / 1000000000;

				// calculating the run-time for hash Linear deduplication method
				long s2 = startTimer();
				v.hashLinearDeduplication();
				long e2 = endTimer();
				long tS2 = secondsElapsed(s2, e2);
				// converting to seconds
				double elapsedTime2 = (double) tS2 / 1000000000;

				// calculating the run-time for quickSort deduplication method
				long s3 = startTimer();
				v.quickSort(0, v.getVoterRows().size() - 1);
				v.quicksortDeduplication();
				long e3 = endTimer();
				long tS3 = secondsElapsed(s3, e3);
				// converting to seconds
				double elapsedTime3 = (double) tS3 / 1000000000;
				// System.out.println(i);
				System.out.println(count);

				rows[count] = i;

				allPairs[count] = elapsedTime1;
				hashLinear[count] = elapsedTime2;
				quickSort[count] = elapsedTime3;

				count++;
			}

			// create series plot by combining run-times and rows from above
			// into two arrays
			int[] aggregateRows = new int[numRows * 3]; // xvals
			double[] yvals = new double[numRows * 3]; // yvals
			String[] categories = new String[numRows * 3];

			for (int i = 0; i < numRows * 3; i++) {

				// adds the quick sort data to plot
				if (i < numRows) {
					categories[i] = "Quick Sort";
					aggregateRows[i] = rows[i];
					yvals[i] = quickSort[i];

				} else if (i < numRows * 2) { // adds the hash data to plot
					categories[i] = "Hash";
					aggregateRows[i] = rows[i - numRows];
					yvals[i] = hashLinear[i - numRows];

				} else if (i < numRows * 3) { // adds the all pairs data to plot
					categories[i] = "All Pairs";
					aggregateRows[i] = rows[i - numRows * 2];
					yvals[i] = allPairs[i - numRows * 2];
				}
			}

			// plot series using our arrays storing run-times and row numbers
			// for all deduplication methods
			DoubleColumn column1 = DoubleColumn.create("rows", aggregateRows);
			DoubleColumn column2 = DoubleColumn.create("run-time (s)", yvals);

			StringColumn catcolumn = StringColumn.create("algorithm",
					categories);
			Table table = Table.create("for plot");
			table.addColumns(column1, column2, catcolumn);
			Plot.show(LinePlot.create(
					"Run-Time Analysis for Deduplication Methods", table,
					"rows", "run-time (s)", "algorithm"));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	/**
	 * calculating run-time based on seconds elapsed
	 * 
	 * @param long start (start time), long end (end time)
	 * @return long end-start
	 */
	private static long secondsElapsed(long start, long end) {
		return end - start;
	}

	/**
	 * ending the timer
	 * 
	 * @param N/A
	 * @return long time (in nanoseconds)
	 */
	private static long endTimer() {
		return System.nanoTime();
	}

	/**
	 * starting the timer
	 * 
	 * @param N/A
	 * @return long time (in nanoseconds)
	 */
	private static long startTimer() {
		return System.nanoTime();
	}

	/**
	 * method for computing fib(n)
	 * 
	 * @param int n
	 * @return int fib sequence of n
	 */
	public static int fib(int n) {
		if (n == 0 || n == 1) {
			return 1;
		} else {
			return fib(n - 2) + fib(n - 1);
		}
	}

}
