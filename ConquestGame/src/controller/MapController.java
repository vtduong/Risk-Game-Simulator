package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import beans.Continent;
import beans.Country;
import exception.MapInvalidException;
import utilities.MapFileWriter;

/**
 * @author apoorvasharma
 *
 */
public class MapController {

	public static ArrayList<Country> countriesDefault = new ArrayList<Country>();
	public static ArrayList<Continent> continentsDefault = new ArrayList<Continent>();
	private static Map<String, ArrayList<Country>> worldMap = new HashMap<String, ArrayList<Country>>();
	private static String filepath = "src/resources/usermap.map";

	/**
	 * @param inputFile
	 * @throws IOException
	 * @throws MapInvalidException 
	 */
	public void validateMap(String inputFile) throws IOException, MapInvalidException {
		new utilities.MapValidator(inputFile).createCountryGraph();
	}

	private void setMapDetails(BufferedWriter bw) throws IOException {
		bw.write("[Map]");
	}

	/**
	 * @param continentMap
	 * @param bw
	 * @param isEdit
	 * @param inputFile
	 * @throws IOException
	 */
	public void addContinent(Map<String, Integer> continentMap, BufferedWriter bw, boolean isEdit,
			String inputFile) throws IOException {

		if (!isEdit) {
			bw.write("[Continents]");
			bw.newLine();
			for (String key : continentMap.keySet()) {
				bw.write(key + "=" + continentMap.get(key));
				bw.newLine();
			}
		} else {
			FileReader editFile = new FileReader(inputFile);
			Scanner sc = new Scanner(new File(inputFile));
			String tempStr = null;
			String buildMapFile = "";
			while (sc.hasNext()) {
				tempStr = sc.nextLine();
				buildMapFile = buildMapFile + "" + tempStr + "\n";
			}
			String temp = null, updatedStr = null;
			for (String key : continentMap.keySet()) {
				temp = (key + "=" + continentMap.get(key) + "\n");
			}
			updatedStr = buildMapFile.substring(0, buildMapFile.indexOf("[Territories]")) + temp
					+ buildMapFile.substring(buildMapFile.indexOf("[Territories]"));
			FileWriter writer = new FileWriter(inputFile);

			writer.write(updatedStr);
			writer.close();
		}

	}

	/**
	 * @param arrayList
	 * @param bw
	 * @param isEdit
	 * @param inputFile
	 * @throws IOException
	 */
	public void addCountry(Object[] arrayList, BufferedWriter bw, boolean isEdit, String inputFile)
			throws IOException {
		if (!isEdit) {
			bw.write("[Territories]" + "\n");
			for (int k = 0; k < arrayList.length; k++) {
				bw.write((String)arrayList[k] + "\n");
			}
		} else {
			FileReader editFile = new FileReader(inputFile);
			Scanner sc = new Scanner(new File(inputFile));
			String tempStr = null;
			String buildMapFile = "";
			while (sc.hasNext()) {
				tempStr = sc.nextLine();
				buildMapFile = buildMapFile + "" + tempStr + "\n";
			}
			String temp = null, updatedStr = null;
			for (int k = 0; k < arrayList.length; k++) {
				temp = (String)arrayList[k] + "\n";
			}
			updatedStr = buildMapFile.substring(0, buildMapFile.indexOf("[Territories]"))
					+ buildMapFile.substring(buildMapFile.indexOf("[Territories]")) + temp;
			FileWriter writer = new FileWriter(inputFile);
			writer.write(updatedStr);
			writer.close();
		}

	}
	
	
	/**
	 * @param adjCountryMap
	 * @param inputFile
	 * @throws IOException
	 * @throws MapInvalidException 
	 */

	private void addAdjCountry(Map<String,List<String>> adjCountryMap,String inputFile) throws IOException, MapInvalidException {
  
		utilities.MapParser mpsr = new utilities.MapParser(inputFile);
		if (countriesDefault.size() == 0) {
			mpsr.readFile();
			countriesDefault = mpsr.countriesList;
			
			
		}
		Set<String> countryname =new HashSet<String>();
		if(!countriesDefault.isEmpty()) {
			for(Country rec:countriesDefault) {
				countryname.add(rec.getName());
			}
		}
		
		for(String str:adjCountryMap.keySet()) {
			if(countryname.contains(str)) {
				List<String> adjStr = adjCountryMap.get(countryname);
				for(String arr:adjStr) {
					if(countryname.contains(arr)) {
						Country rec =mpsr.getCountry(str,countriesDefault);
						if(!rec.getAdjacentCountries().contains(arr)) {
							rec.getAdjacentCountries().add(arr);
						}
					}else {
						throw new MapInvalidException("Adjacent country does not exist");
						/// throw error "Adjacent country does not Exist.
					}
				}
			}else {
				throw new MapInvalidException(str+ "Country does not exist. Add the country again");
				// throw error "str Country does not exist.Add the country again. "
				
			}
			
		}
		
		if (continentsDefault.size() == 0) {
			mpsr.readFile();
			continentsDefault = mpsr.continentsList;
		}
		
		MapFileWriter mfw = new MapFileWriter();
		mfw.writeFile(continentsDefault, countriesDefault, inputFile);

	}

	/**
	 * @param continentName
	 * @param inputFile
	 * @throws IOException
	 * @throws MapInvalidException 
	 */

	private void removeContinent(ArrayList<String> continentName, String inputFile) throws IOException, MapInvalidException {

		utilities.MapParser mpsr = new utilities.MapParser(inputFile);
		mpsr.readFile();
		continentsDefault = mpsr.continentsList;
		worldMap = mpsr.worldMap;
		Iterator<Continent> iter = continentsDefault.iterator();
		while (iter.hasNext()) {
			Continent conrec = iter.next();
			if (continentName.contains(conrec.getName())) {
				iter.remove();
			}
		}
		ArrayList<Country> countriesToRemove = new ArrayList<Country>();
		for (String key : worldMap.keySet()) {
			if (continentName.contains(key)) {
				countriesToRemove.addAll(worldMap.get(key));
			}
		}

		ArrayList<String> countriesToDelete = new ArrayList<String>();
		for (Country cRec : countriesToRemove) {
			countriesToDelete.add(cRec.getName());
		}

		if (countriesToDelete.size() != 0) {
			removeCountry(countriesToDelete, inputFile,false);
		}
		
		
	}

	/**
	 * @param countries
	 * @param inputFile
	 * @throws IOException
	 * @throws MapInvalidException 
	 */

	public void removeCountry(ArrayList<String> countries, String inputFile,boolean isRecursion) throws IOException, MapInvalidException {
    
		utilities.MapParser mpsr = new utilities.MapParser(inputFile);
		mpsr.readFile();
		countriesDefault = mpsr.countriesList;
	    if(worldMap.size()==0) {
	    	worldMap = mpsr.worldMap;
	    }
		Iterator<Country> iter = countriesDefault.iterator();
		while (iter.hasNext()) {
			Country conrec = iter.next();
			if (countries.contains(conrec.getName())) {
				worldMap.get(conrec.getContinent()).remove(conrec.getName());
				iter.remove();
			}
		}
		removeAdjCountry(countries, inputFile,null,true);

	}

	/**
	 * @param countries
	 * @param inputFile
	 * @throws IOException
	 * @throws MapInvalidException 
	 */

	public void removeAdjCountry(ArrayList<String> countries, String inputFile,Map<String,List<String>> adjMap,boolean isFunCall) throws IOException, MapInvalidException {

		if (countriesDefault.size() == 0) {
			utilities.MapParser mpsr = new utilities.MapParser(inputFile);
			mpsr.readFile();
			countriesDefault = mpsr.countriesList;
			worldMap = mpsr.worldMap;
		}
		
		if (continentsDefault.size() == 0) {
			utilities.MapParser mpsr = new utilities.MapParser(inputFile);
			mpsr.readFile();
			continentsDefault = mpsr.continentsList;
		}
		Iterator<Country> iter = countriesDefault.iterator();
		if(isFunCall) {
			while (iter.hasNext()) {
				Country conrec = iter.next();
				for (String str : countries) {
					if (conrec.getAdjacentCountries().contains(str)) {
						conrec.getAdjacentCountries().remove(str);
	
					}
				}
			}
		}else {
			while (iter.hasNext()) {
				Country conrec = iter.next();
				if(adjMap.containsKey(conrec.getName())) {
					for (String str : adjMap.get(conrec.getName())) {
						System.out.println("Ie"+conrec.getName()+"  "+adjMap.get(conrec.getName()));
						conrec.getAdjacentCountries().remove(str);
					}
				}
			}
		}
		
		//check for countries without any adjacent countries and remove them if they exist
		Iterator<Country> iter1 = countriesDefault.iterator();
		while (iter1.hasNext()) {
			Country conrec = iter1.next();
			if (conrec.getAdjacentCountries().size()==0) {
				System.out.print(conrec.getName());
				worldMap.get(conrec.getContinent()).remove(conrec.getName());
				iter1.remove();
			}
		}
	    // remove continents if no country is associated with the continent
		if(!worldMap.isEmpty()) {
			Iterator<Continent> iterCon = continentsDefault.iterator();
			while (iterCon.hasNext()) {
				Continent conrec = iterCon.next();
				if(worldMap.get(conrec.getName())==null) {
					iterCon.remove();
				}
			}
		}
		MapFileWriter mfw = new MapFileWriter();
		mfw.writeFile(continentsDefault, countriesDefault, inputFile);
	}
}