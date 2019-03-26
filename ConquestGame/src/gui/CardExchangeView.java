package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Observable;
import beans.Player;
import controller.GameController;

// TODO: Auto-generated Javadoc
/**
 * The Class CardExchangeView.
 *
 * @author yadavsurbhi
 */
public class CardExchangeView implements Observer {

/** The controller. */
public GameController controller;

/** The player. */
Player player;

/** The is exchange possible. */
boolean isExchangePossible= false;

/** The scan. */
Scanner scan= new Scanner(System.in);
List<String> cardsToRemoveList;


	
	/**
	 * Instantiates a new card exchange view.
	 */
	public CardExchangeView() {
		controller = GameController.getInstance();
	}
	
	/**
	 * Gets the card progress.
	 *
	 * @return the card progress
	 */
	public void getCardProgress() {
			player = controller.getCurrentPlayer();
			System.out.println("***************CARDS INVENTORY VIEW***************");
			String playerName = player.getPlayerName();
			System.out.println("*".repeat(20));
			System.out.println("PlayerName : " + playerName);
			System.out.println("Total cards acquired : "+ player.getCardsAcquired().size());
			System.out.println("Cards acquired by this player are :" + player.getCardsAcquired());
			//System.out.println("Cards in cardsToRemoveList are :" + player.getCardsToRemove());
			System.out.println("-".repeat(20));			
		}	
	
	/**
	 * Checks if is exchange cards possible.
	 *
	 * @return true, if is exchange cards possible
	 */
	public boolean isExchangeCardsPossible() {
			player = controller.getCurrentPlayer();
			cardsToRemoveList= new ArrayList<String>();
			cardsToRemoveList= player.getCardsToRemove();
			int infantry=0; int artillery=0; int cavalry=0;
			if(player.getCardsAcquired()!= null && player.getCardsAcquired().size()>=3) {
				for(String cardList : cardsToRemoveList) {
					if(cardList.equalsIgnoreCase("INFANTRY")) {
						infantry++;
					}
					if(cardList.equalsIgnoreCase("CAVALRY")) {
						cavalry++;
					}
					if(cardList.equalsIgnoreCase("ARTILLERY")) {
						artillery++;
					}
				}
				if((infantry == 1 && cavalry == 1 && artillery == 1) || infantry == 3 || cavalry == 3 || artillery == 3){
					isExchangePossible= true;
				}
			}
		return isExchangePossible;
	}

	@Override
	public void update(Observable sub) {
		getCardProgress();	
	}
}
