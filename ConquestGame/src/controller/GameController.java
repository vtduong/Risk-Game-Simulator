package controller;

import java.util.HashMap;
import java.util.Scanner;

import beans.*;
import phases.TurnPhase;

/*
 * @description :
 * @author
 */
public class GameController {
	
	HashMap<Player,WorldMap> countryOwnership = new HashMap<Player,WorldMap>();
	int numberOfPlayers;
	
	/*
	 * @description :
	 * @author
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    //TODO
		
	}
	
	/*
	 * @description :
	 * @author
	 */
	public boolean loadMap(){
		//TODO
		return false;
		
	}
	
	/*
	 * @description :
	 * @author
	 */
	public boolean setPhase(TurnPhase turnPhase) {
		return false;
	}
    
	/*
	 * @description :
	 * @author
	 */
	public boolean nextPhase() {
		return false;
	}
}
