package gui;
import beans.Observable;
import controller.GameController;

/**
 * Implementation of a �phase view� using the Observer pattern. The phase view should display:
 * (1)The name of the game phase currently being played 
 * (2)The current player�s name
 * (3)Information about actions that are taking place during this phase. 
 * The phase view should be cleared at the beginning of every phase.
 * @author ankit
 *
 */
public class PhaseView implements Observer {
	
	private static PhaseView phaseView = null;
	private GameController controller;
	
	private PhaseView() {
		controller = GameController.getInstance();
	}
	
	public static PhaseView getInstance() {
		if ( phaseView == null) {
			phaseView = new PhaseView();
		}
		return phaseView;
	}

	@Override
	public void update(Observable sub) {
		System.out.println("Current state : " + controller.getCurrentPhase());
		System.out.println("Current Player : " + controller.getCurrentPlayer().getPlayerName());
	}

	

}
