package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomMapGenerator {
	private static CustomMapGenerator customMap = null;
	private String FILEPATH = "src/resources/usermap.map";
	private CustomMapGenerator() {
		
	}

	public static CustomMapGenerator getInstance() {
		if(customMap == null) {
			customMap = new CustomMapGenerator();
		}
		return customMap;
	}
	
	public void createCustomMap() throws IOException {
		BufferedWriter writeMap = new BufferedWriter(new FileWriter(FILEPATH, false));
		Map<String, Integer> continentMap = new HashMap<String, Integer>();
		Scanner getMapInt = new Scanner(System.in);
		Scanner getMapStr = new Scanner(System.in);
		

		int numberOfContinent = 0;
		int numberOfCountries = 0;
		String tempContinentName, controlValue;
		
		
		writeMap.write("[Map]\n");
		System.out.println("Please Enter Map Details");
		System.out.println("Please input the number of continents you wish to"
				+ " add. The value should be grater than 1.");
		
		numberOfContinent = getMapInt.nextInt();
		
		System.out.println();
		
		for(int i = 0; i < numberOfContinent; i++) {

			System.out.println("Enter Continent Name");
			tempContinentName = getMapStr.nextLine();
			System.out.println("Enter Continent value");
			controlValue = getMapInt.nextLine();
			continentMap.put(tempContinentName, Integer.parseInt(controlValue));
		}
		
		writeMap.write("[Continents]\n");
		for (String key : continentMap.keySet()) {
			writeMap.write(key + "=" + continentMap.get(key).toString() + "\n");
		}
		
		System.out.println("Please input the numbers of countries you wish to add."
				+ " The value should be grater than 1.");
		
		numberOfCountries = getMapInt.nextInt();
		
		String [] countryList = new String[numberOfCountries];
		System.out.println("Enter Country Details in Order specified!!");
		System.out.println("Example: \n Country_name,latitude,longitude,continent,adjacent_countries1,adjacent_countries2.... ");
		System.out.println("c - Change continent\nq - quit\n");
		for (int i = 0; i < numberOfCountries; i++) {
			System.out.print("Enter the country's name: ");
			String temp = getMapStr.nextLine();
			if(temp == "c")
				countryList[i] = "\n";
			else if(temp == "q")
				break;
			else
				countryList[i] = temp;
		}

		writeMap.write("[Territories]\n");
		
		for(int i = 0; i < countryList.length; i++) {
			writeMap.write(countryList[i] + "\n");
		}
		
		writeMap.close();
	}
}
