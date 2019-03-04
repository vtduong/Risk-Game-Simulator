package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import beans.Continent;
import beans.Country;

/**
 * This class writes into the existing input map and update it accordingly
 * 
 * @author apoorvasharma version 1.0.0
 */
public class MapFileWriter {

	/**
	 * @param continentsDefault list of continents already present after parsing
	 *                          input map file
	 * @param countriesDefault  list of countries already present after parsing
	 *                          input map file
	 * @param inputfile         input map file
	 * @throws IOException file handling exception
	 */
	public void writeFile(ArrayList<Continent> continentsDefault, ArrayList<Country> countriesDefault, String inputfile)
			throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(inputfile, false));
		bw.write("[Map]" + "\n");
		bw.write("author" + "=" + "userdefiend map \n");
		bw.write("[Continents]" + "\n");
		for (Continent conRec : continentsDefault) {
			bw.write(conRec.getName() + "=" + conRec.getMaxArmies() + "\n");
		}

		bw.write("[Territories]" + "\n");
		for (Country conRec : countriesDefault) {
			String adjCountry = ",";
			for (String s : conRec.getAdjacentCountries()) {
				adjCountry = adjCountry.concat(s) + ",";
			}
			bw.write(conRec.getName() + "," + conRec.getLatitude() + "," + conRec.getlongitude() + ","
					+ conRec.getContinent() + adjCountry + "\n");
		}

		bw.close();
	}

}
