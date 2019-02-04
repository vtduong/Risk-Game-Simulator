package phases;

import controller.GameController;

/**
 * TurnPHase interface declares method(s) for other concrete phases to implement
 * This class is a part of State design pattern used for implementing player's phases
 * @author vanduong
 *
 */
public interface TurnPhase {
	
	public boolean nextPhase(GameController controller);
}
