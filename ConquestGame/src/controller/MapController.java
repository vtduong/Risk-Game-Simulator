package controller;

import beans.Continent;
import beans.Country;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapController {

	public void createMap() throws IOException {
	}

	public void validateMap(String inputFile) throws IOException {
		new utilities.MapValidator(inputFile).createCountryGraph();
	}

	private void editMap(String inputFile) {

	}

	private static void setMapDetails(BufferedWriter bw) throws IOException {
		bw.write("[Map]");
	}

	private static void addContinent(Map<String, Integer> continentMap, BufferedWriter bw) throws IOException {
		bw.write("[Continents]" + "\n");
		for (String key : continentMap.keySet()) {
			bw.write(key + "=" + continentMap.get(key) + "\n");
		}

	}

	private static void addCountry(String [] arrayList,BufferedWriter bw) throws IOException {
		bw.write("[Territories]" + "\n");
		for(int k=0;k<arrayList.length;k++) {
			bw.write(arrayList[k] +"\n");
		}
		

	}

	private void removeContinet() {

	}

	private void removeCountry() {

	}

}
