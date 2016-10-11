package z.maxim.operations;

import org.junit.Assert;
import org.junit.Test;
import z.maxim.Constants;

public class NumberExpressionTest extends Assert {

    @Test
    public void calculateTest() {
        NumberExpression numberExpressionFirst = new NumberExpression(5);
        NumberExpression numberExpressionSecond = new NumberExpression(-13);
        assertEquals(numberExpressionFirst.calculate(), 5.0, Constants.DOUBLE_EQUALS_EPS);
        assertEquals(numberExpressionSecond.calculate(), -13.0, Constants.DOUBLE_EQUALS_EPS);
    }
}
