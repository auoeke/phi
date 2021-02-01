package user11681.phi.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import user11681.phi.util.ProgramGrid;

@Environment(EnvType.CLIENT)
public class ElementGrid extends ProgramGrid<ElementSlot> {
    public ElementGrid() {
        this.forEach((int i) -> this.elements.set(i, new ElementSlot()));
    }

    @Override
    public void clear() {
        this.forEach((ElementSlot slot) -> slot.element = null);
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
