package z.maxim;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import z.maxim.operations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CalculatorTest extends Assert {

    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void calculateTest() {
        List<Expression> expressions = new ArrayList<>();
        assertEquals(calculator.calculate(expressions), new ArrayList<Double>());
        Expression expressionOne = new Mul(new Sum(new NumberExpression(3), new NumberExpression(1)), new Sub(new NumberExpression(5), new NumberExpression(-2)));
        Expression expressionTwo = new Div(new Sub(new NumberExpression(12), new NumberExpression(55)), new Sum(new NumberExpression(10), new NumberExpression(22)));
        assertEquals(calculateOneExpression(expressionOne), 28, Constants.DOUBLE_EQUALS_EPS);

        expressions.add(expressionOne);
        expressions.add(expressionTwo);

        List<Double> result = new ArrayList<>();
        result.addAll(Arrays.asList(28.0, -1.44375));
        assertEquals(calculator.calculate(expressions), result);
    }

    private Double calculateOneExpression(Expression expression) {
        List<Expression> expressions = Collections.singletonList(expression);
        return calculator.calculate(expressions).get(0);
    }
}
