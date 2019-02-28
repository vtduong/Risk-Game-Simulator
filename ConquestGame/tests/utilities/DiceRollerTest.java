package utilities;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiceRollerTest {
	
	private DiceRoller dice;
	
	@Before
	public void setUp() {
		dice = DiceRoller.getInstance();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void diceTest() {
		int dices = 3;
		//Checking if the dices produces 3 rolls or not
		assertEquals(dices, dice.roll(3).length);
		assertNotEquals(dices, dice.roll(5).length);
		
		//Check if the results is in decreasing order or not.
		int[] diceResult = dice.roll(2);
		assertTrue(diceResult.length == 2);
		assertTrue(diceResult[0] > diceResult[1]);
	}
}
