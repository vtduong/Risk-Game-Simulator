package phases;

import controller.GameController;

public interface TurnPhase {
	
	public boolean nextPhase(GameController controller);
}
