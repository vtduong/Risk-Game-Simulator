package utilities;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.MapInvalidException;

public class MapParserTest {
	
	private MapParser mapParser;
	String inputFile;


	@Before
	public void setUp() throws Exception {
		mapParser= new MapParser(inputFile);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = MapInvalidException.class)
	public void checkForDuplicateTerritory() throws IOException, MapInvalidException {
		mapParser = new MapParser(null);
		mapParser.readFile();
	}

}
