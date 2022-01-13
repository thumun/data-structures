package prelab;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println(
				"Given command line arguments: \n" + Arrays.toString(args));

		// Variables to keep track of the produce
		ArrayList<String> vegetableNames = new ArrayList<String>();
		ArrayList<String> fruitNames = new ArrayList<String>();
		ArrayList<String> spiceNames = new ArrayList<String>();
		double totalAssetVegetable = 0;
		double totalAssetFruit = 0;
		double totalAssetSpice = 0;

		// TODO: parse the command line arguments!

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f")) {
				fruitNames.add(args[i + 1]);
				totalAssetFruit += Integer.parseInt(args[i + 2]);
			} else if (args[i].equals("-v")) {
				vegetableNames.add(args[i + 1]);
				totalAssetVegetable += Integer.parseInt(args[i + 2]);
			} else if (args[i].equals("-s")) {
				spiceNames.add(args[i + 1]);
				totalAssetSpice += Integer.parseInt(args[i + 2]);
			}
		}

		// TODO: create an object of each type and print it!

		ProduceStand fruits = new ProduceStand(fruitNames, totalAssetFruit,
				"Fabulous Fruit Stand");
		System.out.println(fruits.toString());

		ProduceStand vegetables = new ProduceStand(vegetableNames,
				totalAssetVegetable, "Valiant Vegetable Stand");
		System.out.println(vegetables.toString());

		ProduceStand spices = new ProduceStand(spiceNames, totalAssetSpice,
				"Super Spice Stand");
		System.out.println(spices.toString());

		// TODO: after implementing comparable, compare the objects above!

		String orange = "orange";
		String lemon = "lemon";
		System.out.println(
				"orange compared to lemon: " + orange.compareTo(lemon));
		System.out.println(
				"lemon compared to orange: " + lemon.compareTo(orange));
		System.out
				.println("lemon compared to lemon: " + lemon.compareTo(lemon));

		spices.compareTo(fruits);

	}
}
