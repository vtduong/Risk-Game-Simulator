package beans;

import java.util.ArrayList;
import java.util.List;

import gui.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class Continent represents a continent in the map
 *
 * @author vanduong
 */
public class Continent implements Observable {
	
	/** The name. */
	private String name;
	
	/** The max armies. */
	private int maxArmies;
	
	/** The owner. */
	private Player owner = null;
	
	private int controlValue;
	
	/** The observer list. */
	private List<Observer> obList = null;
	
	/**
	 * Instantiates a new continent.
	 *
	 * @param name      the name
	 * @param maxArmies the max armies
	 */
	public Continent(String name, int maxArmies) {
		super();
		this.name = name;
		this.maxArmies = maxArmies;
		obList = new ArrayList<Observer>();
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the max armies.
	 *
	 * @return the maxArmies
	 */
	public int getMaxArmies() {
		return maxArmies;
	}
	
	/**
	 * Sets the max armies.
	 *
	 * @param maxArmies the maxArmies to set
	 */
	public void setMaxArmies(int maxArmies) {
		this.maxArmies = maxArmies;
	}
	
	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Sets the owner.
	 *
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	/* (non-Javadoc)
	 * @see beans.Observable#attach(gui.Observer)
	 */
	@Override
	public void attach(Observer ob) {
		obList.add(ob);
		
	}
	/* (non-Javadoc)
	 * @see beans.Observable#detach(gui.Observer)
	 */
	@Override
	public void detach(Observer ob) {
		obList.remove(ob);
		
	}
	/* (non-Javadoc)
	 * @see beans.Observable#notifyChanges()
	 */
	@Override
	public void notifyChanges() {
		for(Observer o : obList) {
			o.update(this);
		}
		
	}

	public int getControlValue() {
		return controlValue;
	}

	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}
	
	
}