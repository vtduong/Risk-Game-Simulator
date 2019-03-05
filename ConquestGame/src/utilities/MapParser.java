package utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import beans.Continent;
import beans.Country;
import exception.MapInvalidException;

/**
 * This class is used to handle the parsing of map files
 * 
 * @author apoorvasharma
 * @version 1.0.0
 */

public class MapParser {

	private String inputFile;
	private Scanner sc;

	/**
	 * @param inputFile input map file
	 */
	public MapParser(String inputFile) {
		this.inputFile = inputFile;
	}

	private String buildMapFile;
	public ArrayList<Country> countriesList = new ArrayList<Country>();
	public ArrayList<Continent> continentsList = new ArrayList<Continent>();
	public static Map<String, ArrayList<Country>> worldMap = new HashMap<String, ArrayList<Country>>();

	/**
	 * This method reads the input file for parsing
	 * @throws MapInvalidException 
	 * @throws FileNotFoundException 
	 */
	public void readFile() throws MapInvalidException, FileNotFoundException {
		try {
			Scanner sc = new Scanner(new File(inputFile));
			String tempStr = null;
			buildMapFile = "";
			while (sc.hasNext()) {
				tempStr = sc.nextLine();
				buildMapFile = buildMapFile + "" + tempStr + "\n";
			}
			countriesList = parseCountries(buildMapFile.substring(buildMapFile.indexOf("[Territories]")));
			continentsList = parseContinents(buildMapFile.substring(buildMapFile.indexOf("[Continents]"),
					buildMapFile.indexOf("[Territories]")));
		} catch (MapInvalidException e) {
			throw new MapInvalidException(e.getMessage());
		} catch(FileNotFoundException ex) {
			throw new MapInvalidException("Map file does not exist.");
		} catch(Exception e) {
			throw new MapInvalidException("Issue parsing the input file.Ensure correct input is fed.");
		}

	}

	/**
	 * This method parse all the continents and store them in List
	 * 
	 * @param continents String that contains all the continent information
	 * @return list of continents
	 * @throws MapInvalidException invalid map exception
	 */
	private static ArrayList<Continent> parseContinents(String continents) throws MapInvalidException {
		ArrayList<Continent> continentList = new ArrayList<Continent>();
		// System.out.println("Parsing Continents!!"+continents);
		String continent[] = continents.split("\n");
		try {
			for (int i = 1; i < continent.length; i++) {
				if (!continent[i].equalsIgnoreCase("[Continents]") && continent[i] != null && continent[i].contains("=")) {
					String[] temp = continent[i].trim().split("=");
					Continent con = new Continent();
					con.setMaxArmies(Integer.parseInt((temp[1].trim())));
					con.setName(temp[0].trim());
					con.setCountries(worldMap.get(temp[0].trim()));
					continentList.add(con);
				}
			}
		} catch (Exception e) {
			throw new MapInvalidException("Error in parsing of Continents. Provide a valid map file");
		}
		return continentList;
	}

	/**
	 * This method parse all the countries in input map and store them in list
	 * 
	 * @param country String that contains all the country information
	 * @return list of all the countries
	 * @throws MapInvalidException invalid map exception
	 */
	private static ArrayList<Country> parseCountries(String countries) throws MapInvalidException {
		ArrayList<Country> countryList = new ArrayList<Country>();
		// System.out.println("Parsing Countries!!");
		String country[] = countries.split("\n");
		// System.out.println(country.length);
		try {
			for (int i = 1; i < country.length; i++) {
				if (!country[i].isEmpty() && !country[i].equalsIgnoreCase("[Territories]")) {
					String[] temp = country[i].trim().split(","); // Alaska,70,126,North America,
					if (temp != null) {
						Country con = new Country();
						con.setName(temp[0].trim());
						con.setLatitude(Integer.parseInt(temp[1].trim()));
						con.setlongitude(Integer.parseInt(temp[2].trim()));
						con.setContinent(temp[3].trim());
						countryList.add(con);
					}
				}
			}
			addAdjacentCountries(country, countryList);

		} catch (Exception e) {
			throw new MapInvalidException("Error in parsing of countries. Provide a valid map file");
		}
		return countryList;

	}

	/**
	 * This method gets all the countries records
	 * 
	 * @param name        name of country to get
	 * @param countryList list of all countries
	 * @return country record
	 */
	public static Country getCountry(String name, ArrayList<Country> countryList) {
		Country recToReturn = new Country();
		for (Country rec : countryList) {
			if (rec.getName().equals(name)) {
				recToReturn = rec;
			}
		}

		return recToReturn;
	}

	/**
	 * This method parse the adjacent countries in input map file and store them in
	 * a list
	 * 
	 * @param country     country whose adjacent country needs to be parsed
	 * @param countryList list of all adjacent countries
	 */
	private static void addAdjacentCountries(String country[], ArrayList<Country> countryList) {
		for (int i = 1; i < country.length; i++) {
			String[] temp = country[i].trim().split(",");
			Country rec = getCountry(temp[0].trim(), countryList);
			ArrayList<String> adjCountry = new ArrayList<String>();
			for (int j = 4; j < temp.length; j++) {
				if (temp[j] != null) {
					adjCountry.add(temp[j].trim());
				}
			}
			rec.setAdjacentCountries(adjCountry);

			if (worldMap.get(rec.getContinent()) != null) {
				ArrayList<Country> tempList = new ArrayList<Country>();
				tempList.addAll(worldMap.get(rec.getContinent()));
				tempList.add(rec);
				worldMap.put(rec.getContinent(), tempList);

			} else {
				ArrayList<Country> tempList = new ArrayList<Country>();
				tempList.add(rec);
				worldMap.put(rec.getContinent(), tempList);
			}

		}
	}

}
