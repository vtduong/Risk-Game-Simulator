package phases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import beans.CardSet;
import beans.CardType;
import beans.Player;
import controller.GameController;
import beans.Continent;
import beans.Country;

// TODO: Auto-generated Javadoc
/**
 * Re-enforcement phase.
 *
 * @author vanduong
 */
public class ReEnforcement implements TurnPhase {
	
	/** The controller. */
	GameController controller = GameController.getInstance();
	/** The current player. */
	private Player curPlayer = controller.getCurrentPlayer();
	
	/** The minimum new armies each user gets in ReEnforcement phase. */
	private final int MIN_NEW_ARMIES = 3;
	
	/** The card set choice. */
	private int cardSetChoice = 0;
	
	public ReEnforcement() {
		System.out.println("-----------Re-EnForcement Phase-----------");
	}
	
	/**
	 * This methods calls 2 other private methods to 1) obtain new armies and 2)
	 * distribute armies among occupied countries.
	 */
	public void takePhase() {
		obtainNewArmies();
		curPlayer.notifyChanges();
		//ask controller to request user input for army distribution
		distributeArmies();
		
	}

	/**
	 * distribute number of armies to countries occupied by current player.
	 */
	public void distributeArmies() {
		Map<Country, Integer> list = controller.distributeArmies();
		for (Map.Entry<Country, Integer> entry : list.entrySet()) {
			Country country = entry.getKey();
			int numArmies = entry.getValue();
			int totalArmiesToSet = numArmies + country.getNumArmies();
			curPlayer.getCountryByName(country.getName()).setNumArmies(totalArmiesToSet);
		}
		
	}
	
	
	/**
	 * Distribute armies. For testing purpose only
	 *
	 * @param list the list of countries with corresponding armies.
	 * @VisibleForTesting
	 */
	public void distributeArmies(Map<Country, Integer> list) {
		for (Map.Entry<Country, Integer> entry : list.entrySet()) {
			Country country = entry.getKey();
			int numArmies = entry.getValue();
			curPlayer.getCountryByName(country.getName()).setNumArmies(numArmies);
			int totalArmiesToSet = numArmies + country.getNumArmies();
			curPlayer.getCountryByName(country.getName()).setNumArmies(totalArmiesToSet);
		}
		
		
	}
	
	
	
	
	

	/**
	 * Obtain new armies.
	 *
	 * @return total new armies current player is granted to be added to existing armies.
	 */
	public int obtainNewArmies() {
		
		//player's choice of set of cards to be traded
		int setChoice = (cardSetChoice > 1) ? cardSetChoice : 1;
		//redeem armies by cards
		// TODO
//		int armiesByCards = redeemCards(setChoice);
		
		//obtain armies by number of territories occupied
		int numCountries = curPlayer.getPlayerCountries().size();
		int numArmies = numCountries / 3;
		int armiesByCountries = ((numArmies > MIN_NEW_ARMIES)) ? numArmies : MIN_NEW_ARMIES;
		
		//obtain armies by number of continents controlled
		List<Continent> continents= curPlayer.getPlayerContinents();
		int armiesByContinents = 0;
		for(Continent c : continents) {
			armiesByContinents += c.getMaxArmies();
		}
		
		//obtain armies by The specific territory pictured on a traded-in card
		//NOT APPLICABLE
		//TODO add armiesByCards later
		int totalNewArmies = armiesByCountries + armiesByContinents;
		curPlayer.increaseArmies(totalNewArmies);
		return totalNewArmies;
	}
	// TODO redeemCards
/**
	public int redeemCards(int setChoice) throws IllegalArgumentException{
		CardSet set = CardSet.convertInputToType(setChoice);
		int numCards = curPlayer.getNumCards();
		int controller.getNumSetCardsTraded();
		
		//check for validity then...
		//deduct number of cards of each type according to player's set choice
		if(set == CardSet.ALL_INFANTRY) {
			int numCards = curPlayer.getNumCards(CardType.INFANTRY);
			if (numCards < 3) {
				throw new IllegalArgumentException("Not enough infantry cards for this choice");
			} else {
				curPlayer.setNumCards(CardType.INFANTRY, numCards - 3);
			}
		}
		
		else if(set == CardSet.ALL_CAVALRY) {
			int numCards = curPlayer.getNumCards(CardType.CAVALRY);
			if (numCards < 3) {
				throw new IllegalArgumentException("Not enough cavalry cards for this choice");
			} else {
				curPlayer.setNumCards(CardType.CAVALRY, numCards - 3);
			}
		}
		
		else if(set == CardSet.ALL_ARTILLERY) {
			int numCards = curPlayer.getNumCards(CardType.ARTILLERY);
			if (numCards < 3) {
				throw new IllegalArgumentException("Not enough artillery cards for this choice");
			} else {
				curPlayer.setNumCards(CardType.ARTILLERY, numCards - 3);
			}
		}
		
		else if(set == CardSet.ONE_EACH) {
			int numCardsInf = curPlayer.getNumCards(CardType.INFANTRY);
			int numCardsCav = curPlayer.getNumCards(CardType.CAVALRY);
			int numCardsArt = curPlayer.getNumCards(CardType.ARTILLERY);
			if (numCardsInf < 1) {
				throw new IllegalArgumentException("Not enough infantry types for this choice");
			} if(numCardsCav < 1) {
				throw new IllegalArgumentException("Not enough cavalry types for this choice");
			} if(numCardsArt < 1) {
				throw new IllegalArgumentException("Not enough artillery types for this choice");
			} else {
				curPlayer.setNumCards(CardType.INFANTRY, numCardsInf - 1);
				curPlayer.setNumCards(CardType.CAVALRY, numCardsCav - 1);
				curPlayer.setNumCards(CardType.ARTILLERY, numCardsArt - 1);

			}
		}
		//set numCardSetsTraded
		
		//convert cards to armies
		int numArmies = convertCards
		return 0;
	}
	**/

	/* (non-Javadoc)
	 * @see phases.TurnPhase#setNextPhase(controller.GameController)
	 */
	@Override
	public void setNextPhase() {
		controller.setPhase(new Attack());
		
	}
	
	

}
