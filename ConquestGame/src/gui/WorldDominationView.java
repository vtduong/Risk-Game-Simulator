package gui;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import beans.Continent;
import beans.Country;
import beans.EventType;
import beans.Observable;
import beans.Player;
import controller.GameController;
import utilities.CustomMapGenerator;

// TODO: Auto-generated Javadoc
/**
 * The WorldDominationView class is used to get the progress of 
 * the game. This follows a singleton pattern approach.
 * @author sandeepchowdaryannabathuni
 *
 */
public class WorldDominationView implements Observer, Serializable {
	
	/** The obj. */
	private static WorldDominationView obj = null;
	
	/** The controller. */
	private GameController controller = null;
	
	/** The map details. */
	private CustomMapGenerator mapDetails = null;
	
	/** The format. */
	private DecimalFormat format;
	
	
	
	/**
	 * Instantiates a new world domination view.
	 */
	private WorldDominationView() {
		controller = GameController.getInstance();
		format = new DecimalFormat(".##");
		mapDetails = CustomMapGenerator.getInstance();
		
	}
	
	/**
	 * Gets the single instance of WorldDominationView.
	 *
	 * @return single instance of WorldDominationView
	 */
	public static WorldDominationView getInstance() {
		if(obj == null)
			obj = new WorldDominationView();
		
		return obj;
	}
	
	/**
	 * This method is used to output the progress of the game.
	 *
	 * @return the progress
	 */
	private void getProgress() {
		
		List<Player> players = controller.getPlayerList();
		
		int totalPlayersAvailable = controller.getNumPlayers();
		
		System.out.println("Total Players : " + totalPlayersAvailable);
		
		for(Player player : players) {
			
			String playerName = player.getPlayerName();
			int armies = player.getArmies();
			
			System.out.println("*".repeat(30));
			System.out.println("PlayerName : " + playerName);
			System.out.println("Available armies : " + armies);
			System.out.println("Occupied Continents : " + player.getPlayerContinents().size());;
			
			List<Country> playerOccupiedCountries = player.getPlayerCountries();
			
			
			double occupiedPercentage = 0;
			
			if(playerOccupiedCountries.size() > 0)
				occupiedPercentage = ((double)playerOccupiedCountries.size()/
										mapDetails.countryDefault.size()) * 100;
			System.out.println("Percentage of countries occupied : " 
										+ format.format(occupiedPercentage)
										+ "%");
		}
	}

	/* (non-Javadoc)
	 * @see gui.Observer#update(beans.Observable)
	 */
	@Override
	public void update(Observable sub) {
		// TODO Auto-generated method stub
		getProgress();
	}
}
