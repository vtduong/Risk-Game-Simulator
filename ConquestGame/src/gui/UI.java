/**
 * 
 */
package gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import beans.Continent;
import beans.Country;
import beans.Observable;
import beans.Player;
import utilities.Tuple;

/**
 * The user interface of the application. 
 * @author vanduong
 *
 */
public class UI implements Observer{

	/**
	 * @return a tuple containing: country to move armies from, country to move armies to, and number of armies
	 */
	public static Tuple getFortificationInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return true of user is ready for next phase
	 */
	public static boolean readyForNextPhase() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * display error messages
	 * @param message
	 */
	public static void handleExceptions(String message) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see gui.Observer#update(beans.Observable)
	 */
	@Override
	public void update(Observable sub) {
		// display number of countries occupied
		System.out.println("Player's total number of armies: " + ((Player)sub).getArmies());
		List<Country> countries = ((Player)sub).getPlayerCountries();
		System.out.println("Player's occupied countries: " + countries.toString());
		System.out.println("Number of armies in each occupied country: ");
		for(int i = 0; i < countries.size(); i++) {
			Country c = countries.get(i);
			System.out.println(c.getName() + ": " + c.getNumArmies());
		}
		//display number of continents occupied
		System.out.print("Player's occupied continents: ");
		List<Continent> continents = ((Player)sub).getPlayerContinents();
		if(continents == null || continents.size() == 0) {
			System.out.println(0);
		} else {
			System.out.println();
			for(int i = 0; i < continents.size(); i++) {
				System.out.println(continents.get(i).getName());
			}
		}
		
	}

	/**
	 *Ask if user wants to go to war
	 * @return true if user wants to go to war
	 */
	public static boolean isWar() {
		System.out.println("Would you like to go to war with another player?(Y/N):");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		input = input.toLowerCase();
		switch(input) {
			case "y":
				return true;
			case "n":
				return false;
		}
		return false;
	}

	/**
	 * Ask user to select number of armies to be distributed for each country
	 * @param list List of countries owned by current player
	 * @param i number of armies owned by this player
	 * @return list of countries and their assigned armies
	 */
	public static Map<Country, Integer> distributeArmies(List<Country> list, int numArmies) {
		Map<Country, Integer> armyInfo = new HashMap<Country, Integer>();
		Scanner userInput = new Scanner(System.in);
		int temp = 0;
		//while(numArmies > 0)
			for(Country c : list) {
				System.out.println(c.getName());
			}
			for(Country c : list) {
				while(true) {
					System.out.print("PLease assign army for " + c.getName() + ": ");
					temp = userInput.nextInt();
					System.out.println();
					if(temp <= numArmies) {
						numArmies = numArmies - temp;
						break;
					}
					System.out.println("Invalid Entry!!");
				}
				c.setNumArmies(temp);
				armyInfo.put(c, temp);
				if(numArmies == 0)
				{
					break;
				}
				//TODO ask user to input number of army for each country in the list
			}
		
		return armyInfo;
	}
	
	public static void displayPlayerInfo() {
		
	}


}
