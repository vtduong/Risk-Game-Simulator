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
 * @author apoorvasharma
 *
 */
public class MapValidator {
	private String inputFile;
	public static ArrayList<Country> countriesList = new ArrayList<Country>();
	public static ArrayList<Continent> continentsList = new ArrayList<Continent>();
	public static Graph<String, DefaultEdge> mapGraph = new SimpleGraph<>(DefaultEdge.class);
	public static ArrayList<Graph<List<Country>, DefaultEdge>> subGraphsList = new ArrayList<Graph<List<Country>, DefaultEdge>>();

	public MapValidator(String inputFile) {
		this.inputFile = inputFile;
	}

	public void createCountryGraph() throws IOException, MapInvalidException {
		MapParser mapParser = new MapParser(inputFile);
		mapParser.readFile();
		countriesList = mapParser.countriesList;
	
		if (continentsList.size() <= 1) {
			throw new MapInvalidException("There should be atleast one continent");
			/// Throw Exception In valid map
		}
		Map<String, ArrayList<Country>> worldMap = new HashMap<String, ArrayList<Country>>();
		worldMap =utilities.MapParser.worldMap;
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
			System.out.println("Exception :" + e.getStackTrace());
			System.out.println("Exception :" + e.getMessage());
			System.out.println("Exception :" + e.getClass());
		}
		// get subgraph
		System.out.println("createCountrySubGraoh");
		continentsList = mapParser.continentsList;
		for (Continent rec : continentsList) {
			subGraphsList.add(createCountrySubGraph(rec.getCountries(), mapGraph));

		}

		boolean isConnected = mapisConnected();
		if (!isConnected) {
			throw new MapInvalidException("Map is not connected");
			// throw Error
		}
		mapVisual();
		System.out.println(subGraphsList.size());

	}

	// For Map Visualization
	public void mapVisual() throws IOException {

		File imgFile = new File("src/resources/Worldmap.png");
		imgFile.createNewFile();//
		//JGraphXAdapter<Country, DefaultEdge> graphAdapter = new JGraphXAdapter<Country, DefaultEdge>(countryGraph);
		JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<String, DefaultEdge>(mapGraph);
		mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		layout.execute(graphAdapter.getDefaultParent());

		BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
		ImageIO.write(image, "PNG", imgFile);

	}

	public boolean mapisConnected() {

		boolean isConnected = false;
		if (new ConnectivityInspector<>(mapGraph).isConnected()) {
			isConnected = true;
		} else {
			isConnected = false;
		}
		return false;

	}				

	/**
	 * 
	 * @param countryList
	 * @param countryGraph
	 * @return
	 */
	private Graph<List<Country>, DefaultEdge> createCountrySubGraph(List<Country> countryList,
			Graph<String, DefaultEdge> mapGraph) {
		Set<String> countrySet = new HashSet<String>();
		for(Country con :countryList) {
			countrySet.add(con.getName());
		}
		Graph<List<Country>, DefaultEdge> subGraph = new AsSubgraph(mapGraph, countrySet);
		return subGraph;
	}

}		
