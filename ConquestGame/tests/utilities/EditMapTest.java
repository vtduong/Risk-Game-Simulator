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

public class EditMapTest {

	EditMap editMap;
	HashMap adjCountryMap;
	ArrayList removeContinents;
	ArrayList removeAdjacentCountries;
	ArrayList removeCountries;
	HashMap continents;
	ArrayList countries;
	HashMap adjMap;
	
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

	@After
	public void tearDown() throws Exception {
	}

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
