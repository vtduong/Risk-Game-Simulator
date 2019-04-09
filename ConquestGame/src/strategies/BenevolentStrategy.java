package strategies;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import beans.Country;
import beans.EventType;
import beans.Player;
import gui.PhaseView;

public class BenevolentStrategy extends Strategy implements Serializable {
	private Country weakestCountry = null;

	public BenevolentStrategy(Player player) {
		super(player);
	}

	@Override
	public void reEnforce() {
		int newArmies = obtainNewArmies();
		weakestCountry = compareCountries("weakest", null);
		this.distributeArmies(generateArmyCountyMap(weakestCountry,newArmies));

	}

	@Override
	public void attack() {
		// Benevolent Strategy does not require this phase.

	}

	@Override
	public void fortify() {
		
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		this.getPlayer().notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		Country fromCountry = null, toCountry = null;
		toCountry = compareCountries("weakest", null);
		String toName = toCountry.getName(), fromName = null;
		fromCountry=toCountry;
		// get the list of countries from where moving armies is possible
        ArrayList<Country> validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
        HashMap<String,Integer> adjSize = new HashMap<String,Integer> ();
        ArrayList<Country> toRemove =new ArrayList<Country>();
        while(validCountriestoMove.size()==0 && this.getPlayer().getPlayerCountries().size()>1) {
        	toRemove.add(toCountry);
        	toCountry = compareCountries("weakest", toRemove);
        	validCountriestoMove = (ArrayList<Country>) validCountryMove(toCountry);
        	if(!adjSize.containsKey(toCountry.getName())) {
        		adjSize.put(toCountry.getName(),validCountriestoMove.size());
        	}
        	if(adjSize.size()==this.getPlayer().getPlayerCountries().size()) {
        		break;
        	}
        
        }
        
		for(Country rec: validCountriestoMove) {
			if(rec.getNumArmies()>fromCountry.getNumArmies() || fromCountry.getName().equals(toCountry.getName())) {
				fromCountry = rec;
			}
		}
		
		int armiesToMove = 0;
		// check if from country has any other player as neighbor.
		
		if (getdefendingNeighbours(fromCountry).size()==0) {
			armiesToMove = fromCountry.getNumArmies() - 1;
		} else {
			armiesToMove = 1;
		}

			
			toName = toCountry.getName();
			fromName =fromCountry.getName();
			if(toName!=fromName && fromCountry.getNumArmies()-armiesToMove>1) {
				this.moveArmies(fromName, toName, armiesToMove);
			}else {
				System.out.println("You can not move armies.");
			}
			this.getPlayer().notifyChanges(EventType.FORTIFICATION_NOTIFY);

	}

	@Override
	public void placeArmiesForSetup() {
		this.getPlayer().notifyChanges(EventType.PHASE_NOTIFY);
		// randomly assign armies to countries.
	
		Map<Country,Integer> armyCountryMap =new HashMap<Country,Integer>();
		Random r = new Random();
		int maxArmies =this.getPlayer().getArmies();
		for(Country rec:this.getPlayer().getPlayerCountries()) {
			int temp =r.nextInt((maxArmies - 0) + 1) + 0;
			armyCountryMap.put(rec, temp);
			maxArmies =maxArmies- temp;
		}
		
		this.distributeArmies(armyCountryMap);
	}

}
