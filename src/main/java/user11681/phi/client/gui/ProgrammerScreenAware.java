package user11681.phi.client.gui;

import net.minecraft.client.util.math.MatrixStack;

public interface ProgrammerScreenAware extends ScreenAware<ProgrammerScreen>{
    default boolean renderTooltip(ElementSlot slot, MatrixStack matrixes) {
        return this.screen().renderTooltip(slot, matrixes);
    }

    default void renderTooltip(ElementSlot slot, MatrixStack matrixes, int x, int y) {
        this.screen().renderTooltip(slot, matrixes, x, y);
    }
}
