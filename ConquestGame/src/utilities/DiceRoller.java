package utilities;
import java.util.Arrays;
import java.util.Random;

/**
 * <p>This class is used to create a single-ton object that is used
 * to generate a random number between range 1 to 6. </p>
 *
 */

public class DiceRoller {

	private static DiceRoller dice_roller = null;
	private Random rand;
		
	/**
	 * This is a default constructor
	 *
	 */
	private DiceRoller() {
		// Prevents others to directly create a object of this class
		rand = new Random();
	}
	
	/**
	 * @return new instance of the DiceRoller if it not already
	 * exist, otherwise return the existing instance of DiceRoller
	 */
	public static DiceRoller getInstance() {
		if(dice_roller == null) 
			dice_roller = new DiceRoller();
		
		return dice_roller;
			
	}
	
	/**
	 * @param number_dices It is input for the number of dices we
	 * want to roll.
	 * 
	 * @return The values of rolled dices.
	 */
	public int[] roll(int number_dices) {
		int[] rolls = new int[number_dices];
		
		for(int i = 0; i<= number_dices; i++) {
			rolls[i] = 1 + rand.nextInt(6 -1 + 1);
		}
		
		Arrays.sort(rolls);
		return rolls;
	}
	
	
}
