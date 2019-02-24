package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import beans.*;
import gui.GUI;
import phases.Attack;
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
	private boolean readyForNextPhase = false;
	private Player currentPlayer;
	private ArrayList<Player> playerList;
	private final static int MIN_ARGS = 1;
	
	GameController(){
		countryOwnership = new HashMap();
		playerList = new ArrayList<Player>();
	}
	

	public static GameController getInstance(){
		    if(controller == null){
		        synchronized (GameController.class) {
		            if(controller == null){
		                controller = new GameController();
		            }
		        }
	    }
	    return controller;
	}
	
	/*
	 * @description :
	 * @author
	 */
	public static void main(String[] args) {
		/*File inFile = null;
		if (0 < args.length) {
		   inFile = new File(args[0]);
		} else {
		   System.err.println("Invalid arguments count:" + args.length);
		   System.exit(1);
		} */
		
		/*Added to parse the default file.
		 * To provide input file use src/resources/World.map as argument
		 * 
		 */
		if (args == null || args.length < MIN_ARGS) {
            showHelp();
            return;
        }
		String inputFile = args[0];
        new utilities.MapParser(inputFile).readFile();
		GameController controller = new GameController();
	    //TODO  add create map
		
	}
	
	public static void showHelp() {
		//TODO
	}
	public void addPlayer(Player player) {
		playerList.add(player);
		numberOfPlayers++;
	}
	
	public int getNumPlayers() {
		return numberOfPlayers;
	}
	
	public Player getPlayer(int idx) {
		return playerList.get(idx);
	}
	
	/*
	 * @description :
	 * @author
	 */
	public static boolean loadMap(){
		//TODO this method should belong to GUI
		return false;
		
	}
	
	/*
	 * @description :
	 * @author
	 */
	public void setPhase(TurnPhase turnPhase) {
		currentPhase = turnPhase;
	}
    
	public void takePhases() {
		currentPhase = new ReEnforcement();
		while(currentPhase != null) {
			while(!readyForNextPhase) {
				try {
					if(currentPhase instanceof Attack) {
						if(!isWar()) {
							break;
						}
					}
					currentPhase.takePhase();
					readyForNextPhase = readyForNextPhase();
				}catch(IllegalArgumentException e) {
					GUI.handleExceptions(e.getMessage());
				}
			}
			currentPhase.setNextPhase();
		}
	}

	/**
	 * Request GUI to ask if user wants to go to war
	 * @return true if user wants to go to war
	 */
	private boolean isWar() {
		return GUI.isWar();
		
	}


	/**
	 * @return current player
	 * @author Van
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}
	
	public void initGame() {
		//TODO	get number of players in from user
		//TODO	assign each player an initial number of armies (based on risk rule)
		//each player take turn to play
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
	 * @param list. List of countries 
	 * @return
	 */
	public Map<Country, Integer> distributeArmies() {
		 
		return GUI.distributeArmies(currentPlayer.getPlayerCountries(), currentPlayer.getArmies());
	}


	/**
	 * Ask GUI to ask if user is ready for next phase
	 */
	public boolean readyForNextPhase() {
		return GUI.readyForNextPhase();
		
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
