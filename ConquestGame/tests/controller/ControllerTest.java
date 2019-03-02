/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Continent;
import beans.Country;
import beans.Player;
import phases.ReEnforcement;

/**
 * @author vanduong
 *
 */
public class ControllerTest {

	GameController controller = null;
	ReEnforcement phase = null;
	Country vn = new Country("Vietnam");
	Country indi = new Country("India");
	Country usa = new Country("USA");
	Country can = new Country("Canada");
	Country mex = new Country("Mexico");
	Country eng = new Country("England");
	Country ger = new Country("Germany");
	Country france = new Country("France");
	Country rus	= new Country("Russia");
	Country china = new Country("China");
	Country congo = new Country("Congo");
	Country ugan = new Country("Uganda");
	
	Continent america = new Continent("Ameria", 3);
	Continent europe = new Continent("Europe", 2);
	Continent asia = new Continent("Asia", 3);
	Continent africa = new Continent("Africa", 1);
	
	Player gamer1 = null;
	Player gamer2 = null;
	Player gamer3 = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		gamer1 = new Player("gamer1");
		controller.addPlayer(gamer1);
		gamer2 = new Player("gamer2");
		controller.addPlayer(gamer2);
		gamer3 = new Player("gamer3");
		controller.addPlayer(gamer3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

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
		
	}

}
