package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import beans.*;
import exception.MapInvalidException;
import gui.UI;
import phases.Attack;
import phases.ReEnforcement;
import phases.TurnPhase;
import utilities.CustomMapGenerator;
import utilities.DiceRoller;
import utilities.EditMap;
import utilities.MapValidator;
import utilities.Tuple;

// TODO: Auto-generated Javadoc
/**
 * The Class GameController.
 */
/*
 * @description :
 * @author
 */
public class GameController {
	
	private final static String INPUTFILE = "src/resources/World.map";
	/** The controller. */
	private static GameController controller= null;

/** The number of players. */
//	HashMap<Player,WorldMap> countryOwnership = new HashMap<Player,WorldMap>();
	private int numberOfPlayers;



	/** The country ownership. */
	Map<Player, ArrayList<Country>> countryOwnership = null;
	
	/** The current phase. */
	TurnPhase currentPhase = null;
	
	/** The ready for next phase. */
	private boolean readyForNextPhase = false;
	
	/** The current player. */
	private Player currentPlayer;
	
	/** The player list. */
	private ArrayList<Player> playerList;
	
	/** The Constant MIN_ARGS. */
	private final static int MIN_ARGS = 1;
	
	/** The winner. */
	private Player winner = null;
	
	/**
	 * Instantiates a new game controller.
	 */
	private GameController(){
		countryOwnership = new HashMap();
		playerList = new ArrayList<Player>();
	}

	/**
	 * Gets the single instance of GameController.
	 *
	 * @return single instance of GameController
	 */
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
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws MapInvalidException 
	 */
	/*
	 * @description :
	 * @author
	 */
	public static void main(String[] args) throws IOException, MapInvalidException {
		/*File inFile = null;
		if (0 < args.length) {
		   inFile = new File(args[0]);
		} else {
		   System.err.println("Invalid arguments count:" + args.length);
		   System.exit(1);
		} */
		
		GameController controller = GameController.getInstance();
		UI ui = new UI();
		
		
		/*Added to parse the default file.
		 * To provide input file use src/resources/World.map as argument
		 * 
		 */
		MapController mapController = new MapController();
//		if (args == null || args.length < MIN_ARGS) {
//            controller.showHelp();
//            return;
//        }
		MapValidator mapValidator = new MapValidator(INPUTFILE);
		mapValidator.createCountryGraph();
		
		// Here we are asking user to select the map existing map
		// or to create a custom map.
		
		System.out.println("----------Welcome----------");
		System.out.println("Please select the following options.\n1)Load exisiting map\n2)Create map\n3)Edit existing map");
		Scanner mapOption = new Scanner(System.in);
		int selectedMapOption = mapOption.nextInt();
		if(selectedMapOption == 1) {
			mapController.validateMap("/src/resources/World.map");
		}
		else if(selectedMapOption == 2) {
			CustomMapGenerator customMap = CustomMapGenerator.getInstance();
			customMap.createCustomMap();
		}
		else if(selectedMapOption == 3) {
			EditMap editMap = EditMap.getInstance();
			editMap.editExistingMap();
		}
		else {
			System.exit(0);
		}
		//Getting Player Info
		System.out.println("Please enter the number of players between 2 and 6: ");
		Scanner inputNumPlayers = new Scanner(System.in);	
		int numberOfPlayers = inputNumPlayers.nextInt();
		//if user inputs number of players less than 2 or greater than 6 then exit the game
		if (!(numberOfPlayers >= 2 && numberOfPlayers <= 6)) {
			System.exit(0);
		}
		
		int initialArmies = 0;
		switch(numberOfPlayers) {
		//TODO	not so sure about case 2
			case 2:
				initialArmies = 40;
			case 3:
				initialArmies = 35;
			break;
			case 4:
				initialArmies = 30;
			break;
			case 5:
				initialArmies = 25;
			break;
			case 6:
				initialArmies = 20;
			break;	
		}
		for(int i = 1; i <=numberOfPlayers ; i++) {
			String playerName = "Player " + i;
			Player player = new Player(playerName, true, initialArmies);
			controller.addPlayer(player);
			//register UI as observer for this player
			player.attach(ui);
		}
		
		//TODO MOVE CODE TO UI CLASS UNDER APPROPRIATE METHODS
		System.out.println("evenly distributing countries among players in random fashion...");
		controller.randomizeCountryDistribution(MapValidator.countriesList, controller.getPlayerList());
		
		controller.takeTurns();	
				
		
		
	}
	
	/**
	 * @return the list of players
	 */
	public List<Player> getPlayerList() {
		return this.playerList;
	} 	
	
	/**
	 * Show help.
	 */
	public void showHelp() {
		//TODO
	}
	
	/**
	 * Adds the player.
	 *
	 * @param player the player
	 */
	public void addPlayer(Player player) {
		playerList.add(player);
		numberOfPlayers++;
	}
	
	/**
	 * Gets the num players.
	 *
	 * @return the num players
	 */
	public int getNumPlayers() {
		return numberOfPlayers;
	}
	
	/**
	 * Gets the player.
	 *
	 * @param idx the idx
	 * @return the player
	 */
	public Player getPlayer(int idx) {
		return playerList.get(idx);
	}
	
	

	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * Load map.
	 *
	 * @return true, if successful
	 */
	/*
	 * @description :
	 * @author
	 */
	public static boolean loadMap(){
		//TODO this method should belong to GUI
		return false;
		
	}
	
	/**
	 * Sets the phase.
	 *
	 * @param turnPhase the new phase
	 */
	/*
	 * @description :
	 * @author
	 */
	public void setPhase(TurnPhase turnPhase) {
		currentPhase = turnPhase;
	}
	
	/**
	 * Take turns.
	 */
	public void takeTurns() {
		int i = 0;
		while (winner == null) {
			i = i % playerList.size();
			currentPlayer = playerList.get(i);
			System.out.println("-----------PLAYER "+ i+1 + "'S TURN---------");
			takePhases();
			// check if current player has won the game
			if(countryOwnership.size() == MapValidator.countriesList.size()) {
				winner = currentPlayer;
			}
			i++;
		}
		
	}
    
	/**
	 * Take phases.
	 */
	public void takePhases() {
		currentPhase = new ReEnforcement();
		while(currentPhase != null) {
			while(!readyForNextPhase) {
				try {
					//ask user if wants to init an attack
					if(currentPhase instanceof Attack) {
						if(!isWar()) {
							break;
						}
					}
					currentPhase.takePhase();
					//ask user if ready for next phase
					readyForNextPhase = readyForNextPhase();
				}catch(IllegalArgumentException e) {
					UI.handleExceptions(e.getMessage());
				}
			}
			currentPhase.setNextPhase();
		}
	}

	/**
	 * Request GUI to ask if user wants to go to war.
	 *
	 * @return true if user wants to go to war
	 */
	private boolean isWar() {
		return UI.isWar();
		
	}


	/**
	 * Gets the current player.
	 *
	 * @author Van
	 * @return current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Sets the current player.
	 *
	 * @param player the new current player
	 */
	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}
	
	/**
	 * Inits the game.
	 */
	public void initGame() {
		//TODO	get number of players in from user
		//TODO	assign each player an initial number of armies (based on risk rule)
		//each player take turn to play
	}
	
	
	/**
	 * evenly distributes countries among players in a random fashion .
	 *
	 * @author vanduong
	 * @param countries the countries
	 * @param players   the players
	 */
	public void randomizeCountryDistribution(List<Country> countries, List<Player> players) {
	    Random rand = new Random();
	    int playerIdx = 0;
	    
	    //players take turn to add a country to their occupied_list until the unoccupied country list is empty
	    while(countries.size() > 0) {
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
	 * Asks GUI to ask user to input number of armies to be distributed to each
	 * occupied countries.
	 *
	 * @return the map
	 */
	public Map<Country, Integer> distributeArmies() {
		 
		return UI.distributeArmies(currentPlayer.getPlayerCountries(), currentPlayer.getArmies());
	}


	/**
	 * Ask GUI to ask if user is ready for next phase.
	 *
	 * @return true, if successful
	 */
	public boolean readyForNextPhase() {
		return UI.readyForNextPhase();
		
	}


	/**
	 * Gets the params for fortification.
	 *
	 * @return a set of 3 objects: country to move armies from, country to move
	 *         armies to, and number of armies
	 */
	public Tuple getParamsForFortification() {
		return UI.getFortificationInfo();
		
	}
}
