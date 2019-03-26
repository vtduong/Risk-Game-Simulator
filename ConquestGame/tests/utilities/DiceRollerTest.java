package utilities;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class DiceRollerTest.
 */
public class DiceRollerTest {
	
	/** The dice. */
	private DiceRoller dice;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		dice = DiceRoller.getInstance();
	}
	
	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		
	}
	
	/**
	 * Dice test.
	 */
	@Test
	public void diceTest() {
		int dices = 3;
		//Checking if the dices produces 3 rolls or not
		assertEquals(dices, dice.roll(3).length);
		assertNotEquals(dices, dice.roll(5).length);
		
		//Check if the results is in decreasing order or not.
		int[] diceResult = dice.roll(5);
		assertTrue(diceResult.length == 5);
		assertTrue(diceResult[0] >= diceResult[4]);
	}
}
