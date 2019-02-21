package phases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Continent;
import beans.Country;
import beans.Player;
import controller.GameController;

public class FortificationTest {
	
	GameController controller = null;
	Fortification phase = null;
	Country vn = new Country("Vietnam");
	Country indi = new Country("India");
	
	Continent asia = new Continent("Asia", 3);
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		controller.addPlayer(new Player("gamer1"));
	}
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {	
	}
	
	@Test
	public void testMoveArmies() {
		Player gamer1 = controller.getPlayer(0);
		
		//add one continent to the player
		gamer1.addContinent(asia.getName(), asia);
		String[] countryNames = {vn.getName(), indi.getName()};
		Country[] countries = {vn, indi};
		//add 2 countries to gamer1
		gamer1.addCountries(countryNames, countries);
		controller.setCurrentPlayer(gamer1);
		//create phase
		phase = new Fortification();
		assertEquals(2, gamer1.getPlayerCountries().size());
		assertEquals(1, gamer1.getPlayerContinents().size());
		
		// TO-DO. Still working on it.
	}
	
}
