package utilities;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.MapController;
import exception.MapInvalidException;


public class MapUpdateTest {
	MapController mapController;
	EditMap editMap;
	Map<String,Integer> continents;
	String inputfile= "src/tests/resources/testworld.map";

	@Before
	public void setUp() throws Exception {
		mapController= new MapController();
		editMap= EditMap.getInstance();
		continents= new HashMap<String,Integer>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addContinentTest() throws IOException, MapInvalidException {
		continents.put("Wakanda", 7);
		continents.put("Mars", 2);
		mapController.addContinent(continents, null, true, inputfile );
		assertNotNull(mapController.continentsDefault);
		assertEquals(mapController.continentsDefault.size(), 8);
	}

}
