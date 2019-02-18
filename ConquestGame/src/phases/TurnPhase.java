package phases;

import controller.GameController;

// TODO: Auto-generated Javadoc
/**
 * TurnPHase interface declares method(s) for other concrete phases to implement
 * This class is a part of State design pattern used for implementing player's
 * phases.
 *
 * @author vanduong
 */
public interface TurnPhase {
	
	/**
	 * abstract method. Implementing classes have to define what to do before going to next phase
	 * @param controller the Game Controller instance.
	 */
	public void takePhase(GameController controller);
	
	/**
	 * Sets the next phase.
	 *
	 * @param controller the Game Controller instance
	 */
	public void setNextPhase(GameController controller);
}
