package phases;

import beans.Country;
import beans.Player;
import controller.GameController;
import utilities.Tuple;

// TODO: Auto-generated Javadoc
/**
 * Fortification phase.
 *
 * @author vanduong
 */
public class Fortification implements TurnPhase{

	/** The is done moving. false if user is not done with fortification*/
	private boolean isDoneMoving = false;
	
	/** The current player. */
	private Player curPlayer = null;
	
	/* (non-Javadoc)
	 * @see phases.TurnPhase#nextPhase(controller.GameController)
	 */
	public void nextPhase(GameController controller) {
		curPlayer = controller.getCurrentPlayer();
		//move armies from one (and only one) country to another neighboring country
		do {
			Tuple tuple = controller.getParamsForFortification();
			moveArmies(tuple.getFromCountry(), tuple.getToCountry(), tuple.getNumArmies());
			//ask controller to ask user for permission to continue fortifying
			isDoneMoving = controller.isDoneFortification();
		}while(!isDoneMoving);
		
		//set phase back to before re-enforcement
		controller.setPhase(null);
	}

	/**
	 * Move armies.
	 *
	 * @param fromCountry the country from which armies are transfered
	 * @param toCountry   the country to which armies are transfered
	 * @param numArmies   number of armies transfered
	 * @exception IllegalArgumentException There must be at least one army in one
	 *                                     country
	 */
	private void moveArmies(String fromCountry, String toCountry, int numArmies) throws IllegalArgumentException {
		Country from = curPlayer.getCountryByName(fromCountry);
		Country to = curPlayer.getCountryByName(toCountry);
		if(from.getNumArmies() - numArmies < 1) {
			throw new IllegalArgumentException("there must be at least 1 army in one country");
		}
		from.setNumArmies(from.getNumArmies() - numArmies);
		to.setNumArmies(to.getNumArmies() + numArmies);
		
	}


}
