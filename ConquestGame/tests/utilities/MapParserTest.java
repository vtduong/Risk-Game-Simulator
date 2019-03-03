package utilities;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.MapInvalidException;

/**
 * 
 * @author yadavsurbhi
 *
 */

public class MapParserTest {

	private MapParser mapParser;
	String inputFile;
	String validFile = "src/resources/world.map";
	String invalidContinent = "src/resources/Invalid_Continent.map";
	String abandonedTerritory = "src/resources/Abandoned_Territory.map";
	String duplicateTerritory = "src/resources/Duplicate_Territory.map";

	@Before
	public void setUp() throws MapInvalidException {
		mapParser = new MapParser(inputFile);
	}

	@After
	public void tearDown() throws MapInvalidException {
	}

	@Test(expected = MapInvalidException.class)
	public void checkForDuplicateTerritory() {
		mapParser = new MapParser(duplicateTerritory);
		mapParser.readFile();
	}

	@Test(expected = MapInvalidException.class)
	public void checkForAbandonedTerritory() {
		mapParser = new MapParser(abandonedTerritory);
		mapParser.readFile();
	}

	@Test(expected = MapInvalidException.class)
	public void checkForInvalidContinent() {
		mapParser = new MapParser(invalidContinent);
		mapParser.readFile();
	}

}
