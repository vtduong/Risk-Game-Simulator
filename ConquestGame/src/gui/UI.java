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
	 * @param currentPlayer 
	 * @return a tuple containing: country to move armies from, country to move armies to, and number of armies
	 */
	public int getFortificationInfo(Country country) {
		Scanner scan = new Scanner(System.in);
		int numArmies = 0;
		
		while(true) {
			System.out.print("Please enter number of armies being transferred: ");
			int input2 = scan.nextInt();
			System.out.println();
			//make sure there is at least one army left in a country
			if(input2 <= country.getNumArmies() -1) {
				numArmies = input2;
				break;
			}
		}
		
		return numArmies;
	}

//	/**
//	 * @return true of user is ready for next phase
//	 */
//	public static boolean readyForNextPhase() {
//		return false;
//	}

	/**
	 * display error messages
	 * @param message
	 */
	public  void handleExceptions(String message) {
		System.out.println("ERROR: "+ message);
		
	}

	/* (non-Javadoc)
	 * @see gui.Observer#update(beans.Observable)
	 */
	@Override
	public void update(Observable sub) {
		Player player = (Player)sub;
		// display number of countries occupied
		System.out.println("Player's total number of armies: " + player.getArmies());
		System.out.println("Player's total number of dispatched armies: " + player.getNumArmiesDispatched());
		List<Country> countries = player.getPlayerCountries();
		System.out.println("Player's total number of occupied countries: " + countries.size());
		//1System.out.println("Player's occupied countries: " + countries.toString());
		System.out.println("Number of armies in each occupied country: ");
		for(int i = 0; i < countries.size(); i++) {
			Country c = countries.get(i);
			System.out.println(c.getName() + ": " + c.getNumArmies());
		}
		//display number of continents occupied
		System.out.print("Player's occupied continents: ");
		List<Continent> continents = player.getPlayerContinents();
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
	public Map<Country, Integer> distributeArmies(List<Country> list, int numArmies) {
		Map<Country, Integer> armyInfo = new HashMap<Country, Integer>();
		Scanner userInput = new Scanner(System.in);
		int temp = 0;
		//while(numArmies > 0)
//			for(Country c : list) {
//				System.out.println(c.getName());
//			}
			for(Country c : list) {
				while(true) {
					System.out.println("Remaining number of armies to be deployed: " + numArmies);
					System.out.print("PLease assign army for " + c.getName() + ": ");
					temp = userInput.nextInt();
					System.out.println();
					//each country must have at least one army
					if(temp <= numArmies) {
						numArmies = numArmies - temp;
						break;
					}
					this.handleExceptions("Invalid number!");
				}
				//c.setNumArmies(temp);
				armyInfo.put(c, temp);
				if(numArmies == 0)
				{
					break;
				}
			}
		
		return armyInfo;
	}

	/**
	 * @param playerCountries
	 * @return
	 */
	public String selectCountryToTransferFrom(List<Country> playerCountries) {
		Scanner scan = new Scanner(System.in);
		String fromCountry = null;
		 
		System.out.print("Please enter a country name to move armies From:");
		fromCountry = scan.nextLine();
		System.out.println();
			
		
		return fromCountry;
	}

	/**
	 * @param adjCountries
	 * @return
	 */
	public String selectCountryToTransferTo(List<String> adjCountries) {
		System.out.println("Below is a list of your countries adjacent to the selected country:" );
		for(int i = 0; i < adjCountries.size(); i++) {
			System.out.println(adjCountries.get(i));
		}
		System.out.println("Please enter a country name shown above to transfer to:");
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	

}
