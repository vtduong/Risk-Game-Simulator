/**
 * 
 */
package beans;

// TODO: Auto-generated Javadoc
/**
 * enum for card sets, representing different sets of 3 RISK cards.
 *
 * @author vanduong
 */
public enum CardSet {
	
	/** The all infantry. */
	ALL_INFANTRY(1),
	
	/** The all cavalry. */
	ALL_CAVALRY(2),
	
	/** The all artillery. */
	ALL_ARTILLERY(3),
	
	/** The one each. */
	ONE_EACH(4);
	
	/** The input value. */
	private final int inputValue;
	
	/**
	 * Instantiates a new card set.
	 *
	 * @param input the input
	 */
	CardSet(int input){
		this.inputValue = input;
	}
	
	/**
	 * Convert input to type.
	 *
	 * @param input the input
	 * @return the card set
	 */
	public static CardSet convertInputToType(int input) {
		for(CardSet c : CardSet.values()) {
			if(c.getInputValue() == input) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Gets the input value.
	 *
	 * @return the input value
	 */
	public int getInputValue() {
		return inputValue;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.toString();
	}
}
