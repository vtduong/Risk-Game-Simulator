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
import utilities.MapUpdateTest;
import utilities.UtilTestSuite;

/**
 * @author vanduong
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ControllerTest.class,
				BeanTestSuite.class,
				GUITest.class,
				PhaseTestSuite.class,
				UtilTestSuite.class,
				DiceRollerTest.class,
				MapParserTest.class,
				MapUpdateTest.class})
public class TestSuite {

}
