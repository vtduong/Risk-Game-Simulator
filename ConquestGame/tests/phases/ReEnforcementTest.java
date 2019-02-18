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
	GameController controller = GameController.getInstance();
	ReEnforcement phase = new ReEnforcement();
	Player gamer1 = new Player("gamer1");
	Player gamer2 = new Player("gamer2");
	Player gamer3 = new Player("gamer3");
	
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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		controller.getPlayer(0);
		gamer1.addContinent(africa.getName(), africa);
		String [] countryNames = {vn.getName(), indi.getName(), usa.getName(), can.getName()};
		Country [] countries = {vn, indi, usa, can};
		gamer1.addCountries(countryNames, countries );

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testObtainArmies() {
		phase.obtainNewArmies();
	}

}
