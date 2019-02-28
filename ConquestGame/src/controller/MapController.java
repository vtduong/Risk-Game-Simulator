package controller;

import beans.Continent;
import beans.Country;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapController {

	public static void main(String argv[]) throws IOException {
		String filepath = "src/resources/results.map";
		BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, false));
		Scanner scan = new Scanner(System.in);
		setMapDetails(bw);
		Map<String, Integer> continentMap = new HashMap<String, Integer>();
		System.out.println("Please Enter Map Details");
		System.out.println(
				"Please input the numbers of continet you wish to add." + "The value should be grater than 1.");
		int continentNumber = scan.nextInt();
		scan.nextLine();
		String tempname, controlValue;
		for (int i = 0; i < continentNumber; i++) {

			System.out.println("Enter Continent Name");
			tempname = scan.nextLine();
			System.out.println("Enter Continent value");
			controlValue = scan.nextLine();
			continentMap.put(tempname, Integer.parseInt(controlValue));
		}
		// Call add continent Method
		addContinent(continentMap, bw);

		// adding Countries
		System.out.println(
				"Please input the numbers of countries you wish to add." + "The value should be grater than 1.");

		int countryNo = scan.nextInt();
		scan.nextLine();
		String tempStr;
		String [] countryList = new String[countryNo];
		System.out.println("Enter Country Details in Order specified!!");
		for (int i = 0; i < countryNo; i++) {
			tempStr = scan.nextLine();
			countryList[i] =(tempStr);
		}
		addCountry(countryList,bw);
		
		bw.close();
		scan.close();
		validateMap(filepath);
	}

	private void createMap() throws IOException {
	}

	private static void validateMap(String inputFile) throws IOException {
		new utilities.MapValidator(inputFile).createCountryGraph();
	}

	private void editMap(String inputFile) {

	}

	private static void setMapDetails(BufferedWriter bw) throws IOException {
		bw.write("[Map]");
	}

	private static void addContinent(Map<String, Integer> continentMap, BufferedWriter bw) throws IOException {
		bw.write("[Continents]" + "\n");
		for (String key : continentMap.keySet()) {
			bw.write(key + "=" + continentMap.get(key) + "\n");
		}

	}

	private static void addCountry(String [] arrayList,BufferedWriter bw) throws IOException {
		bw.write("[Territories]" + "\n");
		for(int k=0;k<arrayList.length;k++) {
			bw.write(arrayList[k] +"\n");
		}
		

	}

	private void removeContinet() {

	}

	private void removeCountry() {

	}

}
