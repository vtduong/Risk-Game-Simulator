package phases;

import beans.Player;
import controller.GameController;

/**
 * Attack phase
 * @author vanduong
 *
 */
public class Attack implements TurnPhase{
	
	GameController controller = GameController.getInstance();
	private Player curPlayer = controller.getCurrentPlayer();
	

	public void takePhase() {
		System.out.println("Attack phase is coming soon...");
	}

	/* (non-Javadoc)
	 * @see phases.TurnPhase#setNextPhase(controller.GameController)
	 */
	@Override
	public void setNextPhase() {
		controller.setPhase(new Fortification());
		
	}

}
