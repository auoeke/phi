package user11681.phi.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import org.jetbrains.annotations.NotNull;
import user11681.phi.program.Program;

public class ProgramGrid<T> implements Iterable<T> {
    protected static final int LENGTH = Program.SIZE * Program.SIZE;

    @SuppressWarnings("unchecked")
    protected final T[] array = (T[]) new Object[LENGTH];

    protected final List<T> elements = ObjectArrayList.wrap(this.array, LENGTH);

    public T get(int i) {
        return this.array[i];
    }

    public T get(int x, int y) {
        return this.array[x + Program.SIZE * y];
    }

    public void set(int i, T element) {
        this.array[i] = element;
    }

    public void set(int x, int y, T element) {
        this.array[x + Program.SIZE * y] =  element;
    }

    public void clear() {
        Arrays.fill(this.array, null);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public Point find(Object element) {
        if (element == null) {
            return null;
        }

        int i = this.elements.indexOf(element);

        return i < 0 ? null : new Point(i % Program.SIZE, i / Program.SIZE);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return this.elements.iterator();
    }

    public void forEach(IntConsumer action) {
        for (int i = 0; i < LENGTH; i++) {
            action.accept(i);
        }
    }

    public void forEach(IntBiConsumer action) {
        for (int i = 0; i < LENGTH; i++) {
            action.accept(i % Program.SIZE, i / Program.SIZE);
        }
    }

    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < LENGTH; i++) {
            action.accept(this.get(i));
        }
    }

    public void forEach(ListConsumer<? super T> action) {
        for (int i = 0; i < LENGTH; i++) {
            action.accept(i, this.get(i));
        }
    }

    public void forEach(GridConsumer<? super T> action) {
        for (int i = 0; i < LENGTH; i++) {
            action.accept(i % Program.SIZE, i / Program.SIZE, this.get(i));
        }
    }
}
