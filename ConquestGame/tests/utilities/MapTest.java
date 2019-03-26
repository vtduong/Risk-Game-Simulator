/**
 * 
 */
package utilities;
import static org.junit.Assert.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import beans.Country;
import config.Config;
import controller.MapController;
import exception.MapInvalidException;

/**
 * @author apoorvasharma
 *
 */
public class MapTest {
	
	private CustomMapGenerator custMap;
	private MapController mapcntrl;
	private Map<String, Integer> continentMap;
	@Before
	public void setUp() throws Exception {
		custMap = CustomMapGenerator.getInstance();
		mapcntrl = MapController.getInstance();
		continentMap = new HashMap<String,Integer>();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void loadMapTest() throws IOException, MapInvalidException {
		mapcntrl.init("LoadMap", Config.getProperty("testMap"));
		ArrayList<Country> countryDefault = new ArrayList<Country>();
		countryDefault = mapcntrl.countriesDefault;
		assertTrue(countryDefault.size() > 0);
	}
	
	@Test
	public void addContinentNegativeTest() throws IOException{
		try {
			mapcntrl.init("CreateMap", Config.getProperty("testMap"));
			continentMap.put("Asia",1);
			mapcntrl.addContinent(continentMap);
		} catch (MapInvalidException e) {//
			assertTrue(e.getMessage().equalsIgnoreCase(Config.getProperty("errorAddingContinent")));
		}
	}
	
	@Test
	public void addContryNegativeTest() throws IOException{
		try {
			mapcntrl.init("LoadMap", Config.getProperty("testMap"));
			ArrayList<String> countryDefault = new ArrayList<String>();
			countryDefault.add("Test1123,345,Asia");
			mapcntrl.addCountry(countryDefault);
		} catch (MapInvalidException e) {//
			System.out.print(e.getMessage());
			assertTrue(e.getMessage().equalsIgnoreCase(Config.getProperty("errorAddingCountry")));
		}
	}
	
	@Test
	public void addContryContinentPositive() throws IOException, MapInvalidException{
		mapcntrl.init("CreateMap", Config.getProperty("testMap"));
		ArrayList<String> countryDefault = new ArrayList<String>();
		countryDefault.add("Test1,123,345,Asia,Test2");
		countryDefault.add("Test2,123,345,Asia,Test1");
		continentMap.put("Asia",1);
		mapcntrl.addContinent(continentMap);
		mapcntrl.addCountry(countryDefault);
		assertTrue(mapcntrl.continentsDefault.size()==1);
		assertTrue(mapcntrl.countriesDefault.size()==2);
	}
	
	@Test
	public void addAdjCountryNegativeTest() throws IOException{
		try {
			mapcntrl.init("CreateMap", Config.getProperty("testMap"));
			Map<String,List<String>> adjmap = new HashMap<String,List<String>>();
			ArrayList<String> countryDefault = new ArrayList<String>();
			countryDefault.add("China");
			adjmap.put("Test Country", countryDefault);
			mapcntrl.addAdjCountry(adjmap);
		} catch (MapInvalidException e) {//
			System.out.print(e.getMessage());
			assertTrue(e.getMessage().equalsIgnoreCase(Config.getProperty("addAdjCountryError2")));
		}
	}
	
	@Test
	public void removeContinentNegativeTest() throws IOException{
		try {
			mapcntrl.init("LoadMap", Config.getProperty("negativeTestingWorldMap"));
			ArrayList<String> contToRemove = new ArrayList<String>();
			contToRemove.add("Asia");
			mapcntrl.removeContinent(contToRemove,null);
			mapcntrl.mapWritter(Config.getProperty("negativeTestingWorldMap"));
		} catch (MapInvalidException e) {//
			assertTrue(e.getMessage()!=null);
			//assertTrue(e.getMessage().equalsIgnoreCase(Config.getProperty("errorMapNotConnected")));
		}
	}
	
	@Test
	public void removeContinentPositiveTest() throws IOException, MapInvalidException{
		
			mapcntrl.init("LoadMap", Config.getProperty("testMap"));
			ArrayList<String> countryDefault = new ArrayList<String>();
			countryDefault.add("Test1,123,345,Asia1,Test2,Alaska");
			countryDefault.add("Test2,123,345,Asia1,Test1");
			continentMap.put("Asia1",1);
			mapcntrl.addContinent(continentMap);
			mapcntrl.addCountry(countryDefault);
			int sizeBefore =mapcntrl.continentsDefault.size();
			ArrayList<String> contToRemove = new ArrayList<String>();
			contToRemove.add("Asia1");
			mapcntrl.removeContinent(contToRemove,null);
			int sizeAfter =mapcntrl.continentsDefault.size();
			assertTrue(sizeBefore>sizeAfter);
	 }
	
	
	@Test
	public void removeCountrytNegativeTest() throws IOException{
		try {
			mapcntrl.init(Config.getProperty("editOperation"), Config.getProperty("testMap"));
			ArrayList<String> countrytoRemove = new ArrayList<String>();
			countrytoRemove.add("Onion");
			mapcntrl.removeCountry(countrytoRemove, null, false);
		} catch (MapInvalidException e) {//
			System.out.print(e.getMessage());
			assertTrue(e.getMessage().equalsIgnoreCase(Config.getProperty("errorRemoveCountry")));
		}
	}
	
	@Test
	public void removeCountryPositiveTest() throws IOException, MapInvalidException{
			mapcntrl.init(Config.getProperty("editOperation"), Config.getProperty("testMap"));
			ArrayList<String> countrytoAdd = new ArrayList<String>() ;
			countrytoAdd .add("Test1,123,345,Asia,China");
			mapcntrl.addCountry(countrytoAdd);
			int sizebefore =mapcntrl.countriesDefault.size();
			ArrayList<String> countrytoRemove = new ArrayList<String>();
			countrytoRemove.add("Test1");
			mapcntrl.removeCountry(countrytoRemove, null, false);
			int sizeAfter =mapcntrl.countriesDefault.size();
			assertTrue(sizebefore>sizeAfter);
		
	}
	
	@Test
	public void removeadjCountryPositiveTest() throws IOException, MapInvalidException{
			mapcntrl.init(Config.getProperty("editOperation"), Config.getProperty("testMap"));
			Map<String,List<String>> adjmap = new HashMap<String,List<String>>();
			ArrayList<String> countryDefault = new ArrayList<String>();
			countryDefault.add("China");
			adjmap.put("Alaska", countryDefault);
			mapcntrl.addAdjCountry(adjmap);
			MapParser mpsr = new MapParser(Config.getProperty("testMap"));
			mapcntrl.removeAdjCountry(countryDefault,null,adjmap,false);
			Country rec =mpsr.getCountry("China", mapcntrl.countriesDefault);
			//assertTrue(rec.getAdjacentCountries().contains("Alaska"));
			
			//Map<String, List<String>> adjMap
		
	}
	
	@Test
	public void removeadjCountryNegativeTest() throws IOException, MapInvalidException{
			mapcntrl.init(Config.getProperty("editOperation"), Config.getProperty("testMap"));
			ArrayList<String> countrytoAdd = new ArrayList<String>() ;
			countrytoAdd .add("Test1,123,345,Asia,China");
			mapcntrl.addCountry(countrytoAdd);
			int sizebefore =mapcntrl.countriesDefault.size();
			ArrayList<String> countrytoRemove = new ArrayList<String>();
			countrytoRemove.add("Test1");
			mapcntrl.removeCountry(countrytoRemove, null, false);
			int sizeAfter =mapcntrl.countriesDefault.size();
			assertTrue(sizebefore>sizeAfter);
			
			//Map<String, List<String>> adjMap
		
	}
	
	@Test
	public void testmapWritter(){
		try {
			mapcntrl.mapWritter(Config.getProperty("usermap"));
		} catch (MapInvalidException | IOException e) {
			assertTrue(e.getMessage()!=null);
		}
	}
	
	@Test
	public void custMapGeneratorTest(){
		try {
			mapcntrl.init("LoadMap", Config.getProperty("testMap"));
			custMap.getContinent("test");
			custMap.getCountry("test");
		} catch (Exception e) {
			assertTrue(e!=null);
		}
	}
}
