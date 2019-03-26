package utilities;

import static org.junit.Assert.*;
/**
 * This class test for EditMap class
 * @author yadavsurbhi
 * @version 1.0.0
 */

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.MapInvalidException;

// TODO: Auto-generated Javadoc
/**
 * The Class EditMapTest.
 */
public class EditMapTest {

	/** The edit map. */
	EditMap editMap;
	
	/** The adj country map. */
	HashMap adjCountryMap;
	
	/** The remove continents. */
	ArrayList removeContinents;
	
	/** The remove adjacent countries. */
	ArrayList removeAdjacentCountries;
	
	/** The remove countries. */
	ArrayList removeCountries;
	
	/** The continents. */
	HashMap continents;
	
	/** The countries. */
	ArrayList countries;
	
	/** The adj map. */
	HashMap adjMap;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {

		adjCountryMap = new HashMap<String,List<String>>();
		removeContinents = new ArrayList<String>();
		removeAdjacentCountries = new ArrayList<String>();
		removeCountries = new ArrayList<String>();
		continents = new HashMap<String, Integer>();
		countries = new ArrayList<String>();
		adjMap = new HashMap<String,List<String>>();
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Adds the continent test.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MapInvalidException the map invalid exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 */
	@Test
	public void addContinentTest() throws IOException, MapInvalidException, NoSuchMethodException, SecurityException {
		Class reflectEditMap = editMap.getClass();
		Method reflectMethodCall = reflectEditMap.getDeclaredMethod("editExistingMap");
		reflectMethodCall.setAccessible(true);
		String invalid= "Asia";
		String controlvalue= "4";
		String valid= "ABCD";
		Assert.assertNotNull(continents);
		Assert.assertEquals(valid, controlvalue);
		Assert.assertNotEquals(invalid, controlvalue);
		
	}
}
