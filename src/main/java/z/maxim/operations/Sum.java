package z.maxim.operations;

public class Sum extends AbstractTwoArgOperation {

    public Sum(Expression arg1, Expression arg2) {
        super(arg1, arg2);
    }

    @Override
    public double calculate() {
        return arg1.calculate() + arg2.calculate();
    }
}
