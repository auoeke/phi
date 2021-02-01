package user11681.phi.client.gui;

import user11681.phi.util.ProgramGrid;

public class ElementGrid extends ProgramGrid<ElementSlot> {
    public ElementGrid() {
        this.forEach((int i) -> this.elements.set(i, new ElementSlot()));
    }

    public ElementSlot slot(double x, double y) {
        for (ElementSlot slot : this) {
            if (ScreenUtil.inside(x, y, slot.x, slot.y, 16, 16)) {
                return slot;
            }
        }

        return null;
    }
}
