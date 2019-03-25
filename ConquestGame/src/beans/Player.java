package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import controller.GameController;
import exception.MapInvalidException;
import gui.CardExchangeView;
import gui.Observer;
import gui.PhaseView;
import gui.UI;
import utilities.CustomMapGenerator;
import utilities.DiceRoller;

// TODO: Auto-generated Javadoc
/**
 * <p> This class stores the players details.<p>
 *
 */
public class Player implements Observable {
	

	Map<Integer, Observer> observerList;
	int invadeCount =0;
	int tradeCount =0;
	String cardType;
	List<String> cardToRemove;
	Scanner scan= new Scanner(System.in);
	

	/** The player name. */
	private final String playerName;
	
	/** The is human. */
	private final boolean isHuman;
	
	/** The armies. */
	private int armies;
	
	/** The occupied countries. */
	private HashMap<String, Country> occupiedCountries;
	
	/** The occupied continents. */
	private HashMap<String, Continent> occupiedContinents;
	
	/** The cards. */
	private CardType cards;
	
	private Random random;
	private List<String> cardsAcquired= new ArrayList<String>();
	private UI uiInstance;
	
	/** The observer list. */
	private List<Observer> obList = null;
	
	private int numArmiesDispatched = 0;
	
	private static GameController controller = GameController.getInstance();
	private static CardExchangeView cardView= new CardExchangeView();
	
	
	/** The minimum new armies each user gets in ReEnforcement phase. */
	private final int MIN_NEW_ARMIES = 3;
	
	/** The card set choice. */
	private int cardSetChoice = 0;
	
	
	/**
	 * @return the number of armies has been dispatched to occupied countries
	 */
	public int getNumArmiesDispatched() {
		return numArmiesDispatched;
	}



	/**set number of armies dispatched to occupied countries
	 * @param numArmiesDispatched the numArmiesDispatched to set
	 */
	public void setNumArmiesDispatched(int numArmiesDispatched) {
		this.numArmiesDispatched = numArmiesDispatched;
	}



	/**
	 * Gets the cards.
	 *
	 * @return The CardType object.
	 */
	public List<String> getCardsAcquired(){
		return cardsAcquired;
	}
	
	public int getTradeCount() {
		return tradeCount;
	}
	public CardType getCards() {
		return cards;
	}



	/**
	 * Sets the cards.
	 *
	 * @param cards The CardType object
	 */
	public void setCards(CardType cards) {
		this.cards = cards;
	}



	/**
	 * Instantiates a new player.
	 *
	 * @param playerName This is the player's name.
	 * @param isHuman    true if the player is human, otherwise false.
	 * @param armies     The number of armies player holds.
	 */
	public Player(String playerName, boolean isHuman, int armies) {
		
		super();
		this.playerName = playerName;
		this.isHuman = isHuman;
		this.armies = armies;
		this.occupiedContinents = new HashMap<String, Continent>();
		this.occupiedCountries = new HashMap<String, Country>();
//		obList = new ArrayList<Observer>();
		observerList = new HashMap<Integer, Observer>();
	}
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name
	 */
	public Player(String name) {
		this(name, true, 0);
	}
	
	
	
	/**
	 * Gets the player name.
	 *
	 * @return The name of the player.
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	
	
	/**
	 * Checks if is human.
	 *
	 * @return true if the player is human, otherwise returns false.
	 */
	public boolean isHuman() {
		return isHuman;
	}
	
	
	/**
	 * Gets the armies.
	 *
	 * @return Number of armies of the player.
	 */
	public int getArmies() {
		return armies;
	}
	
	
	/**
	 * Sets the armies.
	 *
	 * @param armies The assign the player with the armies.
	 */
	public void setArmies(int armies) {
		this.armies = armies;
	}
	
	
	/**
	 * Increase armies.
	 *
	 * @param armies The additional armies that need to be added to the current
	 *               armies.
	 */
	public void increaseArmies(int armies) {
		
		this.armies = this.armies + armies;
	}
	
	
	/**
	 * Decrease armies.
	 *
	 * @param armies The number of army members that need to be decreased from the
	 *               current armies.
	 */
	public void decreaseArmies(int armies) {
		
		if(this.armies >= armies)
			this.armies = this.armies - armies;
	}
	public List<String> addCards() {
		random= new Random();
		List<String> cardType = Arrays.asList("INFANTRY", "CAVALRY", "ARTILLERY");
		int RandomCard = random.nextInt(cardType.size());
		cardsAcquired.add(cardType.get(RandomCard));
		System.out.println("CONGRATULATIONS !! A Random card is added to your Card Inventory");
		return cardsAcquired;
	}
	
	
	/**
	 * Adds the continent.
	 *
	 * @param continent_name The name of the continent that needs to be added.
	 * @param continent      The continent object for the corresponding continent
	 *                       name.
	 */
	public void addContinent(String continent_name, Continent continent) {
		
		this.occupiedContinents.put(continent_name, continent);
		continent.setOwner(this);
	}
	
	
	/**
	 * Adds the country.
	 *
	 * @param country_name The name of the country that needs to be added.
	 * @param country      The country object for the corresponding country name.
	 */
	public void addCountry(String country_name, Country country) {
		
		this.occupiedCountries.put(country_name, country);
		country.setOwner(this);
	}
		
	
	/**
	 * Adds the countries.
	 *
	 * @param countries_name The names of the countries that needs to be added.
	 * @param countries      The country objects for the respective country.
	 */
	public void addCountries(String[] countries_name, Country[] countries) {
		
		for(int i = 0; i < countries.length ; i++) {
			this.occupiedCountries.put(countries_name[i],
					countries[i]);
			countries[i].setOwner(this);
		}
			
	}
	
	
	/**
	 * Adds the continents.
	 *
	 * @param continents_name The names of the continents that need to be added
	 * @param continents      The continent objects for the respective continent.
	 */
	public void addContinents(String[] continents_name, Continent[] continents) {
		
		for(int i = 0; i <continents.length; i++) {
			this.occupiedContinents.put(continents_name[i],
					continents[i]);
			continents[i].setOwner(this);
		}
			
	}
	
	
	/**
	 * Removes the country.
	 *
	 * @param country Name of the country that need to be removed from the players
	 *                list of countries.
	 */
	public void removeCountry(String country) {
		
		this.occupiedCountries.remove(country);
	}
	
	
	
	/**
	 * Removes the continent.
	 *
	 * @param continent Name of the continent that need to be removed from the
	 *                  players list of continents.
	 */
	public void removeContinent(String continent) {
		
		this.occupiedContinents.remove(continent);
	}
	
	
	
	/**
	 * Gets the player countries.
	 *
	 * @return The list of countries that the player occupied.
	 */
	public List<Country> getPlayerCountries() {
		
		List<Country> countries = new ArrayList<Country>(
				this.occupiedCountries.values());
		
		return countries;
	}
	
	/**
	 * Gets the country by name.
	 *
	 * @param name the name
	 * @return the country by name
	 */
	public Country getCountryByName(String name) {
		return occupiedCountries.get(name);
	}
	
	/**
	 * Gets the player continents.
	 *
	 * @return The list of continents that the player occupied.
	 */
	public List<Continent> getPlayerContinents() {
		List<Continent> continents = new ArrayList<Continent>(
				this.occupiedContinents.values());
		
		return continents;
	}



	/* (non-Javadoc)
	 * @see beans.Observable#attach(gui.Observer)
	 */
	@Override
	public void attach(Observer ob, int event) {
		observerList.put(event, ob);
		
	}



	/* (non-Javadoc)
	 * @see beans.Observable#detach(gui.Observer)
	 */
	@Override
	public void detach(Observer ob) {
		obList.remove(ob);
		
	}



	/* (non-Javadoc)
	 * @see beans.Observable#notifyChanges(java.util.List)
	 */
	@Override
	public void notifyChanges(int event) {
//		for(Observer o : obList) {
//			o.update(this);
//		}
		observerList.get(event).update(this);
	}
	
	
	/**
	 * Attack phase
	 */
	public void attack() throws IllegalArgumentException{
		controller.setCurrentPhase("Attack");
		notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		//System.out.println("-----------Attack Phase-----------");
		//TODO refactor this method
		// get attacked country from user, controller
		Country attackedCountry = controller.getAttackedCountry();
		if(attackedCountry == null) {
			throw new IllegalArgumentException("The attacked country is not occupied by any player!");
		}
		//list of attacker's country name adj to selected attacked country
		List<String> attackingCountries = new ArrayList<String>();
		String attackedCountryName = attackedCountry.getName();
		
		List<Country> occupiedCountries = this.getPlayerCountries();
		//it's possible that there many attacker's countries adj the selected attacked country
		for(Country con : occupiedCountries) {
			//only consider attaker's countries with at least 2 armies
			if(con.getNumArmies() >= 2) {
				List<String> adjCountries = con.getAdjacentCountries();
				for(String name : adjCountries) {
					if(name.equals(attackedCountryName) ) {
						attackingCountries.add(con.getName());
					}
				}
			}
			
		}
		// check if the attacked country is adjacent to attacker's territories
		if(attackingCountries.isEmpty()) {
			throw new IllegalArgumentException("The attacked country must be adjacent to any of attacker's countries!");
		}
		
		//if there are 2 or more attacking country options, ask user to select one
		String attackingCountryName = null;
		if(attackingCountries.size() > 1) {
			attackingCountryName = controller.selectAttackingCountry(attackingCountries);
		}else {
			attackingCountryName = attackingCountries.get(0);
		}
		
		Country attackingCountry = controller.getCountryByCountryName(attackingCountryName);
		//check if the attacking country has at least 2 armies
		if(attackingCountry.getNumArmies() < 2) {
			throw new IllegalArgumentException("The attacking country must have at least 2 armies!");
		}
		
		//request user to choose all-out mode
		if(controller.isAllOutMode()) {
			this.goAllOut(attackingCountry, attackedCountry);
		}else {
			//roll dice
			int[] attackerDice = null;
			int[] defenderDice = null;
			int attackerSelectNumDice = 0;
			int defenderSelectNumDice = 0;
			
			//get number of dice from attacker
			while(true) {
				try {
					attackerSelectNumDice = controller.getNumDiceAttacker();
					attackerDice = rollDiceAttacker(attackingCountry, attackerSelectNumDice);
					break;
				}catch(IllegalArgumentException e) {
					throw e;
				}
			}
			
			//get number of dice from defender
			while(true) {
				try {
					defenderSelectNumDice = controller.getNumDiceDefender();
					defenderDice = rollDiceDefender(attackedCountry,defenderSelectNumDice);
					break;
				}catch(IllegalArgumentException e){
					throw e;
				}
			}
			
			//battle
			int[] result = goToBattle(attackerDice, defenderDice);
			invade(result, attackingCountry, attackedCountry, attackerSelectNumDice );
		}
		
		
		
	}



	/**
	 * attacks in all-out mode
	 *
	 * @param attackingCountry the attacking country
	 * @param attackedCountry the attacked country
	 */
	public void goAllOut(Country attackingCountry, Country attackedCountry) {
		//keep attacking as long as there's enough armies to attack and haven't occupied defender's country
		while(attackingCountry.getNumArmies() > 1 && attackedCountry.getOwner() != this) {
			//get max number of dices possible for attacker
			int numDiceAttacker = (attackingCountry.getNumArmies() > 3) ? 3 : attackingCountry.getNumArmies() - 1;
			int numDiceDefender = (attackedCountry.getNumArmies() >= 2) ? 2 : attackedCountry.getNumArmies();
			int[] attackerDice = rollDiceAttacker(attackingCountry, numDiceAttacker);
			int[] defenderDice = rollDiceDefender(attackedCountry,numDiceDefender);
			//battle
			int[] result = goToBattle(attackerDice, defenderDice);
			invade(result, attackingCountry, attackedCountry, numDiceAttacker);
		}
		
	}



	/**
	 * Deduct armies on both sides based on dice results and attacker occupies defender's country if possible
	 *
	 * @param result the dice result
	 * @param attackingCountry the attacking country
	 * @param attackedCountry the attacked country
	 * @param defenderSelectNumDice 
	 * @param attackerSelectNumDice 
	 */
	private void invade(int[] result, Country attackingCountry, Country attackedCountry, int attackerSelectNumDice) {
		Player defender = attackedCountry.getOwner();
		uiInstance = new UI();
		if(result[0] > 0) {
			attackingCountry.setNumArmies(attackingCountry.getNumArmies() - result[0]);
			this.setArmies(this.getArmies() - result[0]);
		}
		if(result[1] > 0) {
			attackedCountry.setNumArmies(attackedCountry.getNumArmies() - result[1]);
			defender.setArmies(defender.getArmies() - result[1]);
		}
		
		//check if attacker can occupy defender's territory (attackedCountry)
		if(attackedCountry.getNumArmies() == 0) {
			CustomMapGenerator map = CustomMapGenerator.getInstance();
			this.addCountry(attackedCountry.getName(), attackedCountry);
			//cards are added after a country is conquered
			this.addCards();
			defender.removeCountry(attackedCountry.getName());
			attackedCountry.setNumArmies(attackedCountry.getNumArmies() + attackerSelectNumDice);
			attackingCountry.setNumArmies(attackingCountry.getNumArmies() - attackerSelectNumDice);
			
			//check if attacker has conquered a whole continent
//			Continent continent = map.getContinent(attackedCountry.getContinent());
//			if(this.hasConqueredContinent(continent)) {
//				this.addContinent(continent.getName(), continent);
//			}
//			
//			//check if defender just lost a continent
//			if(defender.hasLostContinent(continent)) {
//				this.removeContinent(continent.getName());
//			}
		}
		
	}

	public int winCountry(){
		invadeCount++;
		return invadeCount;
	}

	/**
	 * checks if a player has lost control of a continent
	 * @param continent The continent to be checked
	 * @return true if lost control
	 */
	public boolean hasLostContinent(Continent continent) {
		String continentName = continent.getName();
		if(occupiedContinents.containsKey(continentName)) {
			return true;
		}
		return false;
	}



	/**
	 * Checks if the given continent is conquered by player
	 * @param continent The continent to check 
	 * @return
	 */
	public boolean hasConqueredContinent(Continent continent) {
		List<Country> countryList = continent.getCountries();
		
		for(Country con : countryList) {
			String countryName = con.getName();
			if(!occupiedCountries.containsKey(countryName)) {
				return false;
			}
		}
		return true;
	}



	/**
	 * compares 2 results of dices to decide who wins the battle
	 *returns the result in an array of 2 elements: [0] contains number of attacker's lost armies, [1] contains that of defender
	 * @param attackerDice the attacker dice
	 * @param defenderDice the defender dice
	 * @return the number indicating the winner
	 */
	public int[] goToBattle(int[] attackerDice, int[] defenderDice) {
		//stores result of the battle [0] contains number of attacker's lost armies, [1] contains that of defender
		int[] result = new int[2];
		
		//stores the smaller number of dices between the 2 sets of dices
		int numDice = Math.min(attackerDice.length, defenderDice.length);
		for(int i = 0; i < numDice; i++) {
			//compare the highest (and second highest) dice of both 
			if(attackerDice[i] > defenderDice[i]) {
				//defender loses 1 army
				result[1] += 1;
			} else {
				//attacker loses 1 army
				result[0] += 1;
			}
		}
		return result;
	}



	/**
	 * validate user choice of number of dices and..
	 * get dice result for the attacker's country.
	 *
	 * @param attackingCountry the attacking country
	 * @return the result
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public int[] rollDiceAttacker(Country attackingCountry, int numDice) throws IllegalArgumentException {
		if(numDice > 3 || numDice < 0) {
			throw new IllegalArgumentException("Please enter 1, 2, or 3 only");
		}
		//must have at least one more army than number of dices
		if( attackingCountry.getNumArmies() - numDice < 1) {
			throw new IllegalArgumentException("must have at least one more army than number of dices!");
		}
		DiceRoller dicer = DiceRoller.getInstance();
		
		return dicer.roll(numDice);	
	}
	
	/**
	 * validates user's choice of number of dices and..
	 * get dice result for defender's country
	 *
	 * @param defendingCountry the defending country
	 * @return the result
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public int[] rollDiceDefender(Country defendingCountry, int numDice) throws IllegalArgumentException {
		if(numDice > 2|| numDice < 0) {
			throw new IllegalArgumentException("Please enter 1 or 2 only");
		}
		//must have at least 2 army to roll 2 dices
		if( defendingCountry.getNumArmies() < 2 && numDice >= 2) {
			throw new IllegalArgumentException("must have at least 2 army to roll 2 dices!");
		}
		
		DiceRoller dicer = DiceRoller.getInstance();
		
		return dicer.roll(numDice);
	}

	public int exchangeCards() {
		if(cardView.isExchangeCardsPossible()== true) {
			cardToRemove= new ArrayList<String>();
			System.out.println("You have the following cards :"+ getCardsAcquired());
			System.out.println("Please select three cards you want to trade off :");
			System.out.println("(Names should be either INFANTRY or CAVALRY or ARTILLERY)");
			for(int i=0;i<3;i++) {
				cardType= scan.next();
				cardToRemove.add(cardType);
			}
			removeCards(cardToRemove);
			tradeCount++;
			System.out.println("You would get additional " + " " + (tradeCount*5) + " " + "armies for this card trade during re-enforcement phase");
		}
		else {
			System.out.println("SORRY !! You don't have a CARDSET to exchange for additional armies");
		}
		return tradeCount;
	}

	public void removeCards(List<String> cardsRemove) {
		getCardsAcquired().removeAll(cardsRemove);
	
	}

	/**
	 * ReEnforcement phase
	 * This methods calls 2 other private methods to 1) obtain new armies and 2)
	 * distribute armies among occupied countries.
	 */
	public void reEnforce() {
		controller.setCurrentPhase("Re-Enforcement");
		notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		//System.out.println("-----------Re-EnForcement Phase-----------");
		obtainNewArmies();
		this.notifyChanges(EventType.PHASE_NOTIFY);
		Map<Country, Integer> list = controller.distributeArmies();
		this.distributeArmies(list);
		//distributeArmies();
		
	}

	/**
	 * distribute number of armies to countries occupied by current player.
	 */
	public void distributeArmies() {
		Map<Country, Integer> list = controller.distributeArmies();
		for (Map.Entry<Country, Integer> entry : list.entrySet()) {
			Country country = entry.getKey();
			int numArmies = entry.getValue();
			int totalArmiesToSet = numArmies + country.getNumArmies();
			this.getCountryByName(country.getName()).setNumArmies(totalArmiesToSet);
			this.setNumArmiesDispatched(this.getNumArmiesDispatched() + numArmies);
		}
		
	}
	
	/**
	 * Obtain new armies.
	 *
	 * @return total new armies current player is granted to be added to existing armies.
	 */
	public int obtainNewArmies() {
		
		
		//redeem armies by cards
		int tradeNumber= getTradeCount();
		int armiesByCards = tradeNumber*5;
		
		//obtain armies by number of territories occupied
		int numCountries = this.getPlayerCountries().size();
		int numArmies = numCountries / 3;
		int armiesByCountries = ((numArmies > MIN_NEW_ARMIES)) ? numArmies : MIN_NEW_ARMIES;
		
		//obtain armies by number of continents controlled
		List<Continent> continents= this.getPlayerContinents();
		int armiesByContinents = 0;
		for(Continent c : continents) {
			armiesByContinents += c.getMaxArmies();
		}
		
		int totalNewArmies = armiesByCountries + armiesByContinents + armiesByCards;
		this.increaseArmies(totalNewArmies);
		return totalNewArmies;
	}
	
	
	/**
	 * Fortify player's countries in fortification phase
	 *
	 * @throws IllegalArgumentException if 2 countries given are not adjacent or if one of the countries is not owned by player
	 */
	public void fortify() throws IllegalArgumentException {
		controller.setCurrentPhase("Fortification");
		notifyChanges(EventType.PHASE_VIEW_NOTIFY);
		//System.out.println("--------------Fortification Phase------------");
		//move armies from one (and only one) country to another neighboring country
		String fromName = controller.selectCountryToTransferFrom(this.getPlayerCountries());
		Country fromCountry = this.getCountryByName(fromName);
		if(fromCountry == null) {
			throw new IllegalArgumentException(fromName + " is not a country you occupied!");
		}
		List<String> adjCountries = fromCountry.getAdjacentCountries();
		//get the names of countries occupied by this player among adjacent countries
		List<String> occupiedCountries = new ArrayList<String>();
		for(int i = 0; i < adjCountries.size(); i++) {
			Country country = this.getCountryByName(adjCountries.get(i));
			if(country != null) {
				occupiedCountries.add(adjCountries.get(i));
			}
		}
		if( occupiedCountries.size() == 0) {
			throw new IllegalArgumentException("There is no adjacent countries occupied by you!");
		}
		String toName = controller.selectCountryToTransferTo(occupiedCountries);
		int numArmies = controller.getParamsForFortification(fromCountry);
		Country toCountry = this.getCountryByName(toName);
//		fromCountry.setNumArmies(fromCountry.getNumArmies() - numArmies);
//		toCountry.setNumArmies(toCountry.getNumArmies() + numArmies);
		moveArmies(fromName, toName, numArmies);
		notifyChanges(EventType.FORTIFICATION_NOTIFY);
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
		Country from = this.getCountryByName(fromCountry);
		Country to = this.getCountryByName(toCountry);
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
	
	/**
	 * Distribute armies. For testing purpose only
	 *
	 * @param list the list of countries with corresponding armies.
	 * @VisibleForTesting
	 */
	public void distributeArmies(Map<Country, Integer> list) {
		for (Map.Entry<Country, Integer> entry : list.entrySet()) {
			Country country = entry.getKey();
			int numArmies = entry.getValue();
			int totalArmiesToSet = numArmies + country.getNumArmies();
			this.getCountryByName(country.getName()).setNumArmies(totalArmiesToSet);
			this.setNumArmiesDispatched(this.getNumArmiesDispatched() + numArmies);
		}
	}
	
}
