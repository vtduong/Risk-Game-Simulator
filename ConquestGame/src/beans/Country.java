package beans;

import java.util.ArrayList;
import java.util.List;

import gui.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class Country.
 */
public class Country implements Observable{
	
	/** The name. */
	private String name = null;
	
	/** The number of armies currently occupied in this country. */
	private int numArmies;
	
	/** The owner. */
	private Player owner = null;
	
	/** The observer list. */
	private List<Observer> obList = null;
	
	private List<String> adjacentCountries;
	private String continent;
	private int longitude;
	private int latitude;
	
	
	/**
	 * Instantiates a new country.
	 *
	 * @param name the country name
	 */
	public Country(String name) {
		super();
		this.name = name;
		obList = new ArrayList<Observer>();
	}
	


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets the number of armies.
	 *
	 * @return the numArmies
	 */
	public int getNumArmies() {
		return numArmies;
	}


	/**
	 * Sets the number armies.
	 *
	 * @param numArmies the numArmies to set
	 */
	public void setNumArmies(int numArmies) {
		this.numArmies = numArmies;
	}


	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}


	/**
	 * Sets the owner.
	 *
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
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
	 * @see beans.Observable#notifyChanges()
	 */
	@Override
	public void notifyChanges() {
		for(Observer o : obList) {
			o.update(this);
		}
		
	}



	public List<String> getAdjacentCountries() {
		return adjacentCountries;
	}



	public void setAdjacentCountries(List<String> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
	}



	public String getContinent() {
		return continent;
	}



	public void setContinent(String continent) {
		this.continent = continent;
	}



	public int getlongitude() {
		return longitude;
	}



	public void setlongitude(int langitude) {
		this.longitude = langitude;
	}



	public int getLatitude() {
		return latitude;
	}



	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	
	
}
