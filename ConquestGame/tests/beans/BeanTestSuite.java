package beans;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class BeanTestSuite.
 */
@RunWith(Suite.class)
@SuiteClasses({ ContinentTest.class, CountryTest.class, PlayerTest.class })
public class BeanTestSuite {

}
