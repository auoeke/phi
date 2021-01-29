package user11681.phi.client.gui;

import user11681.phi.util.ProgramGrid;

public class ElementGrid extends ProgramGrid<ElementSlot> {
    public ElementGrid() {
        for (int i = 0; i < LENGTH; i++) {
            this.elements.set(i, new ElementSlot());
        }
    }

    public ElementSlot slot(double x, double y) {
        for (ElementSlot slot : this) {
            if (x >= slot.x && x <= slot.x + 16 && y >= slot.y && y <= slot.y + 16) {
                return slot;
            }
        }

        return null;
    }
}
