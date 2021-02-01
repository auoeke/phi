package user11681.phi.client.gui.widget;

import java.util.Collections;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import user11681.phi.client.gui.ProgrammerScreen;
import user11681.phi.client.gui.ProgrammerScreenAware;
import user11681.phi.client.gui.ScreenUtil;
import user11681.phi.client.gui.Textures;
import user11681.phi.program.element.type.TransactionElementType;
import user11681.phi.program.type.NamedVariable;

public class TransactionSidebar extends AbstractParentElement implements Drawable, ProgrammerScreenAware {
    public static final int SIDEBAR_WIDTH = 80;
    public static final int SIDEBAR_HEIGHT = 126;

    private static final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

    private final ProgrammerScreen screen;
    private final int x;
    private final int y;

    public TransactionSidebar(ProgrammerScreen screen, int x, int y) {
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }

    @Override
    public ProgrammerScreen screen() {
        return this.screen;
    }

    @Override
    public void render(MatrixStack matrixes, int mouseX, int mouseY, float delta) {
        matrixes.push();
        matrixes.translate(this.x, this.y, 0);

        ScreenUtil.drawTexture(Textures.sidebar, matrixes, 0, 0, SIDEBAR_WIDTH, SIDEBAR_HEIGHT, 256, 256);

        TransactionElementType<?> type = (TransactionElementType<?>) this.screen.focusedSlot().element.type;

        List<NamedVariable> inputs = type.input();

        for (int i = 0; i < inputs.size(); i++) {
            int arrowX = SIDEBAR_WIDTH - 26;
            int arrowY = 7 + 26 * i;

            ScreenUtil.drawTexture(Textures.arrows, matrixes, arrowX, arrowY, 22, 22);

            textRenderer.drawWithShadow(matrixes, inputs.get(i).name, 5, arrowY + 5, -1);

            ScreenUtil.bindTexture(Textures.arrow);

            for (int j = 0; j != 4; j++) {
                matrixes.push();
                matrixes.translate(arrowX + 3, arrowY + 3, 0);
                matrixes.multiply(Vec3f.POSITIVE_Z.getRadialQuaternion((float) (Math.PI / 2 * j)));

                drawTexture(matrixes, -16 * (j / 2), -16 * ((j + 1 & 2) >> 1), 0, 0, 16, 16, 16, 16);

                matrixes.pop();
            }
        }

        matrixes.pop();
    }
}
