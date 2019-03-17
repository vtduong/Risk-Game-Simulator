package gui;

import java.util.List;

import beans.Country;
import beans.Player;
import controller.GameController;

/**
 * The WorldDominationView class is used to get the progress of 
 * the game. This follows a singleton pattern approach.
 * @author sandeepchowdaryannabathuni
 *
 */
public class WorldDominationView {
	
	private static WorldDominationView obj = null;
	
	private GameController controller;
	
	private WorldDominationView() {
		controller = GameController.getInstance();
	}
	
	public static WorldDominationView getInstance() {
		if(obj == null)
			obj = new WorldDominationView();
		
		return obj;
	}
	
	/**
	 * This method is used to output the progress of the game.
	 */
	public void getProgress() {
		
		List<Player> players = controller.getPlayerList();
		
		int totalPlayersAvailable = controller.getNumPlayers();
		
		System.out.println("Total Players : " + totalPlayersAvailable);
		
		for(Player player : players) {
			
			String playerName = player.getPlayerName();
			int armies = player.getArmies();
			
			System.out.println("*".repeat(16));
			System.out.println("PlayerName : " + playerName);
			System.out.println("Available armies : " + armies);
			System.out.println("Occupied countries : ");
			
			for(Country country : player.getPlayerCountries()) {
				System.out.println(country.getName() + " of continent " +
									country.getContinent());
				
				System.out.println("Armies available in " + country.getName() +
									" : " + country.getNumArmies());
				
				System.out.println("-".repeat(10));
			}
		}
	}
}
