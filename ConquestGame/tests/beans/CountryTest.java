package beans;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class CountryTest.
 */
public class CountryTest {
	
	/** The gamer 2. */
	private Player player, gamer1, gamer2;
	
	/** The vn. */
	private Country vn;
	
	/** The indi. */
	private Country indi;
	
	/** The usa. */
	private Country usa;
	
	/** The can. */
	private Country can;
	
	/** The mex. */
	private Country mex;
	
	/** The eng. */
	private Country eng;
	
	/** The ger. */
	private Country ger;
	
	/** The france. */
	private Country france;
	
	/** The rus. */
	private Country rus;
	
	/** The china. */
	private Country china;
	
	/** The congo. */
	private Country congo;
	
	/** The ugan. */
	private Country ugan;
	
	/** The america. */
	private Continent america;
	
	/** The europe. */
	private Continent europe;
	
	/** The asia. */
	private Continent asia;
	
	/** The africa. */
	private Continent africa;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new Player("PlayerOne");
		gamer1 = new Player("gamer1");
		gamer2 = new Player("gamer2");
		
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
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
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
	
	/**
	 * Test place initial armies.
	 */
	@Test
	public void testPlaceInitialArmies() {
		
	}

}
