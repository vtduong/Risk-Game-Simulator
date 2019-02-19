/**
 * 
 */
package phases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Continent;
import beans.Country;
import beans.Player;
import controller.GameController;

/**
 * @author vanduong
 *
 */
public class ReEnforcementTest {
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
	
	Continent america = new Continent("Ameria", 3);
	Continent europe = new Continent("Europe", 2);
	Continent asia = new Continent("Asia", 3);
	Continent africa = new Continent("Africa", 1);
	
	Player gamer1 = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//add 3 players to system
		controller = GameController.getInstance();
		controller.addPlayer(new Player("gamer1"));
		controller.addPlayer(new Player("gamer2"));
		controller.addPlayer(new Player("gamer3"));

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testObtainArmies() {
		Player gamer1 = controller.getPlayer(0);
		//add 1 continent to gamer1
		gamer1.addContinent(africa.getName(), africa);
		String [] countryNames = {vn.getName(), indi.getName(), usa.getName(), can.getName()};
		Country [] countries = {vn, indi, usa, can};
		//add 4 countries to gamer1
		gamer1.addCountries(countryNames, countries );
		//set current player
		controller.setCurrentPlayer(gamer1);
		phase = new ReEnforcement();
		//create phase
		phase.obtainNewArmies();
		assertEquals(4, gamer1.getArmies());
		String [] moreCountryNames = {mex.getName(), eng.getName(), ger.getName(), rus.getName()};
		Country [] moreCountries = {mex, eng, ger, rus};
		gamer1.addCountries(moreCountryNames, moreCountries);
		assertEquals(7, gamer1.getPlayerCountries().size());
//		assertEquals(7, gamer1.getArmies());
	}

}
