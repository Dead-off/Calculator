package z.maxim.operations;

import org.junit.Assert;
import org.junit.Test;
import z.maxim.Constants;

public class MulTest extends Assert {

    @Test
    public void calculateTest() {
        AbstractTwoArgOperation mulOne = new Mul(new NumberExpression(7), new NumberExpression(15));
        AbstractTwoArgOperation mulTwo = new Mul(new NumberExpression(21), new NumberExpression(5));
        assertEquals(mulOne.calculate(), mulTwo.calculate(), Constants.DOUBLE_EQUALS_EPS);
        AbstractTwoArgOperation mulThree =
                new Mul(new Mul(new NumberExpression(3), new NumberExpression(3)), new NumberExpression(7));
        assertEquals(mulThree.calculate(), 63, Constants.DOUBLE_EQUALS_EPS);
    }
}
