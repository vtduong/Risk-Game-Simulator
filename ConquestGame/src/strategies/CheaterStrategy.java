/**
 * 
 */
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

/**
 * A cheater computer player strategy whose reinforce() method doubles the
 * number of armies on all its countries, whose attack() method automatically
 * conquers all the neighbors of all its countries, and whose fortify() method
 * doubles the number of armies on its countries that have neighbors that belong to
 * other players. 
 * @author 
 *
 */
public class CheaterStrategy extends Strategy implements Serializable {


	public CheaterStrategy(Player player) {
		super(player);
	}

	@Override
	public void reEnforce() {
		//controller.setCurrentPhase("ReInforce");
		//int newArmies = obtainNewArmies();
		this.getPlayer().notifyChanges(EventType.PHASE_NOTIFY);
		//Map<Country, Integer> list = controller.distributeArmies(newArmies);
		//this.distributeArmies(list);
		for(Country rec:this.getPlayer().getPlayerCountries()) {
			rec.setNumArmies(rec.getNumArmies()*2);
		}
		//player.notifyChanges(EventType.REENFORCEMENT_NOTIFY);
		
	}

	@Override
	public void attack() {
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		List<Country> defendingNeighbours = null;
		List<Country> currentCountryPlayerList = this.getPlayer().getPlayerCountries();
		System.out.println(currentCountryPlayerList.size());
		for(Country rec:currentCountryPlayerList) {
			defendingNeighbours = getdefendingNeighbours(rec);
			for(Country recDef :defendingNeighbours) {
				Player ownerRec = recDef.getOwner();
				ownerRec.removeCountry(recDef.getName());
				this.getPlayer().addCountry(recDef.getName(), recDef);
			}
		}
	}

	@Override
	public void fortify() {
		// TODO Auto-generated method stub
		for(Country rec:this.getPlayer().getPlayerCountries()) {
			List<Country> defendingNeighbours = getdefendingNeighbours(rec);
			if(defendingNeighbours.size()>0) {
				rec.setNumArmies(rec.getNumArmies()*2);
			}
			
		}
		this.getPlayer().notifyChanges(EventType.FORTIFICATION_NOTIFY);
	}

	@Override
	public void placeArmiesForSetup() {
		Map<Country,Integer> armyCountryMap =new HashMap<Country,Integer>();
		Random r = new Random();
		int maxArmies =this.getPlayer().getArmies() - this.getPlayer().getNumArmiesDispatched();;
		for(Country rec:this.getPlayer().getPlayerCountries()) {
			int temp =r.nextInt((maxArmies - 0) + 1) + 0;
			armyCountryMap.put(rec, temp);
			maxArmies =maxArmies-temp;
		}
		
		this.distributeArmies(armyCountryMap);
		
	}

}
