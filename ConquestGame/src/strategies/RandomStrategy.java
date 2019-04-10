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
 * Implementation of Random Strategy
 * A random computer player strategy that reinforces random a random country,
 * attacks a random number of times a random country, and fortifies a random
 * country, all following the standard rules for each phase. 
 * @author ankit
 *
 */
public class RandomStrategy extends Strategy implements Serializable {
	
	/** The attacking country. */
	private Country attackingCountry = null;

	/**
	 * Instantiates a new random strategy.
	 *
	 * @param player the player
	 */
	public RandomStrategy(Player player) {
		super(player);
	}
	
	
	/**
	 * Method to Choose a random country amoung the list of current players' country .
	 *
	 * @return the random country
	 */
	public Country getRandomCountry() {
		List<Country> countries = this.getPlayer().getPlayerCountries();
		int minRange = 0;
		int maxRange = countries.size() - 1;
		
		Random rand = new Random();
		Country randomCountry = null;
		
		for(;;) {
			int randomCountryInt = rand.nextInt((maxRange - minRange) + 1) + minRange;
			randomCountry = countries.get(randomCountryInt);
			
			if(randomCountry != null)
				break;
		}
		
		
		return randomCountry; 
	}

	/**
	 * Method to attack a random country a random number of times .
	 *
	 * @param attackingCountry the attacking country
	 * @param attackedCountry the attacked country
	 */
	public void randomAttack(Country attackingCountry, Country attackedCountry) {
		controller.setAttackingCountry(attackingCountry);
		int minRange = 0;
		int maxRange = 5;
		Random rand = new Random();
		int randomChances = rand.nextInt((maxRange - minRange) + 1) + minRange;
		while (attackingCountry.getNumArmies() > 1 && attackedCountry.getOwner() != this.getPlayer() && randomChances > 0) {
			// get max number of dices possible for attacker
			int numDiceAttacker = (attackingCountry.getNumArmies() > 3) ? 3 : attackingCountry.getNumArmies() - 1;
			int numDiceDefender = (attackedCountry.getNumArmies() >= 2) ? 2 : attackedCountry.getNumArmies();
			int[] attackerDice = rollDiceAttacker(attackingCountry, numDiceAttacker);
			int[] defenderDice = rollDiceDefender(attackedCountry, numDiceDefender);
			// battle
			int[] result = goToBattle(attackerDice, defenderDice);
			invade(result, attackingCountry, attackedCountry, numDiceAttacker);
			randomChances--;
		}

	}
	
	/* (non-Javadoc)
	 * Implementation of Reinforce phase for Random Strategy
	 * @see strategies.Strategy#reEnforce()
	 */
	@Override
	public void reEnforce() {
		int newArmies = obtainNewArmies();
		this.getPlayer().notifyChanges(EventType.PHASE_NOTIFY);
		Country country = getRandomCountry();
		country.setNumArmies(newArmies);
		
	}
	

	/* (non-Javadoc)
	 * Implementation of Attack phase for Random Strategy
	 * @see strategies.Strategy#attack()
	 */
	@Override
	public void attack() {
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		Country attackingCountry = null;
		List<Country> defendingNeighbours = null;
		for(;;) {
			attackingCountry = getRandomCountry();
			defendingNeighbours = getdefendingNeighbours(attackingCountry);
			
			if(defendingNeighbours.size() > 0)
				break;
		}
		//List<Country> defendingNeighbours = getdefendingNeighbours(attackingCountry);
		Country toAttack = null;
		for (Country rec : defendingNeighbours) {
			if (attackingCountry.getNumArmies() > rec.getNumArmies()) {
				toAttack = rec;
				this.getPlayer().setHasEnemy(true);
				break;
			} else {
				this.getPlayer().setHasEnemy(false);
			}
		}
		if (toAttack == null) {
			this.getPlayer().setHasEnemy(false);
		}
		if (attackingCountry.getNumArmies() > 2 && toAttack != null) {
			this.randomAttack(attackingCountry, toAttack);
		}
		//player.notifyChanges(EventType.ATTACK_NOTIFY);
		
	}

	/* (non-Javadoc)
	 * Implementation of Fortify phase for Random Strategy
	 * @see strategies.Strategy#fortify()
	 */
	@Override
	public void fortify() {
		// TODO Auto-generated method stub
		PhaseView phaseView = new PhaseView();
		controller.registerObserver(phaseView, EventType.PHASE_VIEW_NOTIFY);
		Country fromCountry = null, toCountry = null;
		
		//validCountryMove
		for(;;) {
			fromCountry = getRandomCountry();
			List<Country> validCountriesToMove=validCountryMove(fromCountry);
			while(validCountriesToMove.size()<0) {
				fromCountry = getRandomCountry();
				validCountriesToMove=validCountryMove(fromCountry);
			}
			if (fromCountry.getNumArmies() > 1) {
				int minRange = 1;
				int maxRange = fromCountry.getNumArmies() - 1;
				Random rand = new Random();
				int randomTransfer = rand.nextInt((maxRange - minRange) + 1) + minRange;
				
				//List<String> adjCountries = fromCountry.getAdjacentCountries();
				maxRange = validCountriesToMove.size();
				
				int randomToCountry = rand.nextInt((maxRange - minRange) + 1) + minRange;
				toCountry = validCountriesToMove.get(randomToCountry-1);
				
				fromCountry.setNumArmies(fromCountry.getNumArmies() - randomTransfer);
				toCountry.setNumArmies(toCountry.getNumArmies() + randomTransfer);
				break;
				
			}
			else if(fromCountry.getNumArmies() == 1) {
				int minRange = 1;
				int maxRange = 0;
				Random rand = new Random();				
				List<String> adjCountries = fromCountry.getAdjacentCountries();
				maxRange = adjCountries.size();
				
				int randomToCountry = rand.nextInt((maxRange - minRange) + 1) + minRange;
				String toCountryName = adjCountries.get(randomToCountry);
				toCountry = this.getPlayer().getCountryByName(adjCountries.get(randomToCountry));
				
				fromCountry.setNumArmies(fromCountry.getNumArmies() - 1);
				toCountry.setNumArmies(toCountry.getNumArmies() + 1);
				break;
			}
		}
		this.getPlayer().notifyChanges(EventType.FORTIFICATION_NOTIFY);
	}

	/* (non-Javadoc)
	 * Initial Army setup for Random Strategy
	 * @see strategies.Strategy#placeArmiesForSetup()
	 */
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
