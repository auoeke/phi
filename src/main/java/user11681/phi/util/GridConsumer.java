package user11681.phi.util;

@FunctionalInterface
public interface GridConsumer<T> {
    void accept(int x, int y, T slot);
}
