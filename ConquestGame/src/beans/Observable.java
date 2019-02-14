/**
 * 
 */
package beans;

import java.util.List;

import gui.Observer;

/**
 * @author vanduong
 *
 */
public interface Observable {

	public void attach(Observer ob);
	public void detach(Observer ob);
	public void notifyChanges(List<Observer> obList);
}
