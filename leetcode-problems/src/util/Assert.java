package util;

import java.util.Objects;
import java.util.function.Supplier;

public final class Assert {

    private Assert() {
        //
    }

    public static <T> void printAndAssert(Supplier<T> supplier, T expected) {
        final T actual = supplier.get();
        System.out.println(actual);
        assert Objects.equals(actual, expected);
    }

}
