package utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Continent;
import beans.Country;
import beans.Phase;
import beans.Player;
import controller.GameController;
import controller.MapController;
import gui.CardExchangeView;
import gui.PhaseView;
import gui.UI;
import gui.WorldDominationView;

public class GameInit implements Serializable {
	
	public GameInit() {
		super();
		
		GameController gameController = GameController.getInstance();
		MapController mapController = MapController.getInstance();
		CustomMapGenerator customMap = CustomMapGenerator.getInstance();
		
		// Game controller attributes.
		this.gc = gameController;
		this.attackingCountry = gameController.getAttackingCountry();
		this.isSavedGame = true;
		this.currentPhase = gameController.getCurrentPhase();
		this.wdView = gameController.getWorldDominationView();
		this.phaseView = gameController.getPhaseView();
		this.cardView = gameController.getCardExchangeView();
		this.countryList = gameController.getCountryList();
		this.numberOfPlayers = gameController.getNumberOfPlayers();
		this.continentListByName = gameController.getContinentListByName();
		this.countryOwnership = gameController.getCountryOwnership();
		this.readyForNextPhase = gameController.getReadyForNextPhase();
		this.currentPlayer = gameController.getCurrentPlayer();
		this.playerList = gameController.getPlayerList();
		this.winner = gameController.getWinner();
		this.ui = gameController.getUI();
		this.phaseCount = gameController.getPhaseCount();
		this.customMap = gameController.getCustomMapGenerator();
		this.continentList = gameController.getContinetList();
		this.gameStat = gameController.getGameStat();
		
		// Map controller attributes.
		this.countriesDefault = mapController.countriesDefault;
		this.continentsDefault = mapController.continentsDefault;
		this.worldMap = mapController.worldMap;
		this.mpsr = mapController.mpsr;
		this.countrymap = mapController.countrymap;
		
		// custom map generator.
		this.continents = customMap.getContinents();
		this.countries = customMap.getCountries();
		this.removeContinents = customMap.getRemoveContinents();
		this.removeCountries = customMap.getRemoveCountries();
		this.removeAdjacentCountries = customMap.getRemoveAdjacentCountries();
		this.adjMap = customMap.getAdjMap();
		this.countryDefault = customMap.countryDefault;
		this.adjCountryMap = customMap.getAdjCountryMap();
		this.editMap = customMap.getEditMap();
		this.continentmap = customMap.getContinentmap();
	}

	// Game controller attributes.
	public GameController gc = null;
	public  Country attackingCountry = null;
	public boolean isSavedGame = false;
	public Phase currentPhase = null;
	public WorldDominationView wdView = null;
	public PhaseView phaseView = null;
	public CardExchangeView cardView= null;
	public  List<Country> countryList = null;
	public int numberOfPlayers = 0;
	public HashMap<String, Continent> continentListByName = null;
	public Map<Player, ArrayList<Country>> countryOwnership = null;
	public boolean readyForNextPhase = false;
	public Player currentPlayer = null;
	public ArrayList<Player> playerList = null;
	public Player winner = null;
	public UI ui = null;
	public int phaseCount = 0;
	public CustomMapGenerator customMap=null;
	public List<Continent> continentList = null;
	public GameStat gameStat = null;
	
	// Map controller attributes.
	public MapController mc = null;
	public ArrayList<Country> countriesDefault = null;
	public ArrayList<Continent> continentsDefault = null;
	public Map<String, ArrayList<Country>> worldMap = null;
	public MapParser mpsr = null;
	public Map<String, Country> countrymap = null;

	// Custom map generator.
	public CustomMapGenerator cmg = null;
	public Map<String, Integer> continents = null;
	public ArrayList<String> countries = null;
	public ArrayList<String> removeContinents = null;
	public ArrayList<String> removeCountries = null;
	public ArrayList<String> removeAdjacentCountries = null;
	public Map<String,List<String>> adjMap =null;
	public List<Country> countryDefault =null;
	public Map<String, Country> countryMap=null;
	public Map<String,List<String>> adjCountryMap = null;
	public EditMap editMap = null;
	
	//Common attributes for custom map generator and map controller
	public Map<String, Continent> continentmap=null;
	
	
}
