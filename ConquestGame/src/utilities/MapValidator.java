package utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import org.jgrapht.*;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

import beans.Continent;
import beans.Country;
import exception.MapInvalidException;

/**
 * This class creates the main graph of countries and subgraphs of continents.
 * It also validates the input map file
 * 
 * @author apoorvasharma version 1.0.0
 *
 */
public class MapValidator {
	private String inputFile;
	public static ArrayList<Country> countriesList = new ArrayList<Country>();
	public static ArrayList<Continent> continentsList = new ArrayList<Continent>();
	public static Graph<String, DefaultEdge> mapGraph = new SimpleGraph<>(DefaultEdge.class);
	public static ArrayList<Graph<List<Country>, DefaultEdge>> subGraphsList = new ArrayList<Graph<List<Country>, DefaultEdge>>();

	/**
	 * @param inputFile input map file
	 */
	public MapValidator(String inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * This method creates the main graph of all countries
	 * 
	 * @throws IOException         file handling exception
	 * @throws MapInvalidException invalid map exception
	 */
	public void createCountryGraph() throws IOException, MapInvalidException {
		MapParser mapParser = new MapParser(inputFile);
		mapParser.readFile();
		countriesList = mapParser.countriesList;
		continentsList = mapParser.continentsList;
		if (continentsList.size() < 1) {
			throw new MapInvalidException("There should be atleast one continent");
		}
		duplicateCountries(countriesList);
		try {
			// adding the nodes to the graph
			for (Country rec : countriesList) {
				mapGraph.addVertex(rec.getName());
			}

			// adding the edges to the graph

			for (Country conRec : countriesList) {
				ArrayList<String> adjCountry = new ArrayList<String>();
				adjCountry.addAll(conRec.getAdjacentCountries());
				for (String str : adjCountry) {
					Country countryRec = utilities.MapParser.getCountry(str, countriesList);
					mapGraph.addEdge(conRec.getName(), str);
				}
			}

		} catch (Exception e) {
			throw new MapInvalidException("Abandoned Territory Detected");
		}

		continentsList = mapParser.continentsList;
		for (Continent rec : continentsList) {
			subGraphsList.add(createCountrySubGraph(rec.getCountries(), mapGraph));

		}

		boolean isConnected = mapisConnected();
		if (!isConnected) {
			throw new MapInvalidException("Map is not connected. Provide a valid map input");
		}
		mapVisual();

	}

	/**
	 * This method handles the visualization of map graph
	 * 
	 * @throws IOException file handling exception
	 */
	public void mapVisual() throws IOException {

		File imgFile = new File("src/resources/Worldmap.png");
		imgFile.createNewFile();//
		JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<String, DefaultEdge>(mapGraph);
		mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		layout.execute(graphAdapter.getDefaultParent());

		BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
		ImageIO.write(image, "PNG", imgFile);

	}

	/**
	 * @return if input map is connected or not
	 */
	public boolean mapisConnected() {

		boolean isConnected = false;
		if (new ConnectivityInspector<>(mapGraph).isConnected()) {
			isConnected = true;
		} else {
			isConnected = false;
		}
		return isConnected;

	}

	/**
	 * This method handles if the input map file has any duplicate country or not
	 * 
	 * @param defaultCountryList list of countries which are parsed in map input
	 *                           file
	 * @throws MapInvalidException invalid map exception
	 */

	private void duplicateCountries(ArrayList<Country> defaultCountryList) throws MapInvalidException {
		HashMap<String, Country> duplicateCheckMap = new HashMap<String, Country>();
		for (Country rec : defaultCountryList) {
			if (duplicateCheckMap.get(rec.getName()) != null) {
				throw new MapInvalidException(
						"Invalid Map.Country " + rec.getName() + " is associated with more than 1 continent.");
			} else {
				duplicateCheckMap.put(rec.getName(), rec);
			}
		}
	}

	/**
	 * @param countryList list of all countries in input map file
	 * @param mapGraph    main graph of input map file
	 * @return continent subgraphs
	 * @throws MapInvalidException invalid map exception
	 */
	private Graph<List<Country>, DefaultEdge> createCountrySubGraph(List<Country> countryList,
			Graph<String, DefaultEdge> mapGraph) throws MapInvalidException {
		Set<String> countrySet = new HashSet<String>();
		for (Country con : countryList) {
			countrySet.add(con.getName());
		}
		Graph<List<Country>, DefaultEdge> subGraph = new AsSubgraph(mapGraph, countrySet);
		/*
		 * if(!new ConnectivityInspector<>(subGraph).isConnected()) { throw new
		 * MapInvalidException("Subgraph is not connected"); }
		 */
		return subGraph;
	}

}
