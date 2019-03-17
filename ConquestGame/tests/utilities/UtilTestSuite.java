package utilities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DiceRollerTest.class, EditMapTest.class, MapParserTest.class, MapUpdateTest.class,
		MapValidatorTest.class })
public class UtilTestSuite {

}
