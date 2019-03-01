package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import beans.Continent;
import beans.Country;

/**
 * @author apoorvasharma
 *
 */
public class MapFileWriter {
   /**
 * @param continentsDefault
 * @param countriesDefault
 * @param inputfile
 * @throws IOException
 */
public void writeFile(ArrayList<Continent> continentsDefault,ArrayList<Country> countriesDefault,String inputfile) throws IOException {
	   BufferedWriter bw = new BufferedWriter(new FileWriter(inputfile, false));
	   bw.write("[Continents]" + "\n");
	   for(Continent conRec:continentsDefault) {
		   bw.write(conRec.getName() + "=" + conRec.getMaxArmies() + "\n");
	   }
	   
	   bw.write("[Territories]" + "\n");
	   for(Country conRec:countriesDefault) {
		   String adjCountry=",";
		   for(String s:conRec.getAdjacentCountries()) {
			   adjCountry=adjCountry.concat(s)+",";
		   }
		   bw.write(conRec.getName() + "," + conRec.getLatitude()+","+conRec.getlongitude()+","+conRec.getContinent()+adjCountry+ "\n");
	   }
	  
	   bw.close();
   }
	   
}
