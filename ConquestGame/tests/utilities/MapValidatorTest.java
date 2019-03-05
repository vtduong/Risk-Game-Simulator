package utilities;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.MapInvalidException;

/**
 * This class test for MapValidator class
 * 
 * @author yadavsurbhi
 * @version 1.0.0
 */

public class MapValidatorTest {

	private MapValidator mapValidator;
	String inputFile;
	String validFile = "src/resources/world.map";
	String invalidContinent = "src/tests/resources/Invalid_Continent.map";
	String abandonedCountry = "src/tests/resources/Abandoned_Territory.map";
	String duplicateCountry = "src/tests/resources/Duplicate_Territory.map";
	String noContinent = "src/tests/resources/No_Continent.map";
	String notConnected = "src/tests/resources/Not_Connected.map";

	/**
	 * This method allocates resources before calling test methods
	 * 
	 * @throws MapInvalidException invalid map exception
	 */
	@Before
	public void setUp() throws MapInvalidException {
		mapValidator = new MapValidator(inputFile);
	}

	/**
	 * This method deallocates resources before calling test methods
	 * 
	 * @throws MapInvalidException invalid map exception
	 */
	@After
	public void tearDown() throws MapInvalidException {
	}

	/**
	 * This method test for duplicate countries in map file
	 * 
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	@Test(expected = MapInvalidException.class)
	public void checkForDuplicateCountry() throws IOException, MapInvalidException {
		mapValidator = new MapValidator(duplicateCountry);
		mapValidator.createCountryGraph();
	}

	/**
	 * This method test if input map have no continent in it
	 * 
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	@Test(expected = MapInvalidException.class)
	public void checkForNoContinentInMap() throws IOException, MapInvalidException {
		mapValidator = new MapValidator(noContinent);
		mapValidator.createCountryGraph();
	}

	/**
	 * This method test for abandoned country in map file
	 * 
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	@Test(expected = MapInvalidException.class)
	public void checkForAbandonedCountry() throws IOException, MapInvalidException {
		mapValidator = new MapValidator(abandonedCountry);
		mapValidator.createCountryGraph();
	}

	/**
	 * This method test if the input map is connected or not
	 * 
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	@Test(expected = MapInvalidException.class)
	public void checkForMapConnectivity() throws IOException, MapInvalidException {
		mapValidator = new MapValidator(notConnected);
		mapValidator.createCountryGraph();
	}

}
