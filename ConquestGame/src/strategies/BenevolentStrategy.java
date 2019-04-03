package strategies;

import java.awt.List;
import java.util.ArrayList;

import beans.Country;
import beans.EventType;
import beans.Player;
import gui.PhaseView;

public class BenevolentStrategy extends Strategy {
	private Country weakestCountry = null;
	private Player player = null;

	public BenevolentStrategy(Player player) {
		super(player);
		this.player =player;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reEnforce() {
		int newArmies = obtainNewArmies();
		weakestCountry = compareCountries("weakest", null);
		weakestCountry.setNumArmies(newArmies);
		this.distributeArmies(generateArmyCountyMap(weakestCountry));

	}

	@Override
	public void attack() {
		// Benevolent Strategy does not require this phase.

	}

	@Override
	public void fortify() {
		controller.setCurrentPhase("Fortification");
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		player.notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		Country fromCountry = null, toCountry = null;
		toCountry = compareCountries("weakest", null);
		fromCountry=toCountry;
		String toName = toCountry.getName(), fromName = null;
        ArrayList<Country> validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
        
        //System.out.println("------"+validCountriestoMove.size());
		for(Country rec: validCountriestoMove) {
			if(rec.getNumArmies()>fromCountry.getNumArmies() || fromCountry.getName().equals(toCountry.getName())) {
				fromCountry = rec;
			}
		}
		int armiesToMove = 0;
		// check if from country has any other player as neighbor.
		if (getdefendingNeighbours(fromCountry).size() == 0) {
			armiesToMove = fromCountry.getNumArmies() - 1;
		} else {
			armiesToMove = 1;
		}
		if (fromCountry == null) {
			throw new IllegalArgumentException(fromName + " is not a country you occupied!");
		}

		if (toCountry == null) {
			throw new IllegalArgumentException("There is no adjacent countries occupied by you!");
		} else {
			
			toName = toCountry.getName();
			fromName =fromCountry.getName();
			System.out.println("To Country Name"+toName);
			System.out.println("From Country Name"+fromName);
			
			this.moveArmies(fromName, toName, armiesToMove);
			player.notifyChanges(EventType.FORTIFICATION_NOTIFY);
		}

	}

	@Override
	public void placeArmiesForSetup() {
		player.notifyChanges(EventType.PHASE_NOTIFY);
		// get the country with with highest number of defenders and assign all the
		// remaining armies to it.
		Country rec = compareCountries();
		int numArmiesToDispatch = player.getArmies() - player.getNumArmiesDispatched();
		rec.setNumArmies(numArmiesToDispatch);
		this.distributeArmies(generateArmyCountyMap(rec));

	}

}
