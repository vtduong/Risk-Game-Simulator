package utilities;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.MapInvalidException;

// TODO: Auto-generated Javadoc
/**
 * This class test for MapValidator class.
 *
 * @author yadavsurbhi
 * @version 1.0.0
 */

public class MapValidatorTest {

	/** The map validator. */
	private MapValidator mapValidator;
	
	/** The input file. */
	String inputFile;
	
	/** The valid file. */
	String validFile = "src/resources/world.map";
	
	/** The invalid continent. */
	String invalidContinent = "src/tests/resources/Invalid_Continent.map";
	
	/** The abandoned country. */
	String abandonedCountry = "src/tests/resources/Abandoned_Territory.map";
	
	/** The duplicate country. */
	String duplicateCountry = "src/tests/resources/Duplicate_Territory.map";
	
	/** The no continent. */
	String noContinent = "src/tests/resources/No_Continent.map";
	
	/** The not connected. */
	String notConnected = "src/tests/resources/Not_Connected.map";

	/**
	 * This method allocates resources before calling test methods.
	 *
	 * @throws MapInvalidException invalid map exception
	 */
	@Before
	public void setUp() throws MapInvalidException {
		mapValidator = new MapValidator(inputFile);
	}

	/**
	 * This method deallocates resources before calling test methods.
	 *
	 * @throws MapInvalidException invalid map exception
	 */
	@After
	public void tearDown() throws MapInvalidException {
	}

	/**
	 * This method test for duplicate countries in map file.
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
	 * This method test if input map have no continent in it.
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
	 * This method test for abandoned country in map file.
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
	 * This method test if the input map is connected or not.
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
