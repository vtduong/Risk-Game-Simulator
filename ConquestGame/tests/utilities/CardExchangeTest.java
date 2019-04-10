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

// TODO: Auto-generated Javadoc
/**
 * This class tests the Card Exchange methods.
 *
 * @author yadavsurbhi
 */

public class CardExchangeTest {
	
	/** The card view. */
	private CardExchangeView cardView;
	
	/** The player. */
	private Player player;
	
	/** The controller. */
	private GameController controller;
	
	  /** The card set 1. */
  	List<String> cardSet1; /** The card set 2. */
 List<String> cardSet2; /** The card set 3. */
 List<String> cardSet3;
	  
  	/** The card set 4. */
  	List<String> cardSet4;
	 
	/** The gamer 1. */
	private Player gamer1;

	/**
	 * Sets the up.
	 */
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

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		gamer1 = null;
		controller = null;
	}

	/**
	 * Adds the cards test.
	 */
	@Test
	public void addCardsTest() {
		assertNotNull(gamer1.addCards());
		assertTrue(gamer1.addCards().size() > 1);
	}
/**
 * Card set 1 test.
 */
@Test
	public void cardSet1Test() {
		//cardView = new CardExchangeView();
		//cardSet1 = Arrays.asList("INFANTRY", "CAVALRY", "ARTILLERY");
		System.out.println("Check:" + gamer1==null);
		gamer1.setCardToRemove("INFANTRY");
		gamer1.setCardToRemove("CAVALRY");
		gamer1.setCardToRemove("ARTILLERY");
		assertFalse(cardView.isExchangeCardsPossible());
	}

	/**
	 * Card set 2 test.
	 */
	public void cardSet2Test() {
		//cardView = new CardExchangeView();
		//cardSet1 = Arrays.asList("INFANTRY", "CAVALRY", "ARTILLERY");
		System.out.println("Check:" + gamer1==null);
		gamer1.setCardToRemove("INFANTRY");
		gamer1.setCardToRemove("INFANTRY");
		gamer1.setCardToRemove("INFANTRY");
		assertFalse(cardView.isExchangeCardsPossible());
	}
	
	/**
	 * Card set 3 test.
	 */
	public void cardSet3Test() {
		//cardView = new CardExchangeView();
		//cardSet1 = Arrays.asList("INFANTRY", "CAVALRY", "ARTILLERY");
		System.out.println("Check:" + gamer1==null);
		gamer1.setCardToRemove("CAVALRY");
		gamer1.setCardToRemove("CAVALRY");
		gamer1.setCardToRemove("CAVALRY");
		assertFalse(cardView.isExchangeCardsPossible());
	}
	
	/**
	 * Card set 4 test.
	 */
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
