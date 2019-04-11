package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import beans.Phase;
import beans.Player;
import config.Config;
import controller.GameController;
import exception.MapInvalidException;

// TODO: Auto-generated Javadoc
/**
 * This class is used to save or load the status of the game.
 * @author sandeepchowdaryannabathuni
 *
 */
public class GameStat implements Serializable {
	
	/** The obj. */
	private static GameStat obj = null;
	
	/** The controller. */
	private GameController controller = null;
	
	/** The custom map. */
	private CustomMapGenerator customMap = null;
	
	
	/**
	 * Instantiates a new game stat.
	 */
	private GameStat() {
		controller = GameController.getInstance();
		customMap = CustomMapGenerator.getInstance();
		
		
	}
	
	/**
	 * Gets the single instance of GameStat.
	 *
	 * @return single instance of GameStat
	 */
	public static GameStat getInstance() {
		if(obj == null)
			obj = new GameStat();
		return obj;
	}
	
	/**
	 * This method is used to save game.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
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
	 *
	 * @return the game controller
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException ec
	 * @throws MapInvalidException the map invalid exception
	 */
	public GameInit load() throws IOException, ClassNotFoundException, MapInvalidException {
		String loadStatFromFile = Config.getProperty("loadcontroller");
		
		try(FileInputStream file = new FileInputStream(loadStatFromFile);
		ObjectInputStream objectReader = new ObjectInputStream(file);) {
		
		GameInit controllerObj = (GameInit)objectReader.readObject();
		
		return controllerObj;

		
		
		}
	}
	
//	public static void main(String[] args) throws IOException {
//		GameStat obj = GameStat.getInstance();
//		obj.save();
//	}
}
