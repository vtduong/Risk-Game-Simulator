package utilities;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import beans.Continent;
import beans.Country;
import controller.MapController;
public class EditMap {
	private Map<String, Integer> continents = null;
	private static EditMap editMap = null;
	private String EDITEDMAP = "src/resources/usermap.map";
	private String EXISTINGMAP = "src/resources/World.map";
	private MapController mapController;
	
	
	private EditMap() {
		continents = new HashMap<String, Integer>();
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
	
	public void editExistingMap() throws IOException {
		MapParser mapParser = new MapParser(EDITEDMAP);
		mapParser.readFile();
		ReplicateMap replicateMap = ReplicateMap.getInstance();
		replicateMap.cloneMap();
		System.out.println("----------Continent----------");
		for(Continent continent : mapParser.continentsList)
			System.out.println(continent.getName());
		
		System.out.println("----------Country----------");
		for(Country country : mapParser.countriesList)
			System.out.println(country.getName());
		
		while(true) {
			
			System.out.println("----------1)Add Continent----------");
			System.out.println("----------2)Edit Continent----------");
			System.out.println("----------3)Add Country----------");
			System.out.println("----------4)Edit Country----------");
			System.out.println("----------5)Exit----------");
			
			Scanner getUserOption = new Scanner(System.in);
			int option = getUserOption.nextInt();
			Scanner getUserInputStr = new Scanner(System.in);
			Scanner getUserInputInt = new Scanner(System.in);
			
			if(option < 1&& option > 4) {
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
				
			}
			
			else if(option == 3) {
				
			}
			
			else if(option == 4) {
				
			}
				
			else
				break;
			
			
		}
		System.out.println(continents.size());
		mapController.addContinent(continents, new BufferedWriter(new FileWriter((EDITEDMAP))), true, EDITEDMAP);
		
		}
		
	public static void main(String[] args) throws IOException {
		EditMap em = EditMap.getInstance();
		em.editExistingMap();
	}
}
