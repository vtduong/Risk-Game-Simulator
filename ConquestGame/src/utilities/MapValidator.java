package utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import beans.Continent;
import beans.Country;

/**
 * @author apoorvasharma
 *
 */
public class MapValidator {
	private String inputFile;
	public static ArrayList<Country> countriesList = new ArrayList<Country>();
	public static ArrayList<Continent> continentsList = new ArrayList<Continent>();
	public static Graph<Country, DefaultEdge> countryGraph = new SimpleGraph<>(DefaultEdge.class);
	public static ArrayList<Graph<List<Country>, DefaultEdge>> subGraphsList = new ArrayList<Graph<List<Country>, DefaultEdge>>();

	public MapValidator(String inputFile) {
		this.inputFile = inputFile;
	}

	public void createCountryGraph() {
		new utilities.MapParser(inputFile).readFile();
		countriesList = utilities.MapParser.countriesList;
		try {
			// adding the nodes to the graph
			for (Country rec : countriesList) {
				countryGraph.addVertex(rec);
			}

			// adding the edges to the graph
			for (Country conRec : countryGraph.vertexSet()) {
				ArrayList<String> adjCountry = new ArrayList<String>();
				adjCountry.addAll(conRec.getAdjacentCountries());
				for (String str : adjCountry) {
					Country countryRec = utilities.MapParser.getCountry(str, countriesList);
					countryGraph.addEdge(conRec, countryRec);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception :" + e.getStackTrace());
			System.out.println("Exception :" + e.getMessage());
			System.out.println("Exception :" + e.getClass());
		}
		// get subgraph
		System.out.println("createCountrySubGraoh");
		continentsList = utilities.MapParser.continentsList;
		for (Continent rec : continentsList) {
			subGraphsList.add(createCountrySubGraph(rec.getCountries(), countryGraph));

		}
		System.out.println(subGraphsList.size());

		/***
		 * Check if edge exists!!! Country recToReturn = new Country(); recToReturn
		 * =utilities.MapParser.getCountry("Alaska",countriesList); Country recToReturn1
		 * = new Country(); recToReturn1
		 * =utilities.MapParser.getCountry("Alberta",countriesList);
		 * 
		 * //System.out.println(recToReturn.getAdjacentCountries());
		 * //System.out.println(countryGraph.getEdge(recToReturn, recToReturn1));
		 */

	}

	/**
	 * 
	 * @param countryList
	 * @param countryGraph
	 * @return
	 */
	private Graph<List<Country>, DefaultEdge> createCountrySubGraph(List<Country> countryList,
			Graph<Country, DefaultEdge> countryGraph) {
		Set<Country> countrySet = new HashSet<Country>(countryList);
		Graph<List<Country>, DefaultEdge> subGraph = new AsSubgraph(countryGraph, countrySet);
		return subGraph;
	}

}
