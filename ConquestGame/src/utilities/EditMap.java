package utilities;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jheaps.annotations.VisibleForTesting;

import beans.Continent;
import beans.Country;
import controller.MapController;
import exception.MapInvalidException;

/**
 * This class is used to Edit an existing Map by taking user input.
 *
 * @author ankit
 */
public class EditMap {
	
	/** The continents. */
	private Map<String, Integer> continents = null;
	
	/** The countries. */
	private ArrayList<String> countries = null;
	
	/** The remove continents. */
	private ArrayList<String> removeContinents = null;
	
	/** The remove countries. */
	private ArrayList<String> removeCountries = null;
	
	/** The remove adjacent countries. */
	private ArrayList<String> removeAdjacentCountries = null;
	
	/** The adj map. */
	private Map<String,List<String>> adjMap =null;
	
	/** The country default. */
	public List<Country> countryDefault =null;
	
	
	/** The adj country map. */
	private Map<String,List<String>> adjCountryMap = null;
	
	/** The edit map. */
	private static EditMap editMap = null;
	
	/** The editedmap. */
	private String EDITEDMAP = "src/resources/usermap.map";
	
	/** The existingmap. */
	private String EXISTINGMAP = "src/resources/World.map";
	
	/** The map controller. */
	private MapController mapController;
	
	

	private EditMap() {
		adjCountryMap = new HashMap<String, List<String>>();

		removeContinents = new ArrayList<String>();
		removeAdjacentCountries = new ArrayList<String>();
		removeCountries = new ArrayList<String>();
		continents = new HashMap<String, Integer>();
		countries = new ArrayList<String>();
		adjMap = new HashMap<String, List<String>>();
		countryDefault = new ArrayList<Country>();
	}

	/**
	 * This is used to create a singleton object of EditMap class.
	 * 
	 * @return EditMap
	 */
	public static EditMap getInstance() {
		if (editMap == null) {
			editMap = new EditMap();
		}
		return editMap;
	}

	/**
	 * This method is used to clone the existing map and then edit the new map by
	 * taking user input .
	 *
	 * @throws IOException         Signals that an I/O exception has occurred.
	 * @throws MapInvalidException the map invalid exception
	 */
	public void editExistingMap() throws IOException, MapInvalidException {
		ReplicateMap replicateMap = ReplicateMap.getInstance();
		replicateMap.cloneMap();
		MapParser mapParser = new MapParser(EDITEDMAP);
		mapParser.readFile();
		countryDefault = mapParser.countriesList;
		mapController = new MapController();
		System.out.println("----------Continent----------");
		for (Continent continent : mapParser.continentsList)
			System.out.println(continent.getName());

		System.out.println("----------Country----------");
		for (Country country : mapParser.countriesList)
			System.out.println(country.getName());

		while (true) {

			System.out.println("----------1)Add Continent----------");
			System.out.println("----------2)Remove Continent----------");
			System.out.println("----------3)Add Country----------");
			System.out.println("----------4)Remove Country----------");
			System.out.println("----------5)Add Adjacency----------");
			System.out.println("----------6)Remove Adjacency----------");
			System.out.println("----------7)Exit----------");

			Scanner getUserOption = new Scanner(System.in);
			int option = getUserOption.nextInt();
			Scanner getUserInputStr = new Scanner(System.in);
			Scanner getUserInputInt = new Scanner(System.in);

			if (option < 1 && option > 5) {
				System.out.println("Invalid option!!!!");
				System.exit(0);
			}

			if (option == 1) {
				System.out.print("Enter the Continent Name: ");
				String cotinentName = getUserInputStr.nextLine();
				System.out.println();
				System.out.println("Enter the control value: ");
				System.out.println();
				int controlValue = getUserInputInt.nextInt();
				continents.put(cotinentName, controlValue);
			}

			else if (option == 2) {
				System.out.print("Enter the Continent Name: ");
				String cotinentName = getUserInputStr.nextLine();
				System.out.println();
				removeContinents.add(cotinentName);
			}

			else if (option == 3) {
				System.out.println("Enter Country Details in Order specified!!");
				System.out.println("Example: \n Country_name,latitude,longitude,continent,adjacent_countries1,adjacent_countries2.... ");
				String countryName = getUserInputStr.nextLine();
				System.out.println();
				countries.add(countryName);
			}

			else if (option == 4) {
				System.out.print("Enter the Country Name: ");
				String countryName = getUserInputStr.nextLine();
				System.out.println();
				removeCountries.add(countryName);
			}

			else if (option == 5) {
				ArrayList<String> addAdjacentCountries = new ArrayList<String>();
				System.out.println("Enter the Country Name: ");
				String countryName = getUserInputStr.nextLine();
				int adjLen = 0;
				System.out.println("Enter number of adjacent list: ");
				adjLen = getUserInputInt.nextInt();

				for (int i = 0; i < adjLen; i++) {
					addAdjacentCountries.add(getUserInputStr.nextLine());
					System.out.println();
				}

				adjCountryMap.put(countryName, addAdjacentCountries);
			}

			else if (option == 6) {
				adjMap = new HashMap<String, List<String>>();
				System.out.println("Enter the Country Name: ");
				String countryName = getUserInputStr.nextLine();
				System.out.println();
				System.out.println("Country/Countries Adjacent to the specified country aree listed below");
				Country conrec = mapParser.getCountry(countryName, (ArrayList<Country>) countryDefault);
				System.out.println(conrec.getAdjacentCountries());
				
				System.out.println("Enter the number of adjacent Countries to be removed: ");
				int conNo = getUserInputInt.nextInt();
				System.out.println("Enter the name of adjacent Countries to be removed: ");
				for (int i = 0; i < conNo; i++) {
					String adjCountryName = getUserInputStr.nextLine();
					if (adjMap.containsKey(countryName)) {
						adjMap.get(countryName).add(adjCountryName);
					} else {
						List<String> tempList = new ArrayList<String>();
						tempList.add(adjCountryName);
						adjMap.put(countryName, tempList);
					}
					if (adjMap.containsKey(adjCountryName)) {
						adjMap.get(adjCountryName).add(countryName);
					} else {
						List<String> tempList = new ArrayList<String>();
						tempList.add(countryName);
						adjMap.put(adjCountryName, tempList);
					}
				}

			}

			else
				break;

		}

		if (removeCountries.size() > 0) {
			mapController.removeCountry(removeCountries, EDITEDMAP, false);
		}

		if (removeContinents.size() > 0) {
			mapController.removeContinent(removeContinents, EDITEDMAP);
		}

		if (continents.size() > 0) {
			mapController.addContinent(continents, null, true, EDITEDMAP);
		}
		if (countries.size() > 0) {
			mapController.addCountry(countries.toArray(), null, true, EDITEDMAP);
		}

		if (adjMap.size() > 0) {
			mapController.removeAdjCountry(null, EDITEDMAP, adjMap, false);
		}

		if (adjCountryMap.size() > 0) {
			mapController.addAdjCountry(adjCountryMap, EDITEDMAP);
		}

		mapController.validateMap(EDITEDMAP);
	}

}
