package beans;

// TODO: Auto-generated Javadoc
/**
 * The Class Country.
 */
public class Country {
	
	/** The name. */
	private String name = null;
	
	/** The number of armies currently occupied in this country. */
	private int numArmies;
	
	/** The owner. */
	private Player owner = null;
	
	
	/**
	 * Instantiates a new country.
	 *
	 * @param name the country name
	 */
	public Country(String name) {
		super();
		this.name = name;
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
	
	
}
