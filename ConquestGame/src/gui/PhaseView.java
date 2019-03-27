package gui;
import beans.Observable;
import controller.GameController;

// TODO: Auto-generated Javadoc
/**
 * Implementation of a phase view using the Observer pattern. The phase view should display:
 * (1)The name of the game phase currently being played 
 * (2)The current player name
 * The phase view should be cleared at the beginning of every phase.
 *
 */
public class PhaseView implements Observer {
	
	/** The phase view. */
	private static PhaseView phaseView = null;
	
	/** The controller. */
	private GameController controller = GameController.getInstance();
	
//	private PhaseView() {
//		controller = GameController.getInstance();
//	}
	
//	public static PhaseView getInstance() {
//		if ( phaseView == null) {
//			phaseView = new PhaseView();
//		}
//		return phaseView;
//	}

	/* (non-Javadoc)
 * @see gui.Observer#update(beans.Observable)
 */
@Override
	public void update(Observable sub) {
		System.out.println("Current state : " + controller.getCurrentPhase());
		System.out.println("Current Player : " + controller.getCurrentPlayer().getPlayerName());
	}

	

}
	