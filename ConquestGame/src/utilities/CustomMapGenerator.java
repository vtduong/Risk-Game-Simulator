package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import beans.Country;
import controller.MapController;
import exception.MapInvalidException;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomMapGenerator.
 *
 * @author ankit This class is used to take user input for creating a custom map
 *         or load an existing map.
 */
public class CustomMapGenerator {
	
	/** The custom map. */
	private static CustomMapGenerator customMap = null;
	
	/** The filepath. */
	private final String FILEPATH = "src/resources/usermap.map";
	
	/** The country default. */
	public static List<Country> countryDefault =null;
	
	/**
	 * Instantiates a new custom map generator.
	 */
	private CustomMapGenerator() {
		
	}

	/**
	 * This is used to create a singleton object of 
	 * CustomMapGenerator class.
	 * @return CustomMapGenerator
	 */
	public static CustomMapGenerator getInstance() {
		if(customMap == null) {
			customMap = new CustomMapGenerator();
			 countryDefault = new ArrayList<Country>();
		}
		return customMap;
	}
	
	/**
	 * This is used to create a custom map and validate the same.
	 *
	 * @throws IOException         Signals that an I/O exception has occurred.
	 * @throws MapInvalidException the map invalid exception
	 */
	public void createCustomMap() throws IOException, MapInvalidException {
		MapController mapcontroller = MapController.getInstance();
		mapcontroller.intit("CreateMap", FILEPATH);
		Map<String, Integer> continentMap = new HashMap<String, Integer>();
		Scanner getMapInt = new Scanner(System.in);
		Scanner getMapStr = new Scanner(System.in);
		int numberOfContinent = 0;
		int numberOfCountries = 0;
		String tempContinentName;
		int controlValue;
		System.out.println("Please Enter Map Details");
		System.out.println("Please input the number of continents you wish to"
				+ " add. The value should be greater than 1.");
		
		numberOfContinent = getMapInt.nextInt();
		
		System.out.println();
		
		for(int i = 0; i < numberOfContinent; i++) {

			System.out.println("Enter Continent Name");
			tempContinentName = getMapStr.nextLine();
			System.out.println("Enter Continent value");
			controlValue = getMapInt.nextInt();
			continentMap.put(tempContinentName, controlValue);
		}
		
		mapcontroller.addContinent(continentMap);
		System.out.println("Please input the numbers of countries you wish to add."
				+ " The value should be greater than 1.");
		
		numberOfCountries = getMapInt.nextInt();
		
		List<String>countryList = new ArrayList<String>();
		System.out.println("Enter Country Details in Order specified!!");
		System.out.println("Example: \n Country_name,latitude,longitude,continent,adjacent_countries1,adjacent_countries2.... ");
		for (int i = 0; i < numberOfCountries; i++) {
			System.out.print("Enter the Country Details: ");
			String temp = getMapStr.nextLine();
			countryList.add(temp);
		}
		mapcontroller.addCountry(countryList);
		mapcontroller.mapWritter(FILEPATH);
		
	}
}
