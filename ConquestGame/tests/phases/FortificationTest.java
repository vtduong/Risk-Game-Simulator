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

public class FortificationTest {
	
	private GameController controller = null;
	private Fortification phase = null;
	private Country vn = null;
	private Country indi = null;
	private Country usa = null;
	
	Continent asia = null;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		controller.addPlayer(new Player("gamer1"));
		asia = new Continent("Asia", 5);
		vn = new Country("Vietnam");
		vn.setNumArmies(4);
		indi = new Country("India");
		indi.setNumArmies(1);
		usa = new Country("USA");
		usa.setNumArmies(4);
	}
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {	
	}
	
	@Test
	public void testMoveArmies() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Player gamer1 = controller.getPlayer(0);
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
		//create phase
		phase = new Fortification();
		assertEquals(3, gamer1.getPlayerCountries().size());
		assertEquals(1, gamer1.getPlayerContinents().size());
		
		//Using reflection API to call private methods
		Class reflectPhase = phase.getClass();
		Method reflectMethodCall = reflectPhase.getDeclaredMethod("moveArmies",
				String.class, String.class, int.class);
		
		reflectMethodCall.setAccessible(true);
		
		int testValue = 2;
		reflectMethodCall.invoke(phase, countryOne, countryTwo, testValue);
		
		assertEquals(2, vn.getNumArmies());
		assertEquals(3, indi.getNumArmies());
	}
	
	@Test
	public void testInvalidMove() throws NoSuchMethodException, SecurityException {
		Player gamer1 = controller.getPlayer(0);
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
		//create phase
		phase = new Fortification();
		assertEquals(2, gamer1.getPlayerCountries().size());
		assertEquals(1, gamer1.getPlayerContinents().size());
		
		//Using reflection API to call private methods
		Class reflectPhase = phase.getClass();
		Method reflectMethodCall;
		
			reflectMethodCall = reflectPhase.getDeclaredMethod("moveArmies",
					String.class, String.class, int.class);
			
		
		
		reflectMethodCall.setAccessible(true);
		
		int testValue = 1;
		try {
			reflectMethodCall.invoke(phase, countryTwo, countryOne, testValue);
			fail();
		} catch (IllegalAccessException e) {
			fail();
		} catch (InvocationTargetException e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException );
		}
	}
	
	@Test
	public void testInvalidCountries() throws NoSuchMethodException, SecurityException {
		Player gamer1 = controller.getPlayer(0);
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
		//create phase
		phase = new Fortification();
		assertEquals(3, gamer1.getPlayerCountries().size());
		assertEquals(1, gamer1.getPlayerContinents().size());
		
		//Using reflection API to call private methods
		Class reflectPhase = phase.getClass();
		Method reflectMethodCall = reflectPhase.getDeclaredMethod("moveArmies",
				String.class, String.class, int.class);
		
		reflectMethodCall.setAccessible(true);
		
		int testValue = 2;
		try {
			reflectMethodCall.invoke(phase, countryOne, country3, testValue);
			fail();
		} catch (IllegalAccessException e) {
			fail();
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println(e.getClass().getName());
			assertTrue(e.getCause() instanceof IllegalArgumentException );
		}
	}
	
}
