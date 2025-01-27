package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import beans.Continent;
import beans.Country;
import config.Config;
import controller.GameController;
import controller.MapController;
import exception.MapInvalidException;

// TODO: Auto-generated Javadoc
/**
 * This class is responsible for loading a map,creating a new map or editing an existing map.
 * This was modified as part of map refactoring to act as single point for map related user
 * interaction.
 * 
 */
public class CustomMapGenerator implements Serializable{
	
	/** The custom map. */
	private static CustomMapGenerator customMap = null;
	
	
	//It should be only used for game saving.
	/**
	 * Gets the custom map.
	 *
	 * @return the custom map
	 */
	//Don't use for other purpose.
	public CustomMapGenerator getCustomMap() {
		return customMap;
	}

	//It should be only used for game saving.
	/**
	 * Sets the custom map.
	 *
	 * @param customMap the new custom map
	 */
	//Don't use for other purpose.
	public void setCustomMap(CustomMapGenerator customMap) {
		this.customMap = customMap;
	}



	/** The filepath. */
	private final String FILEPATH = "src/resources/usermap.map";
	
	/** The continents. */
	private Map<String, Integer> continents = null;
	
	//It should be only used for game saving.
	/**
	 * Gets the continents.
	 *
	 * @return the continents
	 */
	//Don't use for other purpose.
	public Map<String, Integer> getContinents() {
		return continents;
	}


	//It should be only used for game saving.
	/**
	 * Sets the continents.
	 *
	 * @param continents the continents
	 */
	//Don't use for other purpose.
	public void setContinents(Map<String, Integer> continents) {
		this.continents = continents;
	}
	
	

	/** The countries. */
	private ArrayList<String> countries = null;
	
	//It should be only used for game saving.
	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 */
	//Don't use for other purpose.
	public ArrayList<String> getCountries() {
		return countries;
	}
	
	//It should be only used for game saving.
	/**
	 * Sets the countries.
	 *
	 * @param countries the new countries
	 */
	//Don't use for other purpose.
	public void setCountries(ArrayList<String> countries) {
		this.countries = countries;
	}



	/** The remove continents. */
	private ArrayList<String> removeContinents = null;
	
	//It should be only used for game saving.
	/**
	 * Gets the removes the continents.
	 *
	 * @return the removes the continents
	 */
	//Don't use for other purpose.
	public ArrayList<String> getRemoveContinents() {
		return removeContinents;
	}
	
	//It should be only used for game saving.
	/**
	 * Sets the removes the continents.
	 *
	 * @param removeContinents the new removes the continents
	 */
	//Don't use for other purpose.
	public void setRemoveContinents(ArrayList<String> removeContinents) {
		this.removeContinents = removeContinents;
	}



	/** The remove countries. */
	private ArrayList<String> removeCountries = null;
	
	//It should be only used for game saving.
	/**
	 * Gets the removes the countries.
	 *
	 * @return the removes the countries
	 */
	//Don't use for other purpose.
	public ArrayList<String> getRemoveCountries() {
		return removeCountries;
	}

	//It should be only used for game saving.
	/**
	 * Sets the removes the countries.
	 *
	 * @param removeCountries the new removes the countries
	 */
	//Don't use for other purpose.
	public void setRemoveCountries(ArrayList<String> removeCountries) {
		this.removeCountries = removeCountries;
	}



	/** The remove adjacent countries. */
	private ArrayList<String> removeAdjacentCountries = null;
	
	//It should be only used for game saving.
	/**
	 * Gets the removes the adjacent countries.
	 *
	 * @return the removes the adjacent countries
	 */
	//Don't use for other purpose.
	public ArrayList<String> getRemoveAdjacentCountries() {
		return removeAdjacentCountries;
	}

	//It should be only used for game saving.
	/**
	 * Sets the removes the adjacent countries.
	 *
	 * @param removeAdjacentCountries the new removes the adjacent countries
	 */
	//Don't use for other purpose.
	public void setRemoveAdjacentCountries(ArrayList<String> removeAdjacentCountries) {
		this.removeAdjacentCountries = removeAdjacentCountries;
	}



	/** The adj map. */
	private Map<String,List<String>> adjMap =null;
	
	//It should be only used for game saving.
	/**
	 * Gets the adj map.
	 *
	 * @return the adj map
	 */
	//Don't use for other purpose.
	public Map<String, List<String>> getAdjMap() {
		return adjMap;
	}
	
	//It should be only used for game saving.
	/**
	 * Sets the adj map.
	 *
	 * @param adjMap the adj map
	 */
	//Don't use for other purpose.
	public void setAdjMap(Map<String, List<String>> adjMap) {
		this.adjMap = adjMap;
	}



	/** The country default. */
	public List<Country> countryDefault =null;
	
	//It should be only used for game saving.
	/**
	 * Gets the country default.
	 *
	 * @return the country default
	 */
	//Don't use for other purpose.
	public List<Country> getCountryDefault() {
		return countryDefault;
	}
	
	//It should be only used for game saving.
	/**
	 * Sets the country default.
	 *
	 * @param countryDefault the new country default
	 */
	//Don't use for other purpose.
	public void setCountryDefault(List<Country> countryDefault) {
		this.countryDefault = countryDefault;
	}



	/** The continentmap. */
	private Map<String, Continent> continentmap=null;
	
	//It should be only used for game saving.
	/**
	 * Gets the continentmap.
	 *
	 * @return the continentmap
	 */
	//Don't use for other purpose.
	public Map<String, Continent> getContinentmap() {
		return continentmap;
	}
	
	//It should be only used for game saving.
	/**
	 * Sets the continentmap.
	 *
	 * @param continentmap the continentmap
	 */
	//Don't use for other purpose.
	public void setContinentmap(Map<String, Continent> continentmap) {
		this.continentmap = continentmap;
	}



	/** The country map. */
	private Map<String, Country> countryMap=null;
	
	//It should be only used for game saving.
	/**
	 * Gets the country map.
	 *
	 * @return the country map
	 */
	//Don't use for other purpose.
	public Map<String, Country> getCountryMap() {
		return countryMap;
	}

	//It should be only used for game saving.
	/**
	 * Sets the country map.
	 *
	 * @param countryMap the country map
	 */
	//Don't use for other purpose.
	public void setCountryMap(Map<String, Country> countryMap) {
		this.countryMap = countryMap;
	}



	/** The adj country map. */
	private Map<String,List<String>> adjCountryMap = null;
	
	//It should be only used for game saving.
	/**
	 * Gets the adj country map.
	 *
	 * @return the adj country map
	 */
	//Don't use for other purpose.
	public Map<String, List<String>> getAdjCountryMap() {
		return adjCountryMap;
	}

	//It should be only used for game saving.
	/**
	 * Sets the adj country map.
	 *
	 * @param adjCountryMap the adj country map
	 */
	//Don't use for other purpose.
	public void setAdjCountryMap(Map<String, List<String>> adjCountryMap) {
		this.adjCountryMap = adjCountryMap;
	}



	/** The edit map. */
	private static EditMap editMap = null;
	
	//It should be only used for game saving.
	/**
	 * Gets the edits the map.
	 *
	 * @return the edits the map
	 */
	//Don't use for other purpose.
	public EditMap getEditMap() {
		return editMap;
	}

	//It should be only used for game saving.
	/**
	 * Sets the edits the map.
	 *
	 * @param editMap the new edits the map
	 */
	//Don't use for other purpose.
	public void setEditMap(EditMap editMap) {
		CustomMapGenerator.editMap = editMap;
	}



	/** The editedmap. */
	private String EDITEDMAP = "src/resources/usermap.map";
	
	/** The existingmap. */
	private String EXISTINGMAP = "src/resources/World.map";
	
	/** The map controller. */
	private MapController mapController;
	
	//It should be only used for game saving.
	/**
	 * Gets the map controller.
	 *
	 * @return the map controller
	 */
	//Don't use for other purpose.
	public MapController getMapController() {
		return mapController;
	}

	//It should be only used for game saving.
	/**
	 * Sets the map controller.
	 *
	 * @param mapController the new map controller
	 */
	//Don't use for other purpose.
	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}

	/**
	 * Instantiates a new custom map generator.
	 */
	private CustomMapGenerator() {
		adjCountryMap = new HashMap<String, List<String>>();
		removeContinents = new ArrayList<String>();
		removeAdjacentCountries = new ArrayList<String>();
		removeCountries = new ArrayList<String>();
		continents = new HashMap<String, Integer>();
		countries = new ArrayList<String>();
		adjMap = new HashMap<String, List<String>>();
		countryDefault = new ArrayList<Country>();
		continentmap =new HashMap<String, Continent>();
		countryMap = new HashMap<String, Country>();
	}

	
	
	/**
	 * This is used to create a singleton object of 
	 * CustomMapGenerator class.
	 * @return CustomMapGenerator
	 */
	public static CustomMapGenerator getInstance() {
		if(customMap == null) {
			synchronized (CustomMapGenerator.class) {
	            if(customMap == null){
	                customMap = new CustomMapGenerator();
	            }
	        }
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
		mapController = MapController.getInstance();
		mapController.init("CreateMap", Config.getProperty("usermap"));
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
		
		mapController.addContinent(continentMap);
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
		mapController.addCountry(countryList);
		mapController.mapWritter(Config.getProperty("usermap"));
		countryDefault =mapController.countriesDefault;
		continentmap =mapController.continentmap;
		countryMap =mapController.countrymap;
		
	}
	
	/**
	 * This method is responsible for editing an existing map.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MapInvalidException the map invalid exception
	 */
	public void editExistingMap() throws IOException, MapInvalidException {
		ReplicateMap replicateMap = ReplicateMap.getInstance();
		replicateMap.cloneMap();
		MapParser mapParser = new MapParser(EDITEDMAP);
		mapParser.readFile();
		countryDefault = mapParser.countriesList;
		mapController = MapController.getInstance();
		mapController.init("EditMap",EDITEDMAP);
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
				System.out.println("Country/Countries Adjacent to the specified country are listed below");
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
			mapController.addContinent(continents); 
		}
		if (countries.size() > 0) {
			mapController.addCountry(countries);
		}

		if (adjMap.size() > 0) {
			mapController.removeAdjCountry(null, EDITEDMAP, adjMap, false);
		}

		if (adjCountryMap.size() > 0) {
			mapController.addAdjCountry(adjCountryMap);
		}
		boolean flag = mapController.mapWritter(EDITEDMAP);
		countryDefault =mapController.countriesDefault;
		continentmap =mapController.continentmap;
		countryMap =mapController.countrymap;
		if(flag) {
			System.out.println("Map is successfully editied and validated.");
		}
	}
	
	/**
	 * Responsible for loading and existing map file or user-provided File.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MapInvalidException the map invalid exception
	 */
	public void LoadMap() throws IOException, MapInvalidException {
		mapController = MapController.getInstance();
		System.out.println("Do you want to use Existing File?(Y/N)");
		Scanner input = new Scanner(System.in);
		String option =input.nextLine();
		String filePath;
		if(option.equalsIgnoreCase("N")) {
			System.out.println("Enter the file path");
			filePath = input.nextLine();
		}else {
			filePath =Config.getProperty("worldmap");
		}
		mapController.init("LoadMap",filePath);
		countryDefault =mapController.countriesDefault;
		continentmap =mapController.continentmap;
		countryMap =mapController.countrymap;
		
	}
	
	/**
	 * Load map with given map name.
	 *
	 * @param mapName the map name
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MapInvalidException the map invalid exception
	 */
	public void LoadMap(String mapName) throws IOException, MapInvalidException {
		mapController = MapController.getInstance();
		mapController.init("LoadMap", Config.getProperty(mapName));
		countryDefault =mapController.countriesDefault;
		continentmap =mapController.continentmap;
		countryMap = mapController.countrymap;
	}
	
	
	/**
	 * Responsible for returning Continent based on input provided.
	 *
	 * @param name name of the continent to be returned
	 * @return Continent record if it exist otherwise null
	 */
	public Continent getContinent(String name) {
		if(continentmap.get(name)!=null) {
			return continentmap.get(name);
		}
		else { 
			return null;
		}
	}
	
	/**
	 * Responsible for returning Country based on input provided.
	 *
	 * @param name name of the country to be returned
	 * @return Country record if it exist otherwise null
	 */
	public Country getCountry(String name) {
		if(countryMap.get(name)!=null) {
			return countryMap.get(name);
		}
		else { 
			return null;
		}
	}
	
	/**
	 * Responsible for returning list of adjacent countries based on the input provided.
	 * @param name Country which determines the list of adjacent countries
	 * @return list of adjacent Countries
	 */
	public List<Country> getAdjacentCountry(Country name){
		List<Country> adjacentCountriesList = new ArrayList<Country>();
		List<String> adjStrings = name.getAdjacentCountries();
		for(String str:adjStrings) {
			adjacentCountriesList.add(getCountry(str));
		}
		return adjacentCountriesList;
		
	}
	
	public void reset() {
		customMap =null;
	}
}
