package beans;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerTest.
 */
public class PlayerTest {

/** The player. */
private Player player;
	
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

}
