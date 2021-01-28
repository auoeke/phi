package user11681.phi.client.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class ProgramSearchFieldWidget extends TextFieldWidget {
    public ProgramSearchFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text text) {
        super(textRenderer, x, y, width, height, text);

        this.setHasBorder(false);
    }
}
