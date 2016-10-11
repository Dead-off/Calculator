package z.maxim.operations;

public abstract class AbstractTwoArgOperation implements Expression {

    final Expression arg1;
    final Expression arg2;

    protected AbstractTwoArgOperation(Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractTwoArgOperation that = (AbstractTwoArgOperation) o;

        if (arg1 != null ? !arg1.equals(that.arg1) : that.arg1 != null) return false;
        return arg2 != null ? arg2.equals(that.arg2) : that.arg2 == null;

    }

    @Override
    public int hashCode() {
        int result = arg1 != null ? arg1.hashCode() : 0;
        result = 31 * result + (arg2 != null ? arg2.hashCode() : 0);
        return result;
    }
}
