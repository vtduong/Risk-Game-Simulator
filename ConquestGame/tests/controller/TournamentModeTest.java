package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Player;
import exception.MapInvalidException;
import strategies.BenevolentStrategy;
import strategies.CheaterStrategy;
/**
 * 
 * @author yadavsurbhi
 *
 */

public class TournamentModeTest {
	private Player player;
	private GameController controller;
	private Player gamer1= null;
	private Player gamer2= null;
	private Player gamer3= null;
	private Player gamer4= null;
	
	@Before
	public void setUp() {
		controller = GameController.getInstance();
		gamer1 = new Player("gamer1");
		controller.addPlayer(gamer1);
		gamer1.setStrategy(new CheaterStrategy(gamer1));
		
		gamer2 = new Player("gamer2");
		controller.addPlayer(gamer2);
		gamer2.setStrategy(new CheaterStrategy(gamer2));
		
		gamer3 = new Player("gamer3");
		controller.addPlayer(gamer3);
		gamer3.setStrategy(new BenevolentStrategy(gamer3));
		
		gamer4 = new Player("gamer4");
		controller.addPlayer(gamer4);
		gamer4.setStrategy(new CheaterStrategy(gamer4));
	}
	
	/**
	 * @throws Exception invalid input
	 */
	@After
	public void tearDown() throws Exception {
		gamer1 = null;
		controller = null;
	}
	
	/**
	 * @throws ClassNotFoundException invalid class called
	 * @throws IOException invalid input 
	 * @throws MapInvalidException invalid map input
	 */
	@Test
	public void TournamentTest() throws ClassNotFoundException, IOException, MapInvalidException {
	
		controller.setTest(true);
		controller.setMapInput("SmallMap,MediumMap");
		controller.setStrategyInput("Aggressive,Benevolent");
		controller.setTournamentFlag(true);
		controller.setTurnCount(10);
		controller.setGameCount(2);
		assertEquals(10, controller.modeTournament().length);
	}
	
}
