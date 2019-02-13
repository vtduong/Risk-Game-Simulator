package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p> This class stores the players details.<p>
 *
 */
public class Player {
	

	private final String player_name;
	private final boolean is_human;
	private int armies;
	private HashMap<String, Country> occupied_countries;
	private HashMap<String, Continent> occupied_continents;
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
	 * @param player_name This is the player's name.
	 * @param is_human true if the player is human, otherwise false.
	 * @param armies The number of armies player holds.
	 */
	public Player(String player_name, boolean is_human, int armies) {
		
		super();
		this.player_name = player_name;
		this.is_human = is_human;
		this.armies = armies;
		this.occupied_continents = new HashMap<String, Continent>();
		this.occupied_countries = new HashMap<String, Country>();
	}
	
	
	
	/**
	 * @return The name of the player.
	 */
	public String getPlayerName() {
		return player_name;
	}
	
	
	
	/**
	 * @return true if the player is human, otherwise returns false.
	 */
	public boolean isHuman() {
		return is_human;
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
		
		this.occupied_continents.put(continent_name, continent);
	}
	
	
	/**
	 * @param country_name The name of the country that needs to
	 * be added.
	 * @param country The country object for the corresponding 
	 * country name.
	 */
	public void addCountry(String country_name, Country country) {
		
		this.occupied_countries.put(country_name, country);
	}
	
	
	/**
	 * @param countries_name The names of the countries that needs to
	 * be added.
	 * @param countries The country objects for the respective country.
	 */
	public void addCountries(String[] countries_name, Country[] countries) {
		
		for(int i = 0; i < countries.length ; i++)
			this.occupied_countries.put(countries_name[i],
					countries[i]);
	}
	
	
	/**
	 * @param continents_name The names of the continents that need to 
	 * be added
	 * @param continents The continent objects for the respective continent.
	 */
	public void addContinents(String[] continents_name, Continent[] continents) {
		
		for(int i = 0; i <continents.length; i++)
			this.occupied_continents.put(continents_name[i],
					continents[i]);
	}
	
	
	/**
	 * @param country Name of the country that need to be removed
	 * from the players list of countries.
	 */
	public void removeCountry(String country) {
		
		this.occupied_countries.remove(country);
	}
	
	
	
	/**
	 * @param continent Name of the continent that need to be removed
	 * from the players list of continents.
	 */
	public void removeContinent(String continent) {
		
		this.occupied_continents.remove(continent);
	}
	
	
	
	
	/**
	 * @return The list of countries that the player occupied.
	 */
	public List<Country> getPlayerCountries() {
		
		List<Country> countries = new ArrayList<Country>(
				this.occupied_countries.values());
		
		return countries;
	}
	
	/**
	 * @return The list of continents that the player occupied.
	 */
	public List<Continent> getPlayerContinents() {
		List<Continent> continents = new ArrayList<Continent>(
				this.occupied_continents.values());
		
		return continents;
	}
	
}
