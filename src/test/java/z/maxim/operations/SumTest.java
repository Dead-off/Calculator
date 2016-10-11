package z.maxim.operations;

import org.junit.Assert;
import org.junit.Test;
import z.maxim.Constants;

public class SumTest extends Assert {

    @Test
    public void calculateTest() {
        AbstractTwoArgOperation sumOne = new Sum(new NumberExpression(19), new NumberExpression(3));
        AbstractTwoArgOperation sumTwo = new Sum(new NumberExpression(22), new NumberExpression(0));
        assertEquals(sumOne.calculate(), 22, Constants.DOUBLE_EQUALS_EPS);
        assertEquals(sumTwo.calculate(), 22, Constants.DOUBLE_EQUALS_EPS);

        AbstractTwoArgOperation sumThree = new Sum(new NumberExpression(0), new NumberExpression(0));
        assertEquals(sumThree.calculate(), 0, Constants.DOUBLE_EQUALS_EPS);
    }
}
