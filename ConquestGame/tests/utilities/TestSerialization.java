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

// TODO: Auto-generated Javadoc
/**
 * The Class TestSerialization.
 *
 * @author vanduong
 */
public class TestSerialization {
	
	/** The controller. */
	private static GameController controller = null;
	
	/** The saver. */
	private GameStat saver = null;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		controller.setCurrentPhase(Phase.ATTACK);
		controller.setCurrentPlayer(new Player("gamer1"));
		saver = GameStat.getInstance();
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
		try {
			saver.save();
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
		GameInit savedController = null;
		try {
			  savedController = saver.load();
		} catch (ClassNotFoundException | IOException | MapInvalidException e) {
			fail();
			e.printStackTrace();
		}
	
		assertEquals(Phase.ATTACK, savedController.currentPhase);
		
	}
	
	/**
	 * Test loading.
	 */
	@Test
	public void testLoading() {
		GameInit savedController = null;
		try {
			saver.save();
			savedController = saver.load();
		} catch (ClassNotFoundException | IOException | MapInvalidException e) {
			e.printStackTrace();
		}
		
		assertEquals("gamer1", savedController.currentPlayer.getPlayerName());
	}

}
