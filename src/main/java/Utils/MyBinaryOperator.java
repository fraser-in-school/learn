package Utils;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

@FunctionalInterface
public interface MyBinaryOperator<T> extends BinaryOperator<T> {
    /**
     * Returns a {@link BinaryOperator} which returns the lesser of two elements
     * according to the specified {@code Comparator}.
     *
     * @param <T> the type of the input arguments of the comparator
     * @param comparator a {@code Comparator} for comparing the two values
     * @return a {@code BinaryOperator} which returns the lesser of its operands,
     *         according to the supplied {@code Comparator}
     * @throws NullPointerException if the argument is null
     */
    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    public static <T> BinaryOperator<T> add(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return null;
    }
}
