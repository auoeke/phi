package user11681.phi.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntConsumer;
import org.jetbrains.annotations.NotNull;
import user11681.phi.client.gui.Point;
import user11681.phi.program.Program;

@SuppressWarnings("unchecked")
public class ProgramGrid<T> implements Iterable<T> {
    protected static final int LENGTH = Program.SIZE * Program.SIZE;

    protected final List<T> elements = (List<T>) ObjectArrayList.wrap(new Object[LENGTH], LENGTH);

    public T get(int i) {
        return this.elements.get(i);
    }

    public T get(int x, int y) {
        return this.elements.get(x + Program.SIZE * y);
    }

    public void set(int i, T element) {
        this.elements.set(i, element);
    }

    public void set(int x, int y, T element) {
        this.elements.set(x + Program.SIZE * y, element);
    }

    public Point find(T element) {
        if (element == null) {
            return null;
        }

        int i = this.elements.indexOf(element);

        return i < 0 ? null : new Point(i % Program.SIZE, i / Program.SIZE);
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

    public void forEach(ListConsumer<T> action) {
        for (int i = 0; i < LENGTH; i++) {
            action.accept(i, this.get(i));
        }
    }

    public void forEach(GridConsumer<T> action) {
        for (int i = 0; i < LENGTH; i++) {
            action.accept(i % Program.SIZE, i / Program.SIZE, this.get(i));
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return this.elements.iterator();
    }
}
