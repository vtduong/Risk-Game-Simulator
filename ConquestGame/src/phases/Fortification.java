package phases;

import controller.GameController;

/**
 * Fortification phase
 * @author vanduong
 *
 */
public class Fortification implements TurnPhase{

	public void nextPhase(GameController controller) {
		//move armies from one (and only one) country to another neighboring country
		
		moveArmies();
		//set phase back to before re-enforcement
		controller.setPhase(null);
	}

	/**
	 * 
	 */
	private void moveArmies() {
		// TODO Auto-generated method stub
		
	}

}
