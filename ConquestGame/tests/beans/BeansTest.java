/**
 * 
 */
package beans;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vanduong
 * Test model classes: Player, Country, WorldMap...
 */
public class BeansTest {
	
	private Player player;
	
	private Country vn;
	private Country indi;
	private Country usa;
	private Country can;
	private Country mex;
	private Country eng;
	private Country ger;
	private Country france;
	private Country rus;
	private Country china;
	private Country congo;
	private Country ugan;
	
	private Continent america;
	private Continent europe;
	private Continent asia;
	private Continent africa;
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new Player("PlayerOne");
		
		vn = new Country("Vietnam");
		indi = new Country("India");
		usa = new Country("USA");
		can = new Country("Canada");
		mex = new Country("Mexico");
		eng = new Country("England");
		ger = new Country("Germany");
		france = new Country("France");
		rus	= new Country("Russia");
		china = new Country("China");
		congo = new Country("Congo");
		ugan = new Country("Uganda");
		
		america = new Continent("Ameria", 3);
		europe = new Continent("Europe", 2);
		asia = new Continent("Asia", 3);
		africa = new Continent("Africa", 1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlayer() {
		
		//By default the player is treated as human.
		assertTrue(player.isHuman());
		
		player.addCountry(vn.getName(), vn);
		player.addCountry(indi.getName(), indi);
		player.addCountry(usa.getName(), usa);
		player.addCountry(can.getName(), can);
		
		assertEquals(player.getPlayerCountries().size(), 4);
		assertEquals(player.getPlayerName(), "PlayerOne");
		assertNotEquals(player.getArmies(), 1);
		
		Country[] countryObjs = {rus, china, ger, ugan};
		String[] countryNames = {rus.getName(), china.getName(),
				ger.getName(), ugan.getName()};
		
		player.addCountries(countryNames, countryObjs);
		assertEquals(player.getPlayerCountries().size(), 8);
		
		Continent[] continentObjs = {america, europe, asia};
		String[] continentNames = {america.getName(), europe.getName(),
									asia.getName()};
		
		player.addContinent(africa.getName(), africa);
		assertNotEquals(player.getPlayerContinents().size(), 0);
		
		player.addContinents(continentNames, continentObjs);
		assertEquals(player.getPlayerContinents().size(), 4);
		
		
	}
	
	@Test
	public void testCountry() {
		eng.setOwner(player);
		
		List<String> adjacentCountries = new ArrayList<String>();
		adjacentCountries.add(indi.getName());
		adjacentCountries.add(china.getName());
		
		assertEquals(eng.getNumArmies(), 0);
		assertEquals(eng.getOwner(), player);
		eng.setAdjacentCountries(adjacentCountries);
		
		assertSame(adjacentCountries, eng.getAdjacentCountries());
		assertNotNull(eng.getlongitude());
		assertNotNull(eng.getLatitude());
		
	}
	
	@Test
	public void testContinent() {
		america.setOwner(player);
		
		List<Country> country = new ArrayList<Country>();
		country.add(mex);
		
		assertNotEquals(america.getMaxArmies(), 0);
		assertEquals(america.getOwner(), player);
		america.setCountries(country);
		
		assertNotNull(america.getCountries());
		
	}

}
