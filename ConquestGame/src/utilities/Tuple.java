/**
 * 
 */
package utilities;

// TODO: Auto-generated Javadoc
/**
 * This class is a container to stores info needed for army distribution in ReEnforcement phase.
 *
 * @author vanduong
 */
public class Tuple {
	
	/** The country where armies are transferring from. */
	private String fromCountry;
	
	/**  The country where armies are transferring to. */
	private String toCountry;
	
	/** The number of armies. */
	private int numArmies;
	
	/**
	 * Instantiates a new tuple.
	 *
	 * @param fromCountry The country where armies are transferring from
	 * @param toCountry   The country where armies are transferring to
	 * @param numArmies   The number of armies
	 */
	public Tuple(String fromCountry, String toCountry, int numArmies) {
		super();
		this.fromCountry = fromCountry;
		this.toCountry = toCountry;
		this.numArmies = numArmies;
	}
	
	/**
	 * Gets the country armies is transferring from.
	 *
	 * @return the country
	 */
	public String getFromCountry() {
		return fromCountry;
	}
	
	/**
	 * Gets the country armies are transfering to.
	 *
	 * @return the country
	 */
	public String getToCountry() {
		return toCountry;
	}
	
	/**
	 * Gets the num armies.
	 *
	 * @return the numArmies
	 */
	public int getNumArmies() {
		return numArmies;
	}
	
	
	 
	
	
}
