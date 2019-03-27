package utilities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class UtilTestSuite.
 */
@RunWith(Suite.class)
@SuiteClasses({ DiceRollerTest.class, MapParserTest.class, MapTest.class,
		MapValidatorTest.class, CardExchangeTest.class })
public class UtilTestSuite {

}
