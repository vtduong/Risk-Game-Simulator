/**
 * 
 */
package beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * enum for army types: Infantry, Cavalry, Artillery.
 *
 * @author vanduong
 */
public enum ArmyType implements Serializable{
	
	/** The infantry. */
	INFANTRY (1),
	
	/** The cavalry. */
	CAVALRY (5),
	
	/** The artillery. */
	ARTILLERY (10);
	
	/** The num armies. */
	private final int numArmies;
	
	/**
	 * Instantiates a new army type.
	 *
	 * @param numArmies the num armies
	 */
	ArmyType(int numArmies){
		this.numArmies = numArmies;
	}
}
