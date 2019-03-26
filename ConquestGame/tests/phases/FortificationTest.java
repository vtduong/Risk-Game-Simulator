package phases;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Continent;
import beans.Country;
import beans.Player;
import controller.GameController;

// TODO: Auto-generated Javadoc
/**
 * The Class FortificationTest.
 */
public class FortificationTest {
	
	/** The controller. */
	private GameController controller = null;
	
	/** The vn. */
	private Country vn = null;
	
	/** The indi. */
	private Country indi = null;
	
	/** The usa. */
	private Country usa = null;
	
	/** The asia. */
	Continent asia = null;
	
	/** The gamer 1. */
	Player gamer1 = null;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		gamer1 = new Player("gamer1");
		asia = new Continent("Asia", 5);
		vn = new Country("Vietnam");
		vn.setNumArmies(4);
		indi = new Country("India");
		indi.setNumArmies(1);
		usa = new Country("USA");
		usa.setNumArmies(4);
	}
	
	
	
	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {	
		gamer1 = null;
	}
	
	/**
	 * Test move armies.
	 *
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	@Test
	public void testMoveArmies() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Player gamer1 = controller.getPlayer(0);
		String countryOne = vn.getName();
		String countryTwo = indi.getName();
		
		//add one continent to the player
		gamer1.addContinent(asia.getName(), asia);
		String[] countryNames = {countryOne, countryTwo};
		Country[] countries = {vn, indi};
		//set vn and indi to be adjacent
		List<String> adj = new ArrayList<String>();
		adj.add(countryTwo);
		vn.setAdjacentCountries(adj);
		List<String> adjVN = new ArrayList<String>();
		adjVN.add(countryOne);
		indi.setAdjacentCountries(adjVN);
		//add 2 countries to gamer1
		gamer1.addCountries(countryNames, countries);
		controller.setCurrentPlayer(gamer1);
		
		assertEquals(2, gamer1.getPlayerCountries().size());
		assertEquals(1, gamer1.getPlayerContinents().size());
		
		//Using reflection API to call private methods
		Class reflectPhase = controller.getCurrentPlayer().getClass();
		Method reflectMethodCall = reflectPhase.getDeclaredMethod("moveArmies",
				String.class, String.class, int.class);
		
		reflectMethodCall.setAccessible(true);
		
		int testValue = 2;
		reflectMethodCall.invoke(controller.getCurrentPlayer(), countryOne, countryTwo, testValue);
		
		assertEquals(2, vn.getNumArmies());
		assertEquals(3, indi.getNumArmies());
	}
	
	/**
	 * Test invalid move.
	 *
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 */
	@Test
	public void testInvalidMove() throws NoSuchMethodException, SecurityException {
		//Player gamer1 = controller.getPlayer(0);
		String countryOne = vn.getName();
		String countryTwo = indi.getName();
		
		//add one continent to the player
		gamer1.addContinent(asia.getName(), asia);
		String[] countryNames = {countryOne, countryTwo};
		Country[] countries = {vn, indi};
		//set vn and indi to be adjacent
		List<String> adj = new ArrayList<String>();
		adj.add(countryTwo);
		vn.setAdjacentCountries(adj);
		List<String> adjVN = new ArrayList<String>();
		adjVN.add(countryOne);
		indi.setAdjacentCountries(adjVN);
		//add 2 countries to gamer1
		gamer1.addCountries(countryNames, countries);
		controller.setCurrentPlayer(gamer1);
		
		assertEquals(2, gamer1.getPlayerCountries().size());
		assertEquals(1, gamer1.getPlayerContinents().size());
		
		//Using reflection API to call private methods
		Class reflectPhase = controller.getCurrentPlayer().getClass();
		Method reflectMethodCall;
		
			reflectMethodCall = reflectPhase.getDeclaredMethod("moveArmies",
					String.class, String.class, int.class);
			
		
		
		reflectMethodCall.setAccessible(true);
		
		int testValue = 1;
		try {
			reflectMethodCall.invoke(controller.getCurrentPlayer(), countryTwo, countryOne, testValue);
			fail();
		} catch (IllegalAccessException e) {
			fail();
		} catch (InvocationTargetException e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException );
		}
	}
	
	/**
	 * Test invalid countries.
	 *
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 */
	@Test
	public void testInvalidCountries() throws NoSuchMethodException, SecurityException {
		//Player gamer1 = controller.getPlayer(0);
		String countryOne = vn.getName();
		String countryTwo = indi.getName();
		String country3 = usa.getName();
		
		//add one continent to the player
		gamer1.addContinent(asia.getName(), asia);
		String[] countryNames = {countryOne, countryTwo, country3};
		Country[] countries = {vn, indi, usa};
		//set vn and indi to be adjacent
		List<String> adj = new ArrayList<String>();
		adj.add(countryTwo);
		vn.setAdjacentCountries(adj);
		List<String> adjVN = new ArrayList<String>();
		adjVN.add(countryOne);
		indi.setAdjacentCountries(adjVN);
		//add 3 countries to gamer1
		gamer1.addCountries(countryNames, countries);
		controller.setCurrentPlayer(gamer1);
		
		assertEquals(3, gamer1.getPlayerCountries().size());
		assertEquals(1, gamer1.getPlayerContinents().size());
		
		//Using reflection API to call private methods
		Class reflectPhase = controller.getCurrentPlayer().getClass();
		Method reflectMethodCall = reflectPhase.getDeclaredMethod("moveArmies",
				String.class, String.class, int.class);
		
		reflectMethodCall.setAccessible(true);
		
		int testValue = 2;
		try {
			reflectMethodCall.invoke(controller.getCurrentPlayer(), countryOne, country3, testValue);
			fail();
		} catch (IllegalAccessException e) {
			fail();
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException );
		}
	}
	
}
