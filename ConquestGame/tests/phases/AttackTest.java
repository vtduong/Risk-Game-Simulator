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
import utilities.CustomMapGenerator;
import utilities.DiceRoller;


// TODO: Auto-generated Javadoc
/**
 * The Class AttackTest.
 *
 * @author vanduong
 */
public class AttackTest {
	
	/** The controller. */
	private GameController controller = null;
	
	/** The dicer. */
	private static DiceRoller dicer = DiceRoller.getInstance(0);
	
	/** The vn. */
	private Country vn = null;
	
	/** The indi. */
	private Country indi = null;
	
	/** The usa. */
	private Country usa = null;
	
	/** The germany. */
	private Country germany = null;
	
	/** The asia. */
	private Continent asia = null;
	
	/** The africa. */
	private Continent africa = null;
	
	/** The europe. */
	private Continent europe = null;
	
	/** The map. */
	private CustomMapGenerator map = null;
	
	/** The gamer 1. */
	Player gamer1 = null;
	
	/** The gamer 2. */
	Player gamer2 = null;
	
	/** The gamer 3. */
	Player gamer3 = null;
	
	/** The gamer 4. */
	Player gamer4 = null;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		map = CustomMapGenerator.getInstance();
		map.LoadMap("testAttack");
		asia = map.getContinent("Asia");
		africa = map.getContinent("Africa");
		europe = map.getContinent("Europe");
		
		vn = map.getCountry("Vietnam");
		indi = map.getCountry("India");
		usa = map.getCountry("USA");
		germany = map.getCountry("Germany");
		
		gamer1 = new Player("gamer1");
		gamer1.setArmies(4);
		gamer1.addCountry(vn.getName(), vn);
		
		gamer2 = new Player("gamer2");
		gamer2.setArmies(4);
		gamer2.addCountry(usa.getName(), usa);
		gamer2.addContinent(africa.getName(), africa);
		
		gamer3 = new Player("gamer3");
		gamer3.setArmies(1);
		gamer3.addCountry(indi.getName(), indi);
		
		gamer4 = new Player("gamer4");
		gamer4.setArmies(1);
		gamer4.addCountry(germany.getName(), germany);
		gamer4.addContinent(europe.getName(), europe);
		
		vn.setNumArmies(gamer1.getArmies());
		indi.setNumArmies(gamer3.getArmies());
		usa.setNumArmies(gamer2.getArmies());
		germany.setNumArmies(gamer4.getArmies());


		
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test invalid defender roll.
	 */
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
	
	/**
	 * Test invalid attacker roll.
	 */
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
	
	/**
	 * Test valid roll.
	 */
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
	
	/**
	 * Test go to battle.
	 */
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
	
	/**
	 * Test invade.
	 *
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 */
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
	
	/**
	 * Test capture country.
	 *
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
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
	
	/**
	 * Test all out mode.
	 */
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
	
	/**
	 * Test continent invasion.
	 *
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	@Test
	public void testContinentInvasion() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int[] attackerDice = {6, 3, 1};
		int[] defenderDice = {5};//defender loses 1 round, USA is about invade Germany
		int[] result = gamer2.goToBattle(attackerDice, defenderDice);
		
		assertEquals("Africa", gamer2.getPlayerContinents().get(0).getName() );
		//Using reflection API to call private methods
		Class reflectPhase = gamer2.getClass();
		Method reflectMethodCall = reflectPhase.getDeclaredMethod("invade",
				int[].class, Country.class, Country.class, int.class);
		
		reflectMethodCall.setAccessible(true);
		
		
		reflectMethodCall.invoke(gamer2, result, usa, germany, 3);
		
		assertEquals(2, gamer2.getPlayerContinents().size());
		assertTrue(gamer2.getPlayerContinents().contains(africa));
		assertTrue(gamer2.getPlayerContinents().contains(europe));
	}

}
