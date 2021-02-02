package user11681.phi.util;

import java.util.function.Consumer;

public interface Util {
    static void ifNonnull(Object object, Runnable action) {
        if (object != null) {
            action.run();
        }
    }

    static <T> void ifNonnull(T object, Consumer<T> action) {
        if (object != null) {
            action.accept(object);
        }
    }
}
