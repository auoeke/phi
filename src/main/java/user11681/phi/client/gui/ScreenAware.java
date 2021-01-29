package user11681.phi.client.gui;

import java.util.List;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public interface ScreenAware<T extends Screen> {
    T screen();

    default void renderTooltip(MatrixStack matrixes, Text text, int x, int y) {
        this.screen().renderTooltip(matrixes, text, x, y);
    }

    default void renderTooltip(MatrixStack matrixes, List<Text> text, int x, int y) {
        this.screen().renderTooltip(matrixes, text, x, y);
    }
}
