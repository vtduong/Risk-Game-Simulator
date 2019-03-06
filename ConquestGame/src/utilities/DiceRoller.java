package utilities;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;

// TODO: Auto-generated Javadoc
/**
 * <p>This class is used to create a single-ton object that is used
 * to generate a random number between range 1 to 6. </p>
 *
 */

public class DiceRoller {

	/** The dice roller. */
	private static DiceRoller dice_roller = null;
	
	/** The rand. */
	private Random rand;
		
	/**
	 * This is a default constructor.
	 */
	private DiceRoller() {
		// Prevents others to directly create a object of this class
		rand = new Random();
	}
	
	/**
	 * Gets the single instance of DiceRoller.
	 *
	 * @return new instance of the DiceRoller if it not already exist, otherwise
	 *         return the existing instance of DiceRoller
	 */
	public static DiceRoller getInstance() {
		if(dice_roller == null) 
			dice_roller = new DiceRoller();
		
		return dice_roller;
			
	}
	
	/**
	 * Roll.
	 *
	 * @param number_dices It is input for the number of dices we want to roll.
	 * @return The values of rolled dices.
	 */
	public int[] roll(int number_dices) {
		int[] rolls = new int[number_dices];
		
		for(int i = 0; i< number_dices; i++) {
			rolls[i] = 1 + rand.nextInt(6 -1 + 1);
		}
		
		Arrays.sort(rolls);
		ArrayUtils.reverse(rolls);
		return rolls;
	}
	
	
	
}
