package utilities;

import java.io.*;
import java.util.*;

public class ReplicateMap {
	private final String INPUTFILE = "src/resources/World.map";
	private final String OUTPUTFILE = "src/resources/usermap.map";
	private static ReplicateMap replicateMap = null;
	
	public static ReplicateMap getInstance() {
		if ( replicateMap == null) {
			replicateMap = new ReplicateMap();
		}
		return replicateMap;
	}
	
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
	public static void main(String[] args) throws IOException {
		ReplicateMap rp= ReplicateMap.getInstance();
		rp.cloneMap();
	}
	}