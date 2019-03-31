package utilities;

import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * This class is used to clone the original map so that the original map
 *  is not affected by the changes made by user.
 *
 * @author ankit
 */
public class ReplicateMap implements Serializable {
	
	/** The inputfile. */
	private final String INPUTFILE = "src/resources/World.map";
	
	/** The outputfile. */
	private final String OUTPUTFILE = "src/resources/usermap.map";
	
	/** The replicate map. */
	private static ReplicateMap replicateMap = null;
	
	/**
	 * Gets the single instance of ReplicateMap.
	 *
	 * @return single instance of ReplicateMap
	 */
	public static ReplicateMap getInstance() {
		if ( replicateMap == null) {
			replicateMap = new ReplicateMap();
		}
		return replicateMap;
	}
	
	/**
	 * Clones the existing map so the original map is not changed by the user.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void cloneMap() throws IOException {
		
		FileReader reader = new FileReader(INPUTFILE);
		FileWriter writer = new FileWriter(OUTPUTFILE);
		Scanner wrapReader = new Scanner(reader);
		BufferedWriter wrapWriter = new BufferedWriter(writer);
		while(wrapReader.hasNextLine()) {
			wrapWriter.write(wrapReader.nextLine());
			wrapWriter.newLine();
			wrapWriter.flush();	
		}
		reader.close();
		writer.close();
		wrapReader.close();
		wrapWriter.close();
	}
}