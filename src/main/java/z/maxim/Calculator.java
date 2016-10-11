package z.maxim;

import z.maxim.operations.Expression;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public List<Double> calculate(List<Expression> expressions) {
        List<Double> result = new ArrayList<>(expressions.size());
        for (Expression expression : expressions) {
            result.add(calculateExpression(expression));
        }
        return result;
    }

    private double calculateExpression(Expression expression) {
        return expression.calculate();
    }
}
