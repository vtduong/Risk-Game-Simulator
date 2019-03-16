package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.GameController;
import gui.Observer;

// TODO: Auto-generated Javadoc
/**
 * <p> This class stores the players details.<p>
 *
 */
public class Player implements Observable {
	

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
	
	/** The observer list. */
	private List<Observer> obList = null;
	
	private int numArmiesDispatched = 0;
	
	private static GameController controller = GameController.getInstance();
	
	
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
		obList = new ArrayList<Observer>();
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
	public void attach(Observer ob) {
		obList.add(ob);
		
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
	public void notifyChanges() {
		for(Observer o : obList) {
			o.update(this);
		}
		
	}
	
	
	/**
	 * Attack phase
	 */
	public void attack() throws IllegalArgumentException{
		System.out.println("-----------Attack Phase-----------");
		//TODO get attacked country from user, controller
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
			if(con.getNumArmies() > 2) {
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
		//TODO battle and roll dice
		
		 
		
	}



	/**
	 * ReEnforcement phase
	 * This methods calls 2 other private methods to 1) obtain new armies and 2)
	 * distribute armies among occupied countries.
	 */
	public void reEnforce() {
		System.out.println("-----------Re-EnForcement Phase-----------");
		obtainNewArmies();
		this.notifyChanges();
		distributeArmies();
		
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
		}
		
	}
	
	/**
	 * Obtain new armies.
	 *
	 * @return total new armies current player is granted to be added to existing armies.
	 */
	public int obtainNewArmies() {
		
		//player's choice of set of cards to be traded
		int setChoice = (cardSetChoice > 1) ? cardSetChoice : 1;
		//redeem armies by cards
		// TODO
//		int armiesByCards = redeemCards(setChoice);
		
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
		
		//obtain armies by The specific territory pictured on a traded-in card
		//NOT APPLICABLE
		//TODO add armiesByCards later
		int totalNewArmies = armiesByCountries + armiesByContinents;
		this.increaseArmies(totalNewArmies);
		return totalNewArmies;
	}
	
	
	/**
	 * Fortify player's countries in fortification phase
	 *
	 * @throws IllegalArgumentException if 2 countries given are not adjacent or if one of the countries is not owned by player
	 */
	public void fortify() throws IllegalArgumentException {
		System.out.println("--------------Fortification Phase------------");
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
			this.getCountryByName(country.getName()).setNumArmies(numArmies);
			int totalArmiesToSet = numArmies + country.getNumArmies();
			this.getCountryByName(country.getName()).setNumArmies(totalArmiesToSet);
		}
	}
	
}
