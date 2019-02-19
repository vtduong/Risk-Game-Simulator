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
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see phases.TurnPhase#setNextPhase(controller.GameController)
	 */
	@Override
	public void setNextPhase() {
		controller.setPhase(new Fortification());
		
	}

}
