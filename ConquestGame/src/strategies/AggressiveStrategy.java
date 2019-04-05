/**
 * 
 */
package strategies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import beans.Country;
import beans.EventType;
import beans.Player;
import gui.PhaseView;

/**
 * Represents aggressive strategy in which computer player focuses on attack.
 * 
 * @author apoorvasharma
 *
 */
public class AggressiveStrategy extends Strategy implements Serializable {
	private Player player = null;
	private Country attackingCountry = null;

	public AggressiveStrategy(Player player) {
		super(player);
		this.player = player;
	}

	/*
	 * (non-Javadoc) reinforces its strongest country and then always attack with it
	 * until it cannot attack anymore.
	 * 
	 * @see strategies.Strategy#reEnforce()
	 */
	@Override
	public void reEnforce() {
		int newArmies = obtainNewArmies();
		attackingCountry = compareCountries("strongest", attackingCountry);
		if (attackingCountry != null) {
			attackingCountry.setNumArmies(newArmies);
			this.distributeArmies(generateArmyCountyMap(attackingCountry));
		}
	}

	private boolean isAttackPossible(Country attackingCountry, Country defendingCountry) {
		boolean isAttackPossoble = false;
		if (attackingCountry.getNumArmies() > defendingCountry.getNumArmies()) {
			isAttackPossoble = true;
		}
		return isAttackPossoble;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see strategies.Strategy#attack()
	 */
	@Override
	public void attack() {
		controller.setCurrentPhase("Attack");
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		player.notifyChanges(EventType.PHASE_VIEW_NOTIFY);

		List<Country> defendingNeighbours = getdefendingNeighbours(attackingCountry);
		Country toAttack = null;
		for (Country rec : defendingNeighbours) {
			if (attackingCountry.getNumArmies() > rec.getNumArmies()) {
				toAttack = rec;
				player.setHasEnemy(true);
				break;
			} else {
				player.setHasEnemy(false);
			}
		}
		if (toAttack == null) {
			player.setHasEnemy(false);
		}
		if (attackingCountry.getNumArmies() > 2 && toAttack != null) {
			this.goAllOut(attackingCountry, toAttack);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see strategies.Strategy#fortify()
	 */
	@Override
	public void fortify() {
		controller.setCurrentPhase("Fortification");
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		player.notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		Country fromCountry = null, toCountry = null;
		String toName, fromName = null;
		toCountry = compareCountries("strongest", null);
		ArrayList<Country> validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
		while(true){
			if(validCountriestoMove.size()!=0) {
				break;
			}else {
				toCountry = compareCountries("strongest", toCountry);
				validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
			}
			
		}
		for (Country rec : validCountriestoMove) {
			if (rec.getNumArmies() > 1 || fromCountry.getName().equals(toCountry.getName())) {
				fromCountry = rec;
			}
		}
		int armiesToMove = 0;
		// check id attacking country has any defending neighbor left.
		if (getdefendingNeighbours(toCountry).size() == 0 || validCountriestoMove.size() == 0) {
			fromCountry = toCountry;
			validCountriestoMove.removeAll(validCountriestoMove);
			validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
			for (Country rec : validCountriestoMove) {
				if (rec.getNumArmies() > toCountry.getNumArmies()
						|| fromCountry.getName().equals(toCountry.getName())) {
					fromCountry = rec;
				}
			}
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
			fromName = fromCountry.getName();
			this.moveArmies(fromName, toName, armiesToMove);
			player.notifyChanges(EventType.FORTIFICATION_NOTIFY);
		}

	}

	@Override
	public void placeArmiesForSetup() {
		player.notifyChanges(EventType.PHASE_NOTIFY);
		// get the country with with highest number of defenders and assign all the
		// remaining armies to it,
		Country rec = compareCountries();
		int numArmiesToDispatch = player.getArmies() - player.getNumArmiesDispatched();
		rec.setNumArmies(numArmiesToDispatch);
		this.distributeArmies(generateArmyCountyMap(rec));
	}

}
