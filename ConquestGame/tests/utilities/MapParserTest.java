package utilities;

import static org.junit.Assert.*;
/**
 * This class test for MapParser class
 * @author author yadavsurbhi
 * @version 1.0.0
 */

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.MapInvalidException;

// TODO: Auto-generated Javadoc
/**
 * The Class MapParserTest.
 */
public class MapParserTest {

	/** The map parser. */
	private MapParser mapParser;
	
	/** The input file. */
	String inputFile;

	/**
	 * This method allocates resources before calling test methods.
	 *
	 * @throws MapInvalidException invalid map exception
	 */
	@Before
	public void setUp() throws MapInvalidException {
		mapParser = new MapParser(inputFile);
	}

	/**
	 * This method deallocates resources after calling test methods.
	 *
	 * @throws MapInvalidException invalid map exception
	 */
	@After
	public void tearDown() throws MapInvalidException {
	}

	/**
	 * Check for duplicate territory.
	 *
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	@Test(expected = MapInvalidException.class)
	public void checkForDuplicateTerritory() throws IOException, MapInvalidException {
		mapParser = new MapParser(null);
		mapParser.readFile();
	}

}
