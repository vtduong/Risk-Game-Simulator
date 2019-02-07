/**
 * 
 */
package beans;

/**
 * enum for card sets, representing different sets of 3 RISK cards
 * @author vanduong
 *
 */
public enum CardSet {
	ALL_INFANTRY(1),
	ALL_CAVALRY(2),
	ALL_ARTILLERY(3),
	ONE_EACH(4);
	
	private final int inputValue;
	
	CardSet(int input){
		this.inputValue = input;
	}
	public static CardSet convertInputToType(int input) {
		for(CardSet c : CardSet.values()) {
			if(c.getInputValue() == input) {
				return c;
			}
		}
		return null;
	}
	
	public int getInputValue() {
		return inputValue;
	}
	
	public String toString() {
		return this.toString();
	}
}
