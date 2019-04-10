package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import beans.Phase;
import beans.Player;
import config.Config;
import controller.GameController;
import exception.MapInvalidException;

/**
 * This class is used to save or load the status of the game.
 * @author sandeepchowdaryannabathuni
 *
 */
public class GameStat implements Serializable {
	
	private static GameStat obj = null;
	private GameController controller = null;
	private CustomMapGenerator customMap = null;
	
	
	private GameStat() {
		controller = GameController.getInstance();
		customMap = CustomMapGenerator.getInstance();
		
		
	}
	
	public static GameStat getInstance() {
		if(obj == null)
			obj = new GameStat();
		return obj;
	}
	
	/**
	 * This method is used to save game.
	 * @throws IOException
	 */
	public void save() throws IOException{
		
		GameInit snapShot = new GameInit();
		
		String saveStatToFile = Config.getProperty("savecontroller");
		
		try(FileOutputStream file = new FileOutputStream(saveStatToFile);
		ObjectOutputStream objectWriter = new ObjectOutputStream(file);) {
		
		objectWriter.writeObject(snapShot);
		
		}
		
	}
	
	
	/**
	 * This method is used to load the game from the previous checkpoint.
	 * @throws IOException
	 * @throws ClassNotFoundException ec
	 * @throws MapInvalidException 
	 */
	public void load() throws IOException, ClassNotFoundException, MapInvalidException {
		String loadStatFromFile = Config.getProperty("loadcontroller");
		
		try(FileInputStream file = new FileInputStream(loadStatFromFile);
		ObjectInputStream objectReader = new ObjectInputStream(file);) {
		
		GameInit gameInit = (GameInit)objectReader.readObject();
		controller.setController(gameInit.gc);
		controller.setCurrentPhase(Phase.getPhase(gameInit.currentPhase.getValue() + 1));
		controller.setCurrentPlayer(gameInit.currentPlayer);
		controller.setWorldDominationView(gameInit.wdView);
		controller.setPhaseView(gameInit.phaseView);
		controller.setCardExchangeView(gameInit.cardView);;
		controller.setCountryList(gameInit.countryList);
		controller.setNumberOfPlayers(gameInit.numberOfPlayers);
		controller.setContinentListByName(gameInit.continentListByName);;
		controller.setCountryOwnership(gameInit.countryOwnership);;
		controller.setReadyForNextPhase(gameInit.readyForNextPhase);
		controller.setPlayerList(gameInit.playerList);
		controller.setWinner(gameInit.winner);
		controller.setUI(gameInit.ui);
		controller.setCustomMapCenerator(gameInit.cmg);
		controller.setContinentList(gameInit.continentList);
		controller.setGameStat(gameInit.gameStat);
		
		customMap.setCustomMap(gameInit.cmg);
		customMap.setContinents(gameInit.continents);
		customMap.setCountries(gameInit.countries);
		customMap.setRemoveContinents(gameInit.removeContinents);
		customMap.setRemoveCountries(gameInit.removeCountries);
		customMap.setRemoveAdjacentCountries(gameInit.removeAdjacentCountries);
		customMap.setAdjMap(gameInit.adjMap);
		customMap.setCountryDefault(gameInit.countryDefault);
		customMap.setContinentmap(gameInit.continentmap);
		customMap.setCountryMap(gameInit.countrymap);
		customMap.setAdjCountryMap(gameInit.adjCountryMap);
		customMap.setEditMap(gameInit.editMap);
		customMap.setMapController(gameInit.mc);
		
		
		
		controller.takeTurns();
		}
		
	}
	
//	public static void main(String[] args) throws IOException {
//		GameStat obj = GameStat.getInstance();
//		obj.save();
//	}
}
