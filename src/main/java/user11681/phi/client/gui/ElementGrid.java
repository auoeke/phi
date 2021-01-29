package user11681.phi.client.gui;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import user11681.phi.program.piece.Program;

public class ElementGrid implements Iterable<ElementSlot> {
    private static final int LENGTH = Program.SIZE * Program.SIZE;

    private final List<ElementSlot> slots = ObjectArrayList.wrap(new ElementSlot[LENGTH], LENGTH);

    public ElementGrid() {
        for (int i = 0; i < LENGTH; i++) {
            this.slots.set(i, new ElementSlot());
        }
    }

    public ElementSlot get(int i) {
        return this.slots.get(i);
    }

    public ElementSlot get(int x, int y) {
        return this.slots.get(x + 9 * y);
    }

    public void set(int i, ElementSlot slot) {
        this.slots.set(i, slot);
    }

    public void set(int x, int y, ElementSlot slot) {
        this.slots.set(x + 9 * y, slot);
    }

    public Point find(ElementSlot slot) {
        int i = this.slots.indexOf(slot);

        return i < 0 ? null : new Point(i % Program.SIZE, i / Program.SIZE);
    }

    public void forEach(SlotConsumer action) {
        for (int i = 0; i < LENGTH; i++) {
            action.accept(i % Program.SIZE, i / Program.SIZE, this.get(i));
        }
    }

    @NotNull
    @Override
    public Iterator<ElementSlot> iterator() {
        return this.slots.iterator();
    }

}
