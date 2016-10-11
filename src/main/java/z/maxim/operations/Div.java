package z.maxim.operations;

public class Div extends AbstractTwoArgOperation {

    public Div(Expression arg1, Expression arg2) {
        super(arg1, arg2);
    }

    @Override
    public double calculate() {
        double arg2Value = arg2.calculate();
        if (arg2Value == 0) {
            throw new ArithmeticException("division on zero");
        }
        return arg1.calculate() / arg2Value;
    }
}
