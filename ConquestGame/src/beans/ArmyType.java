/**
 * 
 */
package beans;

/**
 * enum for army types: Infantry, Cavalry, Artillery
 * @author vanduong
 *
 */
public enum ArmyType {
	INFANTRY (1),
	CAVALRY (5),
	ARTILLERY (10);
	
	private final int numArmies;
	ArmyType(int numArmies){
		this.numArmies = numArmies;
	}
}
