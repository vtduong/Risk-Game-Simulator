package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p> This class stores the players details.<p>
 *
 */
public class Player {
	

	private final String playerName;
	private final boolean isHuman;
	private int armies;
	private HashMap<String, Country> occupiedCountries;
	private HashMap<String, Continent> occupiedContinents;
	private CardType cards;
	
	
	
	/**
	 * @return The CardType object.
	 */
	public CardType getCards() {
		return cards;
	}



	/**
	 * @param cards The CardType object
	 */
	public void setCards(CardType cards) {
		this.cards = cards;
	}



	/**
	 * @param playerName This is the player's name.
	 * @param isHuman true if the player is human, otherwise false.
	 * @param armies The number of armies player holds.
	 */
	public Player(String playerName, boolean isHuman, int armies) {
		
		super();
		this.playerName = playerName;
		this.isHuman = isHuman;
		this.armies = armies;
		this.occupiedContinents = new HashMap<String, Continent>();
		this.occupiedCountries = new HashMap<String, Country>();
	}
	
	
	
	/**
	 * @return The name of the player.
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	
	
	/**
	 * @return true if the player is human, otherwise returns false.
	 */
	public boolean isHuman() {
		return isHuman;
	}
	
	
	/**
	 * @return Number of armies of the player.
	 */
	public int getArmies() {
		return armies;
	}
	
	
	/**
	 * @param armies The assign the player with the armies.
	 */
	public void setArmies(int armies) {
		this.armies = armies;
	}
	
	
	/**
	 * @param armies The additional armies that need to be added 
	 * to the current armies.
	 */
	public void increaseArmies(int armies) {
		
		this.armies = this.armies + armies;
	}
	
	
	/**
	 * @param armies The number of army members that need to be
	 * decreased from the current armies.
	 */
	public void decreaseArmies(int armies) {
		
		if(this.armies >= armies)
			this.armies = this.armies - armies;
	}
	
	
	/**
	 * @param continent_name The name of the continent that needs to
	 * be added.
	 * @param continent The continent object for the corresponding 
	 * continent name.
	 */
	public void addContinent(String continent_name, Continent continent) {
		
		this.occupiedContinents.put(continent_name, continent);
	}
	
	
	/**
	 * @param country_name The name of the country that needs to
	 * be added.
	 * @param country The country object for the corresponding 
	 * country name.
	 */
	public void addCountry(String country_name, Country country) {
		
		this.occupiedCountries.put(country_name, country);
	}
	
	
	/**
	 * @param countries_name The names of the countries that needs to
	 * be added.
	 * @param countries The country objects for the respective country.
	 */
	public void addCountries(String[] countries_name, Country[] countries) {
		
		for(int i = 0; i < countries.length ; i++)
			this.occupiedCountries.put(countries_name[i],
					countries[i]);
	}
	
	
	/**
	 * @param continents_name The names of the continents that need to 
	 * be added
	 * @param continents The continent objects for the respective continent.
	 */
	public void addContinents(String[] continents_name, Continent[] continents) {
		
		for(int i = 0; i <continents.length; i++)
			this.occupiedContinents.put(continents_name[i],
					continents[i]);
	}
	
	
	/**
	 * @param country Name of the country that need to be removed
	 * from the players list of countries.
	 */
	public void removeCountry(String country) {
		
		this.occupiedCountries.remove(country);
	}
	
	
	
	/**
	 * @param continent Name of the continent that need to be removed
	 * from the players list of continents.
	 */
	public void removeContinent(String continent) {
		
		this.occupiedContinents.remove(continent);
	}
	
	
	
	
	/**
	 * @return The list of countries that the player occupied.
	 */
	public List<Country> getPlayerCountries() {
		
		List<Country> countries = new ArrayList<Country>(
				this.occupiedCountries.values());
		
		return countries;
	}
	
	/**
	 * @return The list of continents that the player occupied.
	 */
	public List<Continent> getPlayerContinents() {
		List<Continent> continents = new ArrayList<Continent>(
				this.occupiedContinents.values());
		
		return continents;
	}
	
}
