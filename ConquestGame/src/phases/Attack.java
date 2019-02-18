package phases;

import controller.GameController;

/**
 * Attack phase
 * @author vanduong
 *
 */
public class Attack implements TurnPhase{

	public void takePhase(GameController controller) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see phases.TurnPhase#setNextPhase(controller.GameController)
	 */
	@Override
	public void setNextPhase(GameController controller) {
		controller.setPhase(new Fortification());
		
	}

}
