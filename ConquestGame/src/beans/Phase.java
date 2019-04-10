/**
 * 
 */
package beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Enum Phase.
 *
 * @author vanduong
 */
public enum Phase implements Serializable {
	/** the enforcement phase. */
	REENFORCEMENT(0),
	
	/** the attack phase. */
	ATTACK(1),
	
	/** the Fortification. */
	FORTIFICATION(2);
	
	/** The input value. */
	private final int inputValue;
	
	/**
	 * Instantiates a new phase.
	 *
	 * @param input the input
	 */
	Phase(int input){
		this.inputValue = input;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return inputValue;
	}
	
	
	/**
	 * Gets the phase.
	 *
	 * @param val the val
	 * @return the phase
	 */
	public static Phase getPhase(int val) {
		Phase phase = null;
		val = val %3;
		switch(val) {
		case 0:
			phase = Phase.REENFORCEMENT;
			break;
		case 1:
			phase = Phase.ATTACK;
			break;
		case 2:
			phase = Phase.FORTIFICATION;
			break;
		default:
			phase = Phase.REENFORCEMENT;
		}
		
		return phase;
		
	}
	
}
