/**
 * 
 */
package strategies;

import java.util.List;
import java.util.Map;

import beans.Country;
import beans.EventType;
import beans.Player;

/**
 * A cheater computer player strategy whose reinforce() method doubles the
 * number of armies on all its countries, whose attack() method automatically
 * conquers all the neighbors of all its countries, and whose fortify() method
 * doubles the number of armies on its countries that have neighbors that belong to
 * other players. 
 * @author 
 *
 */
public class CheaterStrategy extends Strategy{

	private Player player = null;

	public CheaterStrategy(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reEnforce() {
		int newArmies = obtainNewArmies();
		player.notifyChanges(EventType.PHASE_NOTIFY);
		Map<Country, Integer> list = controller.distributeArmies(newArmies);
		this.distributeArmies(list);
		for(Country rec:player.getPlayerCountries()) {
			rec.setNumArmies(rec.getNumArmies()*2);
		}
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fortify() {
		// TODO Auto-generated method stub
		for(Country rec:player.getPlayerCountries()) {
			List<Country> defendingNeighbours = getdefendingNeighbours(rec);
			if(defendingNeighbours.size()>0) {
				rec.setNumArmies(rec.getNumArmies()*2);
			}
			// do we still need to provide the option of moving armies or just  double the armies if defending
			//Neighbor is there.
			player.notifyChanges(EventType.FORTIFICATION_NOTIFY);
		}
		
	}

	@Override
	public void placeArmiesForSetup() {
		// TODO Auto-generated method stub
		
	}

}
