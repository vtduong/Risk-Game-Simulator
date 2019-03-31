/**
 * 
 */
package strategies;

import java.util.List;
import java.util.Map;

import beans.Country;
import beans.EventType;
import beans.Player;
import gui.PhaseView;

/**
 * Represents aggressive strategy in which computer player focuses on attack.
 * @author apoorvasharma
 *
 */
public class AggressiveStrategy extends Strategy{
	private Player player = null;
	private Country attackingCountry =null;
	private Country weakestDefender =null;
	public AggressiveStrategy(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * reinforces its strongest country and then always attack with it until it cannot attack anymore.
	 * @see strategies.Strategy#reEnforce()
	 */
	@Override
	public void reEnforce() {
		// TODO Auto-generated method stub
		int newArmies = obtainNewArmies();
		attackingCountry =compareCountries("strongest",attackingCountry);
		/*weakestDefender =compareCountries("weakest",weakestDefender);
		List<Country> defendingNeighbours = getdefendingNeighbours(attackingCountry);
		player.notifyChanges(EventType.PHASE_NOTIFY);
		// if the attacking country is weak as compared to its weakest defender 
		while(!isStronger(attackingCountry,weakestDefender)) {
			for(Country rec :defendingNeighbours) {
				if(attackingCountry.getNumArmies()>rec.getNumArmies()) {
					attackingCountry =compareCountries("strongest",attackingCountry);
					weakestDefender =compareCountries("weakest",weakestDefender);
				}
				
			}
		}*/
		if(attackingCountry!=null) {
			attackingCountry.setNumArmies(newArmies);
			this.distributeArmies(generateArmyCountyMap(attackingCountry));
		}
	}
	
	private boolean isAttackPossible(Country attackingCountry, Country defendingCountry) {
		boolean isAttackPossoble=false;
		if(attackingCountry.getNumArmies()>defendingCountry.getNumArmies()) {
			isAttackPossoble=true;
		}
		return isAttackPossoble;
	}
	/* (non-Javadoc)
	 * @see strategies.Strategy#attack()
	 */
	@Override
	public void attack() {
		controller.setCurrentPhase("Attack");
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		player.notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		
		if(attackingCountry.getNumArmies() < 2) {
			throw new IllegalArgumentException("The attacking country must have at least 2 armies!");
		}
		
		List<Country> defendingNeighbours = getdefendingNeighbours(attackingCountry);
		Country toAttack=null;
		for(Country rec :defendingNeighbours) {
			if(attackingCountry.getNumArmies()>rec.getNumArmies()) {
				toAttack =rec;
				break;
			}else {
				//TODO : Makes changes in re-enforce to select the next strongest country as attacking country
			}
		}
		
		this.goAllOut(attackingCountry, toAttack);
		
		
	}

	/* (non-Javadoc)
	 * @see strategies.Strategy#fortify()
	 */
	@Override
	public void fortify() {
		// need confirmation on should I assign aarmies to 2nd strongest or to a country that in future might have a 
		// chance to win.
		controller.setCurrentPhase("Fortification");
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		player.notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		Country fromCountry;
		//check id attacking country has any defending neighbor left.
		if(getdefendingNeighbours(attackingCountry).size()>0) {
			
		}
		
	}

	@Override
	public void placeArmiesForSetup() {
		// TODO Auto-generated method stub
		player.notifyChanges(EventType.PHASE_NOTIFY);
		
	}
	
}
