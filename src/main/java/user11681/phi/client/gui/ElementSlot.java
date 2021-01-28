package user11681.phi.client.gui;

import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import user11681.phi.Phi;
import user11681.phi.client.PhiClient;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.type.ElementType;

@Environment(EnvType.CLIENT)
public class ElementSlot {
    private static final Identifier focusedBorder = Phi.id("textures/gui/focused_slot.png");

    public final int x;
    public final int y;

    public boolean focused;

    public Element element;

    public ElementSlot(ElementType element, int x, int y) {
        this(element.defaultElement(), x, y);
    }

    public ElementSlot(Element element, int x, int y) {
        this.element = element;
        this.x = x;
        this.y = y;
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
            PhiClient.textureManager.bindTexture(focusedBorder);
            DrawableHelper.drawTexture(matrixes, this.x, this.y, 0, 0, 16, 16, 16, 16);
        }
    }
}
