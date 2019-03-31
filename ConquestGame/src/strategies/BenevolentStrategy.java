package strategies;

import beans.Country;
import beans.Player;

public class BenevolentStrategy extends Strategy {
	private Country weakestCountry =null;
	public BenevolentStrategy(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reEnforce() {
		int newArmies = obtainNewArmies();
	    weakestCountry = compareCountries("weakest",null);
	    weakestCountry.setNumArmies(newArmies);
		this.distributeArmies(generateArmyCountyMap(weakestCountry));
		
	}

	@Override
	public void attack() {
		// Benevolent Strategy does not require this phase.
		
	}

	@Override
	public void fortify() {
		// TODO Auto-generated method stub
		//Q:How to make the decision to move armies, we might be making the from country weak?
		//Also should move all the armies from stronger country to weaker country
		
	}

	@Override
	public void placeArmiesForSetup() {
		// TODO Auto-generated method stub
		
	}

}
