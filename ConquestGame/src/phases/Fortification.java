package phases;

import java.util.ArrayList;
import java.util.List;

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
	
	/** The controller. */
	GameController controller = GameController.getInstance();
	/** The current player. */
	private Player curPlayer = controller.getCurrentPlayer();
	
	
	public Fortification() {
		System.out.println("--------------Fortification Phase------------");
	}
	/* (non-Javadoc)
	 * @see phases.TurnPhase#nextPhase(controller.GameController)
	 */
	public void takePhase() throws IllegalArgumentException {
		curPlayer = controller.getCurrentPlayer();
		//move armies from one (and only one) country to another neighboring country
		String fromName = controller.selectCountryToTransferFrom(curPlayer.getPlayerCountries());
		Country fromCountry = curPlayer.getCountryByName(fromName);
		if(fromCountry == null) {
			throw new IllegalArgumentException(fromName + " is not a country you occupied!");
		}
		List<String> adjCountries = fromCountry.getAdjacentCountries();
		if(adjCountries == null || adjCountries.size() == 0) {
			throw new IllegalArgumentException("There is no adjacent countries occupied by you!");
		}
		//get the names of countries occupied by this player among adjacent countries
		List<String> occupiedCountries = new ArrayList<String>();
		for(int i = 0; i < adjCountries.size(); i++) {
			Country country = curPlayer.getCountryByName(adjCountries.get(i));
			if(country != null) {
				occupiedCountries.add(adjCountries.get(i));
			}
		}
		String toName = controller.selectCountryToTransferTo(occupiedCountries);
		int numArmies = controller.getParamsForFortification(fromCountry);
		Country toCountry = curPlayer.getCountryByName(toName);
//		fromCountry.setNumArmies(fromCountry.getNumArmies() - numArmies);
//		toCountry.setNumArmies(toCountry.getNumArmies() + numArmies);
		moveArmies(fromName, toName, numArmies);
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
		if( from == null || to == null) {
			throw new IllegalArgumentException("The selected country is not occupied by you!");
		}
		List<String> adjFrom = from.getAdjacentCountries();
		List<String> adjTo = to.getAdjacentCountries();
		//check if 2 countries are adjacent
		if(adjFrom == null || adjTo == null || !adjFrom.contains(to.getName()) || !adjTo.contains(from.getName())) {
			throw new IllegalArgumentException("Two countries must be adjacent");
		}
		//check if number of armies move is valid
		if(from.getNumArmies() - numArmies < 1) {
			throw new IllegalArgumentException("there must be at least 1 army per country");
		}
		if(numArmies < 0) {
			throw new IllegalArgumentException("Number of armies must be non-negative!");
		}
		from.setNumArmies(from.getNumArmies() - numArmies);
		to.setNumArmies(to.getNumArmies() + numArmies);
		
	}

	/* (non-Javadoc)
	 * @see phases.TurnPhase#setNextPhase()
	 */
	@Override
	public void setNextPhase() {
		controller.setPhase(null);
		
	}


}
