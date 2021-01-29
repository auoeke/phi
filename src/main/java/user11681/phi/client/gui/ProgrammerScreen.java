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
import user11681.phi.program.Program;

@Environment(EnvType.CLIENT)
public class ProgrammerScreen extends Screen {
    private static final Identifier background = Phi.id("textures/gui/programmer.png");
    private static final Identifier focusFrame = Phi.id("textures/gui/focus_frame.png");
    private static final Identifier hoverFrame = Phi.id("textures/gui/hover_frame.png");

    private static final int WIDTH = 176;
    private static final int HEIGHT = 176;

    private final ElementGrid slots = new ElementGrid();

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

        this.slots.forEach((int x, int y, ElementSlot slot) -> slot.element = this.program.elements.get(x, y));
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

        this.computeFrame();

        this.search = new ElementSearchWidget(this.textRenderer, 0, 0, 64, 10, LiteralText.EMPTY);

        this.slots.forEach((int x, int y, ElementSlot slot) -> {
            slot.x = this.backgroundX + 8 + 18 * x;
            slot.y = this.backgroundY + 8 + 18 * y;
        });
    }

    @Override
    public void render(MatrixStack matrixes, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrixes);

        PhiClient.textureManager.bindTexture(background);
        drawTexture(matrixes, this.backgroundX, this.backgroundY, 0, 0, WIDTH, HEIGHT);

        for (ElementSlot element : this.slots) {
            element.render(matrixes);
        }

        ElementSlot hovered = this.slot(mouseX, mouseY);

        this.renderFrame(matrixes, hovered);

        super.render(matrixes, mouseX, mouseY, delta);

        if (this.buttons.contains(this.search)) {
            if (!hasControlDown() || this.renderTooltip(this.search.focused, matrixes)) {
                this.renderTooltip(this.search.hovered, matrixes, mouseX, mouseY);
            }
        } else {
            if (!hasControlDown() || this.renderTooltip(this.currentSlot(), matrixes)) {
                this.renderTooltip(hovered, matrixes, mouseX, mouseY);
            }
        }
    }

    private void computeFrame() {
        this.frameX = this.gridX + this.x * 18;
        this.frameY = this.gridY + this.y * 18;
    }

    private ElementSlot slot(double x, double y) {
        for (ElementSlot slot : this.slots) {
            if (x >= slot.x && x <= slot.x + 16 && y >= slot.y && y <= slot.y + 16) {
                return slot;
            }
        }

        return null;
    }

    private ElementSlot currentSlot() {
        return this.slots.get(this.x, this.y);
    }

    private void renderFrame(MatrixStack matrixes, ElementSlot hovered) {
        if (hovered != null) {
            PhiClient.textureManager.bindTexture(hoverFrame);
            drawTexture(matrixes, hovered.x, hovered.y, 0, 0, 16, 16, 16, 16);
        }

        PhiClient.textureManager.bindTexture(focusFrame);
        drawTexture(matrixes, this.frameX, this.frameY, 0, 0, 16, 16, 16, 16);
    }

    private boolean renderTooltip(ElementSlot slot, MatrixStack matrixes) {
        if (slot != null && slot.element != null) {
            this.renderTooltip(matrixes, slot.element.type.tooltip(), slot.x, slot.y + 24);

            return false;
        }

        return true;
    }

    private void renderTooltip(ElementSlot slot, MatrixStack matrixes, int x, int y) {
        if (slot != null && slot.element != null) {
            this.renderTooltip(matrixes, slot.element.type.tooltip(), x, y);
        }
    }

    private void search(boolean active) {
        if (active) {
            this.search.init(Math.min(this.frameX + 17, this.width - ElementSearchWidget.WIDTH), Math.min(this.frameY - 1, this.height - ElementSearchWidget.HEIGHT));

            this.addButton(this.search);
            this.focusOn(this.search);
            this.search.setSelected(true);
        } else {
            this.search.deinit();
            this.buttons.remove(this.search);
            this.children.remove(this.search);

            if (this.getFocused() == this.search) {
                this.setFocused(null);
            }
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.buttons.contains(this.search)) {
            switch (keyCode) {
                case GLFW.GLFW_KEY_ESCAPE:
                    this.search(false);

                    return true;

                case GLFW.GLFW_KEY_ENTER:
                    if (this.search.focused == null) {
                        break;
                    }

                    this.currentSlot().element = this.search.focused.element.type.defaultElement();

                    this.search(false);

                    return true;

                case GLFW.GLFW_KEY_TAB:
                    if (this.search.keyPressed(keyCode, scanCode, modifiers)) {
                        return true;
                    }
            }
        } else {
            if (keyCode == GLFW.GLFW_KEY_DELETE) {
                this.currentSlot().element = null;

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
            case GLFW.GLFW_KEY_RIGHT:
            case GLFW.GLFW_KEY_D:
                this.x = (this.x + 1) % Program.SIZE;

                break;

            case GLFW.GLFW_KEY_LEFT:
            case GLFW.GLFW_KEY_A:
                this.x = (this.x + Program.SIZE - 1) % Program.SIZE;

                break;

            case GLFW.GLFW_KEY_DOWN:
            case GLFW.GLFW_KEY_S:
                this.y = (this.y + 1) % Program.SIZE;

                break;

            case GLFW.GLFW_KEY_UP:
            case GLFW.GLFW_KEY_W:
                this.y = (this.y + Program.SIZE - 1) % Program.SIZE;

                break;

            case GLFW.GLFW_KEY_ENTER:
                this.search(true);

                break;

            default:
                return false;
        }

        this.computeFrame();

        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Point point = this.slots.find(this.slot(mouseX, mouseY));

        if (point != null) {
            this.x = point.x;
            this.y = point.y;

            this.computeFrame();

            if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                this.search(true);
            }

            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
