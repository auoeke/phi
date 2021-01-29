package user11681.phi.util;

@FunctionalInterface
public interface ListConsumer<T> {
    void accept(int i, T element);
}
