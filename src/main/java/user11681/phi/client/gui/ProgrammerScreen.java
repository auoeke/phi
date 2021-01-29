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
import user11681.phi.program.piece.Program;

@Environment(EnvType.CLIENT)
public class ProgrammerScreen extends Screen {
    private static final Identifier background = Phi.id("textures/gui/programmer.png");
    private static final Identifier frame = Phi.id("textures/gui/frame.png");

    private static final int WIDTH = 176;
    private static final int HEIGHT = 176;

    private final ElementSlot[][] elements = new ElementSlot[Program.SIZE][Program.SIZE];

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

        for (int i = 0, length = this.elements.length; i < length; i++) {
            for (int j = 0; j < this.elements[i].length; j++) {
                this.elements[i][j] = new ElementSlot(this.program.get(i, j), this.backgroundX + 8 + 18 * i, this.backgroundY + 8 + 18 * j);
            }
        }
    }

    @Override
    public void render(MatrixStack matrixes, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrixes);

        PhiClient.textureManager.bindTexture(background);
        drawTexture(matrixes, this.backgroundX, this.backgroundY, 0, 0, WIDTH, HEIGHT);

        this.frameX = this.gridX + this.x * 18;
        this.frameY = this.gridY + this.y * 18;

        for (ElementSlot[] row : this.elements) {
            for (ElementSlot element : row) {
                element.render(matrixes);
            }
        }

        this.renderFrame(matrixes);

        super.render(matrixes, mouseX, mouseY, delta);

        if (this.search != null) {
            ElementSlot selected = this.search.focused;

            if (selected != null && hasControlDown()) {
                this.renderTooltip(matrixes, selected.element.type.tooltip(), selected.x, selected.y + 24);
            } else {
                selected = this.search.hovered;

                if (selected != null) {
                    this.renderTooltip(matrixes, selected.element.type.tooltip(), mouseX, mouseY);
                }
            }
        }
    }

    private void renderFrame(MatrixStack matrixes) {
        PhiClient.textureManager.bindTexture(frame);
        drawTexture(matrixes, this.frameX, this.frameY, 0, 0, 16, 16, 16, 16);
    }

    private void removeSearchWidget() {
        this.search.deinit();
        this.buttons.remove(this.search);
        this.children.remove(this.search);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.buttons.contains(this.search)) {
            switch (keyCode) {
                case GLFW.GLFW_KEY_ESCAPE:
                    this.removeSearchWidget();

                    return true;

                case GLFW.GLFW_KEY_ENTER:
                    if (this.search.focused == null) {
                        break;
                    }

                    this.elements[this.x][this.y].element = this.search.focused.element.type.defaultElement();

                    this.removeSearchWidget();

                    break;

                case GLFW.GLFW_KEY_TAB:
                    if (this.search.keyPressed(keyCode, scanCode, modifiers)) {
                        return true;
                    }
            }
        } else {
            if (keyCode == GLFW.GLFW_KEY_DELETE) {
                this.elements[this.x][this.y].element = null;

                return true;
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
                this.x = (this.x + Program.SIZE - 1) % Program.SIZE;

                break;

            case GLFW.GLFW_KEY_RIGHT:
            case GLFW.GLFW_KEY_D:
                this.x = (this.x + 1) % Program.SIZE;

                break;

            case GLFW.GLFW_KEY_S:
            case GLFW.GLFW_KEY_DOWN:
                this.y = (this.y + 1) % Program.SIZE;

                break;

            case GLFW.GLFW_KEY_UP:
            case GLFW.GLFW_KEY_W:
                this.y = (this.y + Program.SIZE - 1) % Program.SIZE;

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
