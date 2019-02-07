package beans;

/**
 * @author vanduong
 *
 */
public class Continent {
	private String name;
	private int maxArmies;
	/**
	 * @param name
	 * @param maxArmies
	 */
	public Continent(String name, int maxArmies) {
		super();
		this.name = name;
		this.maxArmies = maxArmies;
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
	 * @return the maxArmies
	 */
	public int getMaxArmies() {
		return maxArmies;
	}
	/**
	 * @param maxArmies the maxArmies to set
	 */
	public void setMaxArmies(int maxArmies) {
		this.maxArmies = maxArmies;
	}
	
}