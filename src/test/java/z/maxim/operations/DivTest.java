package z.maxim.operations;

import org.junit.Assert;
import org.junit.Test;
import z.maxim.Constants;

public class DivTest extends Assert {

    @Test(expected = ArithmeticException.class)
    public void calculateTestOnExpression() {
        AbstractTwoArgOperation div = new Div(new NumberExpression(5), new NumberExpression(0));
        div.calculate();
    }

    @Test
    public void calculateTest() {
        AbstractTwoArgOperation div = new Div(new NumberExpression(5), new NumberExpression(5));
        assertEquals(div.calculate(), 1, Constants.DOUBLE_EQUALS_EPS);
        div = new Div(new NumberExpression(7), new NumberExpression(2));
        assertEquals(div.calculate(), 3.5, Constants.DOUBLE_EQUALS_EPS);
    }
}
