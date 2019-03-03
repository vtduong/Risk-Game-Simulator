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

import beans.Continent;
import beans.Country;
import controller.MapController;
import exception.MapInvalidException;
public class EditMap {
	private Map<String, Integer> continents = null;
	private ArrayList<String> countries = null;
	private ArrayList<String> removeContinents = null;
	private ArrayList<String> removeCountries = null;
	private ArrayList<String> removeAdjacentCountries = null;
	
	
	private Map<String,List<String>> adjCountryMap = null;
	private static EditMap editMap = null;
	private String EDITEDMAP = "src/resources/usermap.map";
	private String EXISTINGMAP = "src/resources/World.map";
	private MapController mapController;
	
	
	private EditMap() {
		adjCountryMap = new HashMap<String,List<String>>();
		removeContinents = new ArrayList<String>();
		removeAdjacentCountries = new ArrayList<String>();
		removeCountries = new ArrayList<String>();
		continents = new HashMap<String, Integer>();
		countries = new ArrayList<String>();
	}

	/**
	 * This is used to create a singleton object of 
	 * EditMap class.
	 * @return EditMap
	 */
	public static EditMap getInstance() {
		if(editMap == null) {
			editMap = new EditMap();
		}
		return editMap;
	}
	
	public void editExistingMap() throws IOException, MapInvalidException {
		MapParser mapParser = new MapParser(EDITEDMAP);
		mapParser.readFile();
		ReplicateMap replicateMap = ReplicateMap.getInstance();
		replicateMap.cloneMap();
		mapController = new MapController();
		System.out.println("----------Continent----------");
		for(Continent continent : mapParser.continentsList)
			System.out.println(continent.getName());
		
		System.out.println("----------Country----------");
		for(Country country : mapParser.countriesList)
			System.out.println(country.getName());
		
		while(true) {
			
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
			
			if(option < 1&& option > 5) {
				System.out.println("Invalid option!!!!");
				System.exit(0);
			}
			
			
				
			if(option == 1) {
				System.out.print("Enter the Continent Name: ");
				String cotinentName = getUserInputStr.nextLine();
				System.out.println();
				System.out.println("Enter the control value: ");
				System.out.println();
				int controlValue = getUserInputInt.nextInt();
				continents.put(cotinentName, controlValue);	
			}
			
			else if(option == 2) {
				System.out.print("Enter the Continent Name: ");
				String cotinentName = getUserInputStr.nextLine();
				System.out.println();
				removeContinents.add(cotinentName);
			}
			
			else if(option == 3) {
				System.out.print("Enter the Country Name: ");
				String countryName = getUserInputStr.nextLine();
				System.out.println();
				countries.add(countryName);
			}
			
			else if(option == 4) {
				System.out.print("Enter the Country Name: ");
				String countryName = getUserInputStr.nextLine();
				System.out.println();
				removeCountries.add(countryName);
			}
			
			else if(option == 5) {
				ArrayList<String> addAdjacentCountries = new ArrayList<String>();
				System.out.println("Enter the Country Name: ");
				String countryName = getUserInputStr.nextLine();
				System.out.println();
				int adjLen = 0;
				System.out.println("Enter number of adjacent list: ");
				adjLen = getUserInputInt.nextInt();
				
				for(int i =0; i < adjLen; i++) {
					addAdjacentCountries.add(getUserInputStr.nextLine());
					System.out.println();
				}
					
				adjCountryMap.put(countryName, addAdjacentCountries);
			}
			
			else if(option == 6) {
				System.out.println("Enter the Country Name: ");
				String countryName = getUserInputStr.nextLine();
				System.out.println();
				removeAdjacentCountries.add(countryName);
			}
			
			else 
				break;
			
			
			}
		
			
		if(removeCountries.size() > 0)
			mapController.removeCountry(removeCountries, EDITEDMAP);
		
		if(removeContinents.size() > 0)
			mapController.removeContinent(removeContinents, EDITEDMAP);
		
		if(continents.size() > 0)
			mapController.addContinent(continents,null, true, EDITEDMAP);
		
		if(countries.size() > 0)
			mapController.addCountry(countries.toArray(), null, true, EDITEDMAP);
		
		if(removeAdjacentCountries.size() > 0)
			mapController.removeAdjCountry(removeAdjacentCountries, EDITEDMAP);
		
		if(adjCountryMap.size() > 0)
			mapController.addAdjCountry(adjCountryMap, EDITEDMAP);
		    
		mapController.validateMap(EDITEDMAP);
	}
		
	public static void main(String[] args) throws IOException {
		EditMap em = EditMap.getInstance();
		try {
			em.editExistingMap();
		} catch (MapInvalidException e) {
			// TODO Auto-generated catch block
		    System.out.println(e.getMessage());
		}
	}
}
