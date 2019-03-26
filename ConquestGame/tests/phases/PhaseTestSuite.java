package phases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class PhaseTestSuite.
 */
@RunWith(Suite.class)
@SuiteClasses({ FortificationTest.class,
				ReEnforcementTest.class,
				AttackTest.class})
public class PhaseTestSuite {

}
