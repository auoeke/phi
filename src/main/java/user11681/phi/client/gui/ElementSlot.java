package user11681.phi.client.gui;

import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.ElementType;

@Environment(EnvType.CLIENT)
public class ElementSlot {
    public Element element;

    public int x;
    public int y;

    public boolean focused;

    public ElementSlot element(ElementType element) {
        this.element = element.defaultElement();

        return this;
    }

    public ElementSlot element(Element element) {
        this.element = element;

        return this;
    }

    public ElementSlot position(int x, int y) {
        this.x = x;
        this.y = y;

        return this;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that instanceof ElementSlot) {
            ElementSlot slot = (ElementSlot) that;

            return this.x == slot.x && this.y == slot.y && Objects.equals(this.element, slot.element);
        }

        return false;
    }

    public void render(MatrixStack matrixes) {
        if (this.element != null) {
            this.element.type.render(this.element, matrixes, this.x, this.y);
        }

        if (this.focused) {
            ScreenUtil.drawTexture(Textures.focusedBorder, matrixes, this.x, this.y, 16, 16);
        }
    }
}
