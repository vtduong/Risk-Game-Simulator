package strategies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import beans.Country;
import beans.EventType;
import beans.Player;
import gui.PhaseView;

// TODO: Auto-generated Javadoc
/**
 * Represents aggressive strategy in which computer player focuses on attack.
 * 
 * @author apoorvasharma
 *
 */
public class AggressiveStrategy extends Strategy implements Serializable {
	
	/** The attacking country. */
	private Country attackingCountry = null;

	/**
	 * Instantiates a new aggressive strategy.
	 *
	 * @param player the player
	 */
	public AggressiveStrategy(Player player) {
		super(player);
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
		this.getPlayer().notifyChanges(EventType.PHASE_NOTIFY);
		attackingCountry = compareCountries("strongest", null);
		List<Country> defendingNeighbours = getdefendingNeighbours(attackingCountry);
		List<Country> toRemove =new ArrayList<Country>();
		while(defendingNeighbours.size()==0) {
			toRemove.add(attackingCountry);
			attackingCountry = compareCountries("strongest", toRemove);
			defendingNeighbours = getdefendingNeighbours(attackingCountry);
		}
		if (attackingCountry != null) {
			this.distributeArmies(generateArmyCountyMap(attackingCountry,newArmies));
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see strategies.Strategy#attack()
	 */
	@Override
	public void attack() {
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		this.getPlayer().notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		//attackingCountry = compareCountries("strongest", null);
		List<Country> defendingNeighbours = getdefendingNeighbours(attackingCountry);
		Country toAttack = null;
		for (Country rec : defendingNeighbours) {
				toAttack = rec;
				this.getPlayer().setHasEnemy(true);
				break;
		}
		if (toAttack == null) {
			this.getPlayer().setHasEnemy(false);
		}
		if (toAttack != null && attackingCountry.getNumArmies()>1) {
			this.goAllOut(attackingCountry, toAttack);
		}else {
			this.getPlayer().setHasEnemy(false);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see strategies.Strategy#fortify()
	 */
	@Override
	public void fortify() {
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		this.getPlayer().notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		Country fromCountry = null, toCountry = null;
		String toName, fromName = null;
		toCountry = compareCountries("strongest", null);
		ArrayList<Country> validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
		
		List<Country> defendingNeighbours = getdefendingNeighbours(attackingCountry);
		List<Country> toRemove =new ArrayList<Country>();
		while(defendingNeighbours.size()==0 && validCountriestoMove.size()==0 ) {
			toRemove.add(toCountry);
			toCountry = compareCountries("strongest", toRemove);
			validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
			
		}
		fromCountry=toCountry;
		for (Country rec : validCountriestoMove) {
			if (rec.getNumArmies() > 1 || fromCountry.getName().equals(toCountry.getName())) {
				fromCountry = rec;
			}
		}
		int armiesToMove = 0;
		// check id attacking country has any defending neighbor left.
		
		
		if (getdefendingNeighbours(toCountry).size() == 0) {
			fromCountry = toCountry;
			validCountriestoMove.removeAll(validCountriestoMove);
			validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
			for (Country rec : validCountriestoMove) {
				if (rec.getNumArmies() > toCountry.getNumArmies()
						|| fromCountry.getName().equals(toCountry.getName())) {
					toCountry = rec;
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
			if(fromCountry.getNumArmies()>1 && fromName!=toName) {
				this.moveArmies(fromName, toName, armiesToMove);
			}
			this.getPlayer().notifyChanges(EventType.FORTIFICATION_NOTIFY);
		}

	}

	/* (non-Javadoc)
	 * @see strategies.Strategy#placeArmiesForSetup()
	 */
	@Override
	public void placeArmiesForSetup() {
		this.getPlayer().notifyChanges(EventType.PHASE_NOTIFY);
		// get the country with with highest number of defenders and assign all the
		// remaining armies to it,
		//Country rec = compareCountries();
		//this.distributeArmies(generateArmyCountyMap(rec,player.getArmies()));
		
		Map<Country,Integer> armyCountryMap =new HashMap<Country,Integer>();
		Random r = new Random();
		int maxArmies =this.getPlayer().getArmies() - this.getPlayer().getNumArmiesDispatched();
		for(Country rec:this.getPlayer().getPlayerCountries()) {
			int temp =r.nextInt((maxArmies - 0) + 1) + 0;
			armyCountryMap.put(rec, temp);
			maxArmies =maxArmies-temp;
		}
		
		this.distributeArmies(armyCountryMap);
	}

}
