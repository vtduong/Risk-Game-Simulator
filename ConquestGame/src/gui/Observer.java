/**
 * 
 */
package gui;

import beans.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Interface Observer. Every concrete observer must implement this.
 *
 * @author vanduong
 */
public interface Observer {
	
	/**
	 * Gets Updates from the subscribing subject
	 *
	 * @param sub the subject 
	 */
	public void update(Observable sub);

}
