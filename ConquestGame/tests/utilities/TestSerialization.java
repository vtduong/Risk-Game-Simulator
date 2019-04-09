/**
 * 
 */
package utilities;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Phase;
import beans.Player;
import controller.GameController;
import exception.MapInvalidException;

/**
 * @author vanduong
 *
 */
public class TestSerialization {
	private static GameController controller = null;
	private GameStat saver = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		controller.setCurrentPhase(Phase.ATTACK);
		controller.setCurrentPlayer(new Player("gamer1"));
		saver = GameStat.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			saver.save();
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
		GameController savedController = null;
		try {
			  savedController = saver.load();
		} catch (ClassNotFoundException | IOException | MapInvalidException e) {
			fail();
			e.printStackTrace();
		}
	
		assertEquals(savedController.getCurrentPhase(), controller.getCurrentPhase());
		
	}
	
	@Test
	public void testLoading() {
		GameController savedController = null;
		try {
			savedController = saver.load();
		} catch (ClassNotFoundException | IOException | MapInvalidException e) {
			e.printStackTrace();
		}
		
		assertEquals(savedController.getCurrentPlayer().getPlayerName(), controller.getCurrentPlayer().getPlayerName());
	}

}
