package user11681.phi.client.gui;

import user11681.phi.util.ProgramGrid;

public class ElementGrid extends ProgramGrid<ElementSlot> {
    public ElementGrid() {
        for (int i = 0; i < LENGTH; i++) {
            this.elements.set(i, new ElementSlot());
        }
    }
}
