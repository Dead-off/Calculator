package z.maxim.operations;

public class NumberExpression implements Expression {

    private final long value;//возможно переполнение, в файлике может быть записано число, большее, чем Long.MAX_VALUE

    public NumberExpression(long value) {
        this.value = value;
    }

    @Override
    public double calculate() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberExpression that = (NumberExpression) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
