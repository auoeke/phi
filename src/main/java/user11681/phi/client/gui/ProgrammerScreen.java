package user11681.phi.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import user11681.phi.Phi;
import user11681.phi.client.PhiClient;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.Program;

@Environment(EnvType.CLIENT)
public class ProgrammerScreen extends Screen {
    private static final Identifier background = Phi.id("textures/gui/programmer.png");
    private static final Identifier frame = Phi.id("textures/gui/frame.png");

    private static final int WIDTH = 176;
    private static final int HEIGHT = 176;

    private final Element[][] elements = new Element[9][9];

    private ElementSearchWidget search;
    private Program program;

    private int x = 4;
    private int y = 4;

    private int backgroundX;
    private int backgroundY;

    private int gridX;
    private int gridY;

    private int frameX;
    private int frameY;

    public ProgrammerScreen(Text title, Program program) {
        super(title);

        this.program = program;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void init() {
        super.init();

        this.backgroundX = (this.width - WIDTH) / 2;
        this.backgroundY = (this.height - HEIGHT) / 2;

        this.gridX = this.backgroundX + 8;
        this.gridY = this.backgroundY + 8;

        this.search = new ElementSearchWidget(this.textRenderer, 0, 0, 64, 10, LiteralText.EMPTY);
    }

    @Override
    public void render(MatrixStack matrixes, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrixes);

        PhiClient.textureManager.bindTexture(background);
        drawTexture(matrixes, this.backgroundX, this.backgroundY, 0, 0, WIDTH, HEIGHT);

        this.frameX = this.gridX + this.x * 18;
        this.frameY = this.gridY + this.y * 18;

        this.renderFrame(matrixes);

        super.render(matrixes, mouseX, mouseY, delta);

        if (this.search != null) {
            ElementSlot hovered = this.search.hovered;

            if (hovered != null) {
                this.renderTooltip(matrixes, hovered.element.type.tooltip(), mouseX, mouseY);
            }
        }
    }

    private void renderFrame(MatrixStack matrixes) {
        PhiClient.textureManager.bindTexture(frame);
        drawTexture(matrixes, this.frameX, this.frameY, 0, 0, 16, 16, 16, 16);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return !this.buttons.contains(this.search) && super.shouldCloseOnEsc();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.buttons.contains(this.search)) {
            switch (keyCode) {
                case GLFW.GLFW_KEY_ESCAPE:
                    this.buttons.remove(this.search);

                    return true;

                case GLFW.GLFW_KEY_TAB:
                    if (this.search.keyPressed(keyCode, scanCode, modifiers)) {
                        return true;
                    }
            }
        }

        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }

        if (this.buttons.contains(this.search)) {
            return false;
        }

        switch (keyCode) {
            case GLFW.GLFW_KEY_LEFT:
            case GLFW.GLFW_KEY_A:
                if (this.x > 0) {
                    --this.x;
                }

                break;

            case GLFW.GLFW_KEY_RIGHT:
            case GLFW.GLFW_KEY_D:
                if (this.x < Program.SIZE - 1) {
                    ++this.x;
                }

                break;

            case GLFW.GLFW_KEY_S:
            case GLFW.GLFW_KEY_DOWN:
                if (this.y < Program.SIZE - 1) {
                    ++this.y;
                }

                break;

            case GLFW.GLFW_KEY_UP:
            case GLFW.GLFW_KEY_W:
                if (this.y > 0) {
                    --this.y;
                }

                break;

            case GLFW.GLFW_KEY_ENTER:
                this.search.init(Math.min(this.frameX + 17, this.width - ElementSearchWidget.WIDTH), Math.min(this.frameY - 1, this.height - ElementSearchWidget.HEIGHT));

                this.addButton(this.search);
                this.focusOn(this.search);
                this.search.setSelected(true);

                break;

            default:
                return false;
        }

        return true;
    }
}
