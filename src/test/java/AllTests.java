import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import z.maxim.CalculatorTest;
import z.maxim.XmlToExpressionParserTest;
import z.maxim.operations.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({XmlToExpressionParserTest.class, CalculatorTest.class, DivTest.class,
        MulTest.class, NumberExpressionTest.class, SubTest.class, SumTest.class})
public class AllTests {

}
