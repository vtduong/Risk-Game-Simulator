package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import beans.*;
import gui.GUI;
import phases.ReEnforcement;
import phases.TurnPhase;
import utilities.DiceRoller;
import utilities.Tuple;

/*
 * @description :
 * @author
 */
public class GameController {
	
	
	private static GameController controller= null;
//	HashMap<Player,WorldMap> countryOwnership = new HashMap<Player,WorldMap>();
	int numberOfPlayers;
	Map<Player, ArrayList<Country>> countryOwnership = null;
	TurnPhase currentPhase = null;
	
	GameController(){
		countryOwnership = new HashMap();
	}
	
	
	public static GameController getInstance() {
		if(controller == null) {
			return new GameController();
		}
		return controller;
	}
	
	/*
	 * @description :
	 * @author
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GameController controller = new GameController();
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
	public void setPhase(TurnPhase turnPhase) {
		currentPhase = turnPhase;
	}
    
	public void takeTurns() {
		currentPhase = new ReEnforcement();
		while(currentPhase != null) {
			try {
				currentPhase.nextPhase(this);
			}catch(IllegalArgumentException e) {
				GUI.handleExceptions(e.getMessage());
			}
			
		}
	}

	/**
	 * @return current player
	 * @author Van
	 */
	public Player getCurrentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void initGame() {
		// TODO 
	}
	
	
	/**
	 * evenly distributes countries among players in a random fashion 
	 * @author vanduong
	 * @param countries
	 * @param players
	 */
	public void randomizeCountryDistribution(List<Country> countries, List<Player> players) {
	    Random rand = new Random();
	    int numCountriesPerPick = 1;
	    int playerIdx = 0;
	    Map<String, ArrayList<String>> list = new HashMap();
	    //players take turn to add a country to their occupied_list until the unoccupied country list is empty
	    while(countries.size() >= 0) {
	    	//if playerIdx >= playerList size, reset playerIdx
	    	playerIdx = playerIdx % players.size();
	    	Player player = players.get(playerIdx);
	    	int randIdx = rand.nextInt(countries.size());
	    	Country country = countries.get(randIdx);
	    	player.addCountry(country.getName(), country);
	    	playerIdx++;
	    	countries.remove(randIdx); 
	    }
	    
	    
	}
	/**
	 * Asks GUI to ask user to input number of armies to be distributed to each occupied countries
	 * @return
	 */
	public Map<Country, Integer> distributeArmies() {
		
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Ask GUI to ask user for permission to continue fortifying countries
	 */
	public boolean isDoneFortification() {
		return GUI.isDoneFortification();
		
	}


	/**
	 * @return a set of 3 objects: country to move armies from, country to move armies to, and number of armies
	 * 
	 */
	public Tuple getParamsForFortification() {
		return GUI.getFortificationInfo();
		
	}
	// TODO implement the method
}
