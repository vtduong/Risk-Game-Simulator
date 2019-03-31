package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import config.Config;
import controller.GameController;

/**
 * This class is used to save or load the status of the game.
 * @author sandeepchowdaryannabathuni
 *
 */
public class GameStat {
	
	private static GameStat obj = null;
	private GameController controller = null;
	
	private GameStat() {
		controller = GameController.getInstance();
	}
	
	public static GameStat getInstance() {
		if(obj == null)
			obj = new GameStat();
		return obj;
	}
	
	public void save() throws IOException{
		String saveStatToFile = Config.getProperty("saveTofile");
		FileOutputStream file = new FileOutputStream(saveStatToFile);
		ObjectOutputStream objectWriter = new ObjectOutputStream(file);
		
		objectWriter.writeObject(controller);
	}
	
	
	public void load() throws IOException, ClassNotFoundException {
		String loadStatFromFile = Config.getProperty("loadFromfile");
		FileInputStream file = new FileInputStream(loadStatFromFile);
		ObjectInputStream objectReader = new ObjectInputStream(file);
		
		controller = (GameController)objectReader.readObject();
	}
	
	public static void main(String[] args) throws IOException {
		GameStat obj = GameStat.getInstance();
		obj.save();
	}
}
