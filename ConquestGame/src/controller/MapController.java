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
 * This class handles addition, insertion, removal of new continents, countries
 * or adjacent countries
 * 
 * @author apoorvasharma
 * @version 1.0.0
 */
public class MapController {

	public static ArrayList<Country> countriesDefault = new ArrayList<Country>();
	public static ArrayList<Continent> continentsDefault = new ArrayList<Continent>();
	private static Map<String, ArrayList<Country>> worldMap = new HashMap<String, ArrayList<Country>>();
	private static String filepath = "src/resources/usermap.map";

	/**
	 * @param inputFile input map file
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	public void validateMap(String inputFile) throws IOException, MapInvalidException {
		utilities.MapValidator mpsr = new utilities.MapValidator(inputFile);
		mpsr.createCountryGraph();
		countriesDefault =mpsr.countriesList;
		continentsDefault=mpsr.continentsList;
		
	}

	/**
	 * @param bw instance of Buffered Writer
	 * @throws IOException file handling exception
	 */
	private void setMapDetails(BufferedWriter bw) throws IOException {
		bw.write("[Map]");
	}

	/**
	 * @param continentMap continent user wish to add in the input map file
	 * @param bw           instance of Buffered Writer
	 * @param isEdit       to decide if input file needs to be edited or newly
	 *                     created
	 * @param inputFile    input map file
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	public void addContinent(Map<String, Integer> continentMap, BufferedWriter bw, boolean isEdit, String inputFile)
			throws IOException, MapInvalidException {
		try {
			if (!isEdit) {
				bw.write("[Continents]");
				bw.newLine();
				for (String key : continentMap.keySet()) {
					bw.write(key + "=" + continentMap.get(key));
					bw.newLine();
				}
			} else {
				utilities.MapParser mpsr = new utilities.MapParser(inputFile);
				mpsr.readFile();
				countriesDefault = mpsr.countriesList;
				continentsDefault = mpsr.continentsList;
				for (String rec : continentMap.keySet()) {
					if (!continentsDefault.contains(rec)) {
						Continent crec = new Continent();
						crec.setName(rec);
						crec.setMaxArmies(continentMap.get(rec));
						continentsDefault.add(crec);
					}

				}
				MapFileWriter mfw = new MapFileWriter();
				mfw.writeFile(continentsDefault, countriesDefault, inputFile);
			}
		} catch (Exception e) {
			throw new MapInvalidException("Error while adding new continent. Provide a valid input.");
		}
	}

	/**
	 * @param countryArrayList list of
	 * @param bw               instance of Buffered Writer
	 * @param isEdit           to decide if input file needs to be edited or newly
	 *                         created
	 * @param inputFile        input map file
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	public void addCountry(Object[] countryArrayList, BufferedWriter bw, boolean isEdit, String inputFile)
			throws IOException, MapInvalidException {
		try {
			if (!isEdit) {
				bw.write("[Territories]" + "\n");
				for (int k = 0; k < countryArrayList.length; k++) {
					bw.write((String) countryArrayList[k] + "\n");
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
				for (int k = 0; k < countryArrayList.length; k++) {
					temp = (String) countryArrayList[k] + "\n";
				}
				updatedStr = buildMapFile.substring(0, buildMapFile.indexOf("[Territories]"))
						+ buildMapFile.substring(buildMapFile.indexOf("[Territories]")) + temp;
				FileWriter writer = new FileWriter(inputFile);
				writer.write(updatedStr);
				writer.close();
			}
		} catch (Exception e) {
			throw new MapInvalidException("Error while adding new country. Provide a valid input.");
		}

	}

	/**
	 * @param adjCountryMap list of adjacent countries of a particular country
	 * @param inputFile     input map file
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */

	public void addAdjCountry(Map<String, List<String>> adjCountryMap, String inputFile)
			throws IOException, MapInvalidException {

		try {
			utilities.MapParser mpsr = new utilities.MapParser(inputFile);
			if (countriesDefault.size() == 0) {
				mpsr.readFile();
				countriesDefault = mpsr.countriesList;

			}
			Set<String> countryname = new HashSet<String>();
			if (!countriesDefault.isEmpty()) {
				for (Country rec : countriesDefault) {
					countryname.add(rec.getName());
				}
			}

			for (String str : adjCountryMap.keySet()) {
				if (countryname.contains(str)) {
					List<String> adjStr = adjCountryMap.get(str);
					for (String arr : adjStr) {
						if (countryname.contains(arr)) {
							Country rec = mpsr.getCountry(str, countriesDefault);
							if (!rec.getAdjacentCountries().contains(arr)) {
								rec.getAdjacentCountries().add(arr);
								rec = mpsr.getCountry(arr, countriesDefault);
								rec.getAdjacentCountries().add(str);
							}
						} else {
							throw new MapInvalidException("Adjacent country does not exist");
						}
					}
				} else {
					throw new MapInvalidException(str + "Country does not exist. Add the country again");
				}

			}

			if (continentsDefault.size() == 0) {
				mpsr.readFile();
				continentsDefault = mpsr.continentsList;
			}

			MapFileWriter mfw = new MapFileWriter();
			mfw.writeFile(continentsDefault, countriesDefault, inputFile);
		} catch (Exception e) {
			throw new MapInvalidException("Error while adding new adjacent country. Provide a valid input");
		}
	}

	/**
	 * @param continentName continent name user wish to remove from map file
	 * @param inputFile     input map file
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */

	public void removeContinent(ArrayList<String> continentName, String inputFile)
			throws IOException, MapInvalidException {
		try {
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
				removeCountry(countriesToDelete, inputFile, false);
			}
		} catch (Exception e) {
			throw new MapInvalidException("Error while removing Continent. Provide a valid input to remove");
		}

	}

	/**
	 * @param countries country user wish to remove from input map file
	 * @param inputFile input map file
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */

	public void removeCountry(ArrayList<String> countries, String inputFile, boolean isRecursion)
			throws IOException, MapInvalidException {
		int countrytoRemove =0;
		try {
			utilities.MapParser mpsr = new utilities.MapParser(inputFile);
			mpsr.readFile();
			countriesDefault = mpsr.countriesList;
			if (worldMap.size() == 0) {
				worldMap = mpsr.worldMap;
			}
			Iterator<Country> iter = countriesDefault.iterator();
			while (iter.hasNext()) {
				Country conrec = iter.next();
				if (countries.contains(conrec.getName())) {
					worldMap.get(conrec.getContinent()).remove(conrec.getName());
					iter.remove();
					countrytoRemove++;
				}
			}
			if(countrytoRemove==0) {
				throw new MapInvalidException("The country does not exist. Provide a valid input to remove.");
			}
			removeAdjCountry(countries, inputFile, null, true);
		} catch (MapInvalidException e) {
			throw new MapInvalidException(e.getMessage());
		}catch(Exception e) {
			throw new MapInvalidException("Error while removing country. Provide a valid input to remove.");
		}

	}

	/**
	 * @param countries adjacent country user wish to remove from input map file
	 * @param inputFile input map file
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */

	public void removeAdjCountry(ArrayList<String> countries, String inputFile, Map<String, List<String>> adjMap,
			boolean isFunCall) throws IOException, MapInvalidException {
		try {
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
			if (isFunCall) {
				while (iter.hasNext()) {
					Country conrec = iter.next();
					for (String str : countries) {
						if (conrec.getAdjacentCountries().contains(str)) {
							conrec.getAdjacentCountries().remove(str);

						}
					}
				}
			} else {
				while (iter.hasNext()) {
					Country conrec = iter.next();
					if (adjMap.containsKey(conrec.getName())) {
						for (String str : adjMap.get(conrec.getName())) {
							conrec.getAdjacentCountries().remove(str);
						}
					}
				}
			}

			// check for countries without any adjacent countries and remove them if they
			// exist
			Iterator<Country> iter1 = countriesDefault.iterator();
			while (iter1.hasNext()) {
				Country conrec = iter1.next();
				if (conrec.getAdjacentCountries().size() == 0) {
					worldMap.get(conrec.getContinent()).remove(conrec.getName());
					iter1.remove();
				}
			}
			// remove continents if no country is associated with the continent
			if (!worldMap.isEmpty()) {
				Iterator<Continent> iterCon = continentsDefault.iterator();
				while (iterCon.hasNext()) {
					Continent conrec = iterCon.next();
					if (worldMap.get(conrec.getName()) == null) {
						iterCon.remove();
					}
				}
			}
			MapFileWriter mfw = new MapFileWriter();
			mfw.writeFile(continentsDefault, countriesDefault, inputFile);
		} catch (Exception e) {
			throw new MapInvalidException("Error while removing Adjacent country. Provide a valid input to remove");
		}

	}
}