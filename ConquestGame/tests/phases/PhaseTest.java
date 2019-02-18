/**
 * 
 */
package phases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Country;
import beans.Player;

/**
 * @author vanduong
 *
 */
public class PhaseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
