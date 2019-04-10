/**
 * 
 */
package controller;

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
import gui.UI;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerTest.
 *
 * @author vanduong
 */
public class ControllerTest {

	/** The controller. */
	GameController controller = null;

/** The ui. */
//	ReEnforcement phase = null;
	UI ui = null;
	
	/** The vn. */
	Country vn = new Country("Vietnam");
	
	/** The indi. */
	Country indi = new Country("India");
	
	/** The usa. */
	Country usa = new Country("USA");
	
	/** The can. */
	Country can = new Country("Canada");
	
	/** The mex. */
	Country mex = new Country("Mexico");
	
	/** The eng. */
	Country eng = new Country("England");
	
	/** The ger. */
	Country ger = new Country("Germany");
	
	/** The france. */
	Country france = new Country("France");
	
	/** The rus. */
	Country rus	= new Country("Russia");
	
	/** The china. */
	Country china = new Country("China");
	
	/** The congo. */
	Country congo = new Country("Congo");
	
	/** The ugan. */
	Country ugan = new Country("Uganda");
	
	/** The america. */
	Continent america = new Continent("Ameria", 3);
	
	/** The europe. */
	Continent europe = new Continent("Europe", 2);
	
	/** The asia. */
	Continent asia = new Continent("Asia", 3);
	
	/** The africa. */
	Continent africa = new Continent("Africa", 1);
	
	/** The gamer 1. */
	Player gamer1 = null;
	
	/** The gamer 2. */
	Player gamer2 = null;
	
	/** The gamer 3. */
	Player gamer3 = null;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		ui = new UI();
		gamer1 = new Player("gamer1");
		controller.addPlayer(gamer1);
		gamer2 = new Player("gamer2");
		controller.addPlayer(gamer2);
		gamer3 = new Player("gamer3");
		controller.addPlayer(gamer3);
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
	 * Test randomize country distribution.
	 */
	@Test
	public void testRandomizeCountryDistribution() {
		List<Player> players = new ArrayList<Player>();
		players.add(gamer1);
		players.add(gamer2);
		players.add(gamer3);
		
		List<Country> countries = new ArrayList<Country>();
		countries.add(eng);
		countries.add(vn);
		countries.add(indi);
		countries.add(can);
		countries.add(usa);
		countries.add(mex);
		countries.add(ger);
		countries.add(france);
		countries.add(rus);
		countries.add(china);
		countries.add(congo);
		countries.add(ugan);
		
		controller.randomizeCountryDistribution(countries, players);
		
		assertNotNull(gamer1.getPlayerCountries());
		assertNotNull(gamer2.getPlayerCountries());
		assertNotNull(gamer3.getPlayerCountries());
		
		assertEquals(4, gamer1.getPlayerCountries().size());
		assertEquals(4, gamer2.getPlayerCountries().size());
		assertEquals(4, gamer3.getPlayerCountries().size());

	}
	
	/**
	 * Test place initial num armies.
	 *
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 */
	@Test
	public void testPlaceInitialNumArmies() throws NoSuchMethodException, SecurityException {
		gamer1.setArmies(10);
		gamer2.setArmies(10);
		gamer3.setArmies(10);
		
		gamer1.addCountry(vn.getName(), vn);
		gamer1.addCountry(ger.getName(), ger);
		gamer2.addCountry(indi.getName(), indi);
		gamer3.addCountry(usa.getName(), usa);
		
		Class reflectController = controller.getClass();
		Method method;
		
		method = reflectController.getDeclaredMethod("placeInitialArmies");
		method.setAccessible(true);
		
		try {
			method.invoke(controller);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail();

		}
		assertEquals(2, gamer1.getNumArmiesDispatched());
		
	}
	
	/**
	 * Test main.
	 */
	@Test
	public void testMain() {
		
	}
	
//	@Test
//	public void testTakePhases() {
//		controller.addPlayer(gamer1);
//		controller.setCurrentPlayer(gamer1);
//		controller.takePhases();
//	}
//	
//	@Test
//	public void testTakeTurns() {
//		controller.addPlayer(gamer1);
//		controller.setCurrentPlayer(gamer1);
//		controller.takeTurns();
//	}

}
