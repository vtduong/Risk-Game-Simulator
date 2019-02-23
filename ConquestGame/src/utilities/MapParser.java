package utilities;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Continent;
import beans.Country;;

/** 
 * 
 * @author apoorvasharma
 *
 */

public class MapParser {
	private String inputFile;
	private Scanner sc;
	/*MapParser(String inputFile){
		this.inputFile = inputFile;
	}*/
	private static String buildMapFile;
	public static ArrayList<Country> countriesList =new ArrayList<Country>();
	public static ArrayList<Continent> continentsList =new ArrayList<Continent>();
	
	public static void main(String argv[]) {
		
		readFile();
	}
	
	/**
	 * 
	 * 
	 */
	public static void readFile() {
		try {
			System.out.println(System.getProperty("user.dir"));
			Scanner sc = new Scanner(new File("/Users/apoorvasharma/git/SOEN_6441/ConquestGame/src/resources/World.map"));
			String tempStr = null;
			buildMapFile = "";
			while(sc.hasNext()) {
				tempStr =sc.nextLine();
				buildMapFile = buildMapFile+""+tempStr+"\n";
			}
					    
			continentsList=parseContinents(buildMapFile.substring(buildMapFile.indexOf("[Continents]"), buildMapFile.indexOf("[Territories]")));
			countriesList = new ArrayList<Country>();
			countriesList=parseCountries(buildMapFile.substring(buildMapFile.indexOf("[Territories]")));
		}catch(Exception e) {
			
		}
		
	}
	
	/**
	 * 
	 * @param continents
	 * @return
	 */
	private static ArrayList<Continent> parseContinents(String continents){
		ArrayList<Continent> continentList = new ArrayList<Continent>();
		System.out.println("Parsing Continents!!"+continents);
		String continent[] = continents.split("\n");
		try {
			for(int i=1;i<continent.length;i++){
				if(!continent[i].equalsIgnoreCase("[Continents]")) {
					String[] temp = continent[i].trim().split("=");
					Continent con = new Continent();
					con.setControlValue(temp[1].trim());
					con.setName(temp[0].trim());
					continentList.add(con);
				}
			}
		}catch(Exception e) {
			//TODO
			System.out.println("Exception :"+ e.getStackTrace());
		}
		return continentList;
	}
	
	/**
	 * 
	 * @param sc
	 * @return
	 */
	private static ArrayList<Country>  parseCountries(String countries) {
		ArrayList<Country> countryList =new ArrayList<Country>();
		System.out.println("Parsing Countries!!");
		String country[] = countries.split("\n");
		for(int i =1;i<country.length;i++) {
			String[] temp = country[i].trim().split(","); //Alaska,70,126,North America,
			Country con = new Country();
			con.setName(temp[0].trim());
			con.setLatitude(Integer.parseInt(temp[1].trim()));
			con.setlongitude(Integer.parseInt(temp[2].trim()));
			con.setContinent(temp[3].trim());
			countryList.add(con);
			//System.out.println(country[1].trim()+" "+" temp[0].trim()"+temp[3].trim());
		}
		System.out.println(countryList.size());
		return countryList;
	}


}
