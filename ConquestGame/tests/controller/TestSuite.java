/**
 * 
 */
package controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import beans.BeansTest;
import gui.GUITest;
import phases.FortificationTest;
import phases.ReEnforcementTest;
import utilities.DiceRollerTest;
import utilities.MapParserTest;

/**
 * @author vanduong
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ControllerTest.class,
				BeansTest.class,
				GUITest.class,
				FortificationTest.class,
				ReEnforcementTest.class,
				DiceRollerTest.class,
				MapParserTest.class})
public class TestSuite {

}
