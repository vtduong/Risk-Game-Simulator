package utilities;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.MapInvalidException;

/**
 * 
 * @author yadavsurbhi
 *
 */

public class MapValidatorTest {

	private MapValidator mapValidator;
	String inputFile;
	String validFile = "src/resources/world.map";
	String invalidContinent = "src/resources/Invalid_Continent.map";
	String abandonedTerritory = "src/resources/Abandoned_Territory.map";
	String duplicateTerritory = "src/resources/Duplicate_Territory.map";
	String noContinent = "src/resources/No_Continent.map";
	String notConnected = "src/resources/Not_Connected.map";

	@Before
	public void setUp() throws MapInvalidException {
		mapValidator = new MapValidator(inputFile);
	}

	@After
	public void tearDown() throws MapInvalidException {
	}

	@Test(expected = MapInvalidException.class)
	public void checkForDuplicateTerritory() throws IOException, MapInvalidException {
		mapValidator = new MapValidator(duplicateTerritory);
		mapValidator.createCountryGraph();
	}

	@Test(expected = MapInvalidException.class)
	public void checkForNoContinentInMap() throws IOException, MapInvalidException {
		mapValidator = new MapValidator(noContinent);
		mapValidator.createCountryGraph();
	}

	@Test(expected = MapInvalidException.class)
	public void checkForAbandonedTerritory() throws IOException, MapInvalidException {
		mapValidator = new MapValidator(abandonedTerritory);
		mapValidator.createCountryGraph();
	}
	
	@Test(expected = MapInvalidException.class)
	public void checkForMapConnectivity() throws IOException, MapInvalidException {
		mapValidator = new MapValidator(notConnected);
		mapValidator.createCountryGraph();
	}
	

}
