package phases;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Continent;
import beans.Country;
import beans.Player;
import controller.GameController;
import utilities.DiceRoller;


/**
 * 
 * @author vanduong
 *
 */
public class AttackTest {
	private GameController controller = null;
	private static DiceRoller dicer = DiceRoller.getInstance(0);
	private Country vn = null;
	private Country indi = null;
	private Country usa = null;
	
	Continent asia = null;
	Player gamer1 = null;
	Player gamer2 = null;
	Player gamer3 = null;

	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		asia = new Continent("Asia", 5);
		vn = new Country("Vietnam");
		indi = new Country("India");
		usa = new Country("USA");
		
		gamer1 = new Player("gamer1");
		gamer1.setArmies(4);
		gamer1.addCountry(vn.getName(), vn);
		
		gamer2 = new Player("gamer2");
		gamer2.setArmies(4);
		gamer2.addCountry(usa.getName(), usa);
		
		gamer3 = new Player("gamer3");
		gamer3.setArmies(1);
		gamer3.addCountry(indi.getName(), indi);
		
		vn.setNumArmies(gamer1.getArmies());
		indi.setNumArmies(gamer3.getArmies());
		usa.setNumArmies(gamer2.getArmies());


		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInvalidDefenderRoll() {
		int[] result = null;
		try {
			result = gamer1.rollDiceAttacker(indi, 3);

		}catch(IllegalArgumentException e) {
			assertNull(result);
		}
		
		try {
			result = gamer1.rollDiceAttacker(indi, 4);

		}catch(IllegalArgumentException e) {
			assertNull(result);
		}
	}
	
	@Test
	public void testInvalidAttackerRoll() {
		int[] result = null;
		try {
			result = gamer1.rollDiceDefender(indi, 2);

		}catch(IllegalArgumentException e) {
			assertNull(result);
		}
		
		try {
			result = gamer1.rollDiceDefender(indi, 3);

		}catch(IllegalArgumentException e) {
			assertNull(result);
		}
	}
	
	@Test
	public void testValidRoll() {
		int[] result = null;
		try {
			result = gamer1.rollDiceAttacker(vn, 3);
			assertNotNull(result);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		int[] result1 = null;
		try {
			result1 = gamer1.rollDiceDefender(vn, 2);
			assertNotNull(result);
		}catch(IllegalArgumentException e) {
			fail();
		}
	}
	
	@Test
	public void testGoToBattle() {
		int[] attackerDice = {6, 3, 1};
		int[] defenderDice = {6, 4};//defender wins both rounds
		int[] result = gamer1.goToBattle(attackerDice, defenderDice);
		assertEquals(2, result[0]);
		assertEquals(0, result[1]);
		
		int[] attackerDice1 = {6, 3, 1};
		int[] defenderDice1 = {5, 2};//defender loses both rounds
		int []result1 = gamer1.goToBattle(attackerDice1, defenderDice1);
		assertEquals(0, result1[0]);
		assertEquals(2, result1[1]);
		
		int[] attackerDice2 = {6, 3, 1};
		int[] defenderDice2 = {5, 4};//each loses 1 round
		int []result2 = gamer1.goToBattle(attackerDice2, defenderDice2);
		assertEquals(1, result2[0]);
		assertEquals(1, result2[1]);
		
		int[] attackerDice3 = {6};
		int[] defenderDice3 = {5,1};//defender loses 1 round
		int []result3 = gamer1.goToBattle(attackerDice3, defenderDice3);
		assertEquals(1, result3[1]);
	}
	
	@Test
	public void testInvade() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		int[] attackerDice = {6, 3, 1};
		int[] defenderDice = {6, 4};//defender wins both rounds
		int[] result = gamer2.goToBattle(attackerDice, defenderDice);
		
		//Using reflection API to call private methods
		Class reflectPhase = gamer2.getClass();
		Method reflectMethodCall = reflectPhase.getDeclaredMethod("invade",
				int[].class, Country.class, Country.class, int.class);
		
		reflectMethodCall.setAccessible(true);
		
		
		reflectMethodCall.invoke(gamer2, result, usa, vn, 3);
		
		assertEquals(2, usa.getNumArmies());
		assertEquals(4, vn.getNumArmies());
		assertEquals(2, gamer2.getArmies());
		assertEquals(4, gamer1.getArmies());
		
	}
	
	@Test
	public void testCaptureCountry() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int[] attackerDice = {6, 3, 1};
		int[] defenderDice = {5};//defender loses 1 round, india is about to be captured by usa
		int[] result = gamer2.goToBattle(attackerDice, defenderDice);
		
		//Using reflection API to call private methods
		Class reflectPhase = gamer2.getClass();
		Method reflectMethodCall = reflectPhase.getDeclaredMethod("invade",
				int[].class, Country.class, Country.class, int.class);
		
		reflectMethodCall.setAccessible(true);
		
		
		reflectMethodCall.invoke(gamer2, result, usa, indi, 3);
		
		assertEquals(1, usa.getNumArmies());
		assertEquals(3, indi.getNumArmies());
		assertEquals("gamer2", indi.getOwner().getPlayerName());
		assertEquals(2, gamer2.getPlayerCountries().size());
		assertEquals(0, gamer3.getPlayerCountries().size());
	}
	
	@Test
	public void testAllOutMode() {
		int initialArmies = usa.getNumArmies();
		gamer2.goAllOut(usa, indi);
		if(indi.getOwner() != gamer2) {//if india does not get invaded, means usa has at most 1 army
			assertEquals(1, usa.getNumArmies());
		} else {//if usa occupies india, means there are less armies in usa than before all-out attack
			assertTrue(usa.getNumArmies() < initialArmies);
			assertTrue(indi.getNumArmies() > 0);
		}
	}

}
