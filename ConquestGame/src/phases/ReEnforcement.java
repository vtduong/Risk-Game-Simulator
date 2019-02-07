package phases;

import beans.Player;
import controller.GameController;

/**
 * Re-enforcement phase
 * @author vanduong
 *
 *
public class ReEnforcement implements TurnPhase{
	
	private GameController controller = null;
	private Player curPlayer = null;
	private final int MIN_NEW_ARMIES = 3;
	
	public boolean nextPhase(GameController controller) {
		this.controller = controller;
		curPlayer = controller.getCurrentPlayer();
		int numArmie = obtainNewArmies();
		
		return false;
	}

	public int obtainNewArmies(int cardSetChoice) {
		
		//player's set of cards to be traded (1,2), default 0
		int setChoice = (cardSetChoice > 0) ? cardSetChoice : 0;
		//redeem armies by cards
		int armiesByCards = redeemCards(setChoice);
		
		//obtain armies by number of territories occupied
		int numCountries = controller.getContriesByPlayer(curPlayer).size();
		int numArmies = numCountries / 3;
		int armiesByCountries = ((numArmies > 3)) ? numArmies : 3;
		
		//obtain armies by number of continents controllered
		List<Continent> continents= controller.getContinentByPlayer(curPlayer);
		int armiesByContinents = 0;
		for(Continent c : continents) {
			armiesByContinents += c.getMaxArmies();
		}
		
		//obtain armies by The specific territory pictured on a traded-in card
		//NOT APPLICABLE
		int totalNewArmies = armiesByCards + armiesByCountries + armiesByContinents;
		curPlayer.setNumArmies(curPlayer.getNumArmies() + totalNewArmies);
		return totalNewArmies;
	}

	public int redeemCards(int setChoice) {
		int numCards = curPlayer.getNumCards();
		int controller.getNumSetCardsTraded();
		
		//deduct number of cards of each type according to player's set choice
		switch(setChoice) {
		
		}
		int numArmies = convertCards
		return 0;
	}
	
	
	
	public int 

}
**/
