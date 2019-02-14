/**
 * 
 */
package utilities;

/**
 * @author vanduong
 *
 */
public class Tuple {
	private String fromCountry;
	private String toCountry;
	private int numArmies;
	/**
	 * @param fromCountry
	 * @param toCountry
	 * @param numArmies
	 */
	public Tuple(String fromCountry, String toCountry, int numArmies) {
		super();
		this.fromCountry = fromCountry;
		this.toCountry = toCountry;
		this.numArmies = numArmies;
	}
	/**
	 * @return the fromCountry
	 */
	public String getFromCountry() {
		return fromCountry;
	}
	/**
	 * @return the toCountry
	 */
	public String getToCountry() {
		return toCountry;
	}
	/**
	 * @return the numArmies
	 */
	public int getNumArmies() {
		return numArmies;
	}
	
	
	 
	
	
}
