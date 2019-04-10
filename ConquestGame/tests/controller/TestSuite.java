/**
 * 
 */
package controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import beans.BeanTestSuite;
import gui.GUITest;
import phases.PhaseTestSuite;
import utilities.DiceRollerTest;
import utilities.MapParserTest;
import utilities.MapTest;
import utilities.MapUpdateTest;
import utilities.MapValidatorTest;
import utilities.UtilTestSuite;

/**
 * The Class TestSuite.
 *
 * @author vanduong
 */
@RunWith(Suite.class)
@SuiteClasses({ ControllerTest.class,
				BeanTestSuite.class,
				GUITest.class,
				PhaseTestSuite.class,
				UtilTestSuite.class,
				DiceRollerTest.class,
				MapParserTest.class,
				MapValidatorTest.class,
				TournamentModeTest.class,
				MapTest.class})
public class TestSuite {

}
