package beans;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContinentTest {
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

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
america.setOwner(player);
		
		List<Country> country = new ArrayList<Country>();
		country.add(mex);
		
		assertNotEquals(america.getMaxArmies(), 0);
		assertEquals(america.getOwner(), player);
		america.setCountries(country);
		
		assertNotNull(america.getCountries());
	}

}
