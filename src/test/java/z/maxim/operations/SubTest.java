package z.maxim.operations;

import org.junit.Assert;
import org.junit.Test;
import z.maxim.Constants;

public class SubTest extends Assert {

    @Test
    public void calculateTest() {
        AbstractTwoArgOperation subOne = new Sub(new NumberExpression(5), new NumberExpression(5));
        AbstractTwoArgOperation subTwo = new Sub(new NumberExpression(5), new NumberExpression(-5));
        assertEquals(subOne.calculate(), 0, Constants.DOUBLE_EQUALS_EPS);
        assertEquals(subTwo.calculate(), 10, Constants.DOUBLE_EQUALS_EPS);
        AbstractTwoArgOperation subThree = new Sub(new NumberExpression(-3), new NumberExpression(0));
        assertEquals(subThree.calculate(), -3, Constants.DOUBLE_EQUALS_EPS);
    }
}
