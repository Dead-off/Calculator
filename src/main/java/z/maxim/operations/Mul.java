package z.maxim.operations;

public class Mul extends AbstractTwoArgOperation {

    public Mul(Expression arg1, Expression arg2) {
        super(arg1, arg2);
    }

    @Override
    public double calculate() {
        return arg2.calculate() * arg1.calculate();
    }
}
