package beans;

public class Country {
	private String name = null;
	private int numArmies;
	private Player owner = null;
	
	
	/**
	 * @param name
	 * @param numArmies
	 * @param owner
	 */
	public Country(String name) {
		super();
		this.name = name;
	}
	


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the numArmies
	 */
	public int getNumArmies() {
		return numArmies;
	}


	/**
	 * @param numArmies the numArmies to set
	 */
	public void setNumArmies(int numArmies) {
		this.numArmies = numArmies;
	}


	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}


	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	
}
