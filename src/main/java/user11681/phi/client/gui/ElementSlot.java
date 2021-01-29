package user11681.phi.client.gui;

import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import user11681.phi.Phi;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.ElementType;

@Environment(EnvType.CLIENT)
public class ElementSlot {
    private static final Identifier focusedBorder = Phi.id("textures/gui/focused_slot.png");

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
            ScreenUtil.bindTexture(focusedBorder);
            DrawableHelper.drawTexture(matrixes, this.x, this.y, 0, 0, 16, 16, 16, 16);
        }
    }
}
