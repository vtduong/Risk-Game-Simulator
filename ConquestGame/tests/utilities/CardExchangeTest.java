package utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Player;
import controller.GameController;
import gui.CardExchangeView;

/**
 * This class tests the Card Exchange methods
 * @author yadavsurbhi
 *
 */

public class CardExchangeTest {
	private CardExchangeView cardView;
	private Player player;
	private GameController controller;
	
	  List<String> cardSet1; List<String> cardSet2; List<String> cardSet3;
	  List<String> cardSet4;
	 
	private Player gamer1;

	@Before
	public void setUp() {
		cardView = new CardExchangeView();
		controller = GameController.getInstance();
		gamer1 = new Player("gamer1");
		
		controller.addPlayer(gamer1);
		controller.setCurrentPlayer(gamer1);
		
		  cardSet1 = new ArrayList<String>(); cardSet2 = new ArrayList<String>();
		  cardSet3 = new ArrayList<String>(); cardSet4 = new ArrayList<String>();
		 
	}

	@After
	public void tearDown() throws Exception {
		gamer1 = null;
		controller = null;
	}

	@Test
	public void addCardsTest() {
		assertNotNull(gamer1.addCards());
		assertTrue(gamer1.addCards().size() > 1);
	}@Test
	public void cardSet1Test() {
		//cardView = new CardExchangeView();
		//cardSet1 = Arrays.asList("INFANTRY", "CAVALRY", "ARTILLERY");
		System.out.println("Check:" + gamer1==null);
		gamer1.setCardToRemove("INFANTRY");
		gamer1.setCardToRemove("CAVALRY");
		gamer1.setCardToRemove("ARTILLERY");
		assertFalse(cardView.isExchangeCardsPossible());
	}

	public void cardSet2Test() {
		//cardView = new CardExchangeView();
		//cardSet1 = Arrays.asList("INFANTRY", "CAVALRY", "ARTILLERY");
		System.out.println("Check:" + gamer1==null);
		gamer1.setCardToRemove("INFANTRY");
		gamer1.setCardToRemove("INFANTRY");
		gamer1.setCardToRemove("INFANTRY");
		assertFalse(cardView.isExchangeCardsPossible());
	}
	public void cardSet3Test() {
		//cardView = new CardExchangeView();
		//cardSet1 = Arrays.asList("INFANTRY", "CAVALRY", "ARTILLERY");
		System.out.println("Check:" + gamer1==null);
		gamer1.setCardToRemove("CAVALRY");
		gamer1.setCardToRemove("CAVALRY");
		gamer1.setCardToRemove("CAVALRY");
		assertFalse(cardView.isExchangeCardsPossible());
	}
	public void cardSet4Test() {
		//cardView = new CardExchangeView();
		//cardSet1 = Arrays.asList("INFANTRY", "CAVALRY", "ARTILLERY");
		System.out.println("Check:" + gamer1==null);
		gamer1.setCardToRemove("ARTILLERY");
		gamer1.setCardToRemove("ARTILLERY");
		gamer1.setCardToRemove("ARTILLERY");
		assertFalse(cardView.isExchangeCardsPossible());
	}
	
}
