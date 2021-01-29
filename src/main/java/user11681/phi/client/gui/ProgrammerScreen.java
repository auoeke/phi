package user11681.phi.client.gui;

import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3f;
import org.lwjgl.glfw.GLFW;
import user11681.phi.Phi;
import user11681.phi.network.server.InsertElementPacket;
import user11681.phi.program.Program;
import user11681.phi.program.element.type.ElementType;
import user11681.phi.program.element.type.TransactionElementType;
import user11681.phi.program.transaction.Variable;
import user11681.phi.util.Point;

@Environment(EnvType.CLIENT)
public class ProgrammerScreen extends Screen {
    private static final Identifier background = Phi.id("textures/gui/programmer.png");
    private static final Identifier sidebar = Phi.id("textures/gui/programmer_sidebar.png");
    private static final Identifier focusFrame = Phi.id("textures/gui/focus_frame.png");
    private static final Identifier hoverFrame = Phi.id("textures/gui/hover_frame.png");
    private static final Identifier arrow = Phi.id("textures/gui/arrow.png");
    private static final Identifier arrows = Phi.id("textures/gui/arrows.png");

    private static final int WIDTH = 176;
    private static final int HEIGHT = 176;

    private static final int SIDEBAR_WIDTH = 80;
    private static final int SIDEBAR_HEIGHT = 126;

    private final ReferenceArrayList<ElementGrid> gridStates = new ReferenceArrayList<>();
    private final ElementGrid slots = new ElementGrid();
    private final BlockPos position;

    private ElementSearchWidget search;

    private int x = 4;
    private int y = 4;

    private int backgroundX;
    private int backgroundY;

    private int gridX;
    private int gridY;

    private int frameX;
    private int frameY;

    public ProgrammerScreen(Text title, BlockPos position, Program program) {
        super(title);

        this.position = position;

        this.slots.forEach((int x, int y, ElementSlot slot) -> slot.element = program.elements.get(x, y));
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

        this.search = new ElementSearchWidget(this.textRenderer, this, 0, 0, 64, 10);

        this.slots.forEach((int x, int y, ElementSlot slot) -> {
            slot.x = this.backgroundX + 8 + 18 * x;
            slot.y = this.backgroundY + 8 + 18 * y;
        });
    }

    @Override
    public void render(MatrixStack matrixes, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrixes);

        ElementSlot focused = this.focusedSlot();

        if (focused.element != null && focused.element.type instanceof TransactionElementType) {
            this.renderTransactionSidebar(matrixes);
        }

        ScreenUtil.bindTexture(background);
        drawTexture(matrixes, this.backgroundX, this.backgroundY, 0, 0, WIDTH, HEIGHT);

        for (ElementSlot element : this.slots) {
            element.render(matrixes);
        }

        ElementSlot hovered = this.slots.slot(mouseX, mouseY);

        this.renderFrame(matrixes, hovered);

        super.render(matrixes, mouseX, mouseY, delta);

        if (!this.search() && (!hasControlDown() || this.renderTooltip(focused, matrixes))) {
            this.renderTooltip(hovered, matrixes, mouseX, mouseY);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.search()) {
            switch (keyCode) {
                case GLFW.GLFW_KEY_ESCAPE:
                    this.search(false);

                    return true;

                case GLFW.GLFW_KEY_ENTER:
                    if (this.search.focused == null) {
                        break;
                    }

                    this.insertElement(this.search.focused.element.type);

                    return true;

                case GLFW.GLFW_KEY_TAB:
                    this.search.keyPressed(keyCode, scanCode, modifiers);

                    return true;
            }
        }

        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }

        if (this.search()) {
            return false;
        }

        if (keyCode == GLFW.GLFW_KEY_DELETE) {
            if (Screen.hasControlDown() && Screen.hasShiftDown()) {
                this.slots.forEach((ElementSlot slot) -> slot.element = null);
            } else {
                this.focusedSlot().element = null;
            }

            return true;
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
        if (button != GLFW.GLFW_MOUSE_BUTTON_RIGHT && this.search() && ScreenUtil.inside(mouseX, mouseY, this.search.backgroundX, this.search.backgroundY, ElementSearchWidget.WIDTH, ElementSearchWidget.HEIGHT)) {
            return this.search.mouseClicked(mouseX, mouseY, button);
        }

        this.search(false);

        Point point = this.slots.find(this.slots.slot(mouseX, mouseY));

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

    private boolean search() {
        return this.buttons.contains(this.search);
    }

    private ElementSlot focusedSlot() {
        return this.slots.get(this.x, this.y);
    }

    private void computeFrame() {
        this.frameX = this.gridX + this.x * 18;
        this.frameY = this.gridY + this.y * 18;
    }

    private void renderFrame(MatrixStack matrixes, ElementSlot hovered) {
        ScreenUtil.bindTexture(focusFrame);
        drawTexture(matrixes, this.frameX, this.frameY, 0, 0, 16, 16, 16, 16);

        if (hovered != null) {
            ScreenUtil.bindTexture(hoverFrame);

            if (this.frameX == hovered.x && this.frameY == hovered.y) {
                drawTexture(matrixes, hovered.x, hovered.y, 0, 0, 8, 8, 16, 16);
                drawTexture(matrixes, hovered.x + 8, hovered.y + 8, 8, 8, 8, 8, 16, 16);
            } else {
                drawTexture(matrixes, hovered.x, hovered.y, 0, 0, 16, 16, 16, 16);
            }
        }
    }

    private void renderTransactionSidebar(MatrixStack matrixes) {
        ScreenUtil.bindTexture(sidebar);

        matrixes.push();
        matrixes.translate(this.backgroundX - SIDEBAR_WIDTH, this.backgroundY + 25, 0);

        drawTexture(matrixes, 0, 0, 0, 0, SIDEBAR_WIDTH, SIDEBAR_HEIGHT, 256, 256);

        ElementSlot slot = this.focusedSlot();
        TransactionElementType<?> type = (TransactionElementType<?>) slot.element.type;

        List<Variable> inputs = type.input();

        for (int i = 0; i < inputs.size(); i++) {
            int arrowX = SIDEBAR_WIDTH - 26;
            int arrowY = 7 + 26 * i;

            ScreenUtil.bindTexture(arrows);
            drawTexture(matrixes, arrowX, arrowY, 0, 0, 22, 22, 22, 22);

            this.textRenderer.drawWithShadow(matrixes, inputs.get(i).name, 5, arrowY + 5, -1);

            ScreenUtil.bindTexture(arrow);

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

    private void renderArrows(MatrixStack matrixes, int x, int y) {

    }

    public boolean renderTooltip(ElementSlot slot, MatrixStack matrixes) {
        if (slot != null && slot.element != null) {
            this.renderTooltip(matrixes, slot.element.type.tooltip(), slot.x + 8, slot.y + 8);

            return false;
        }

        return true;
    }

    public void renderTooltip(ElementSlot slot, MatrixStack matrixes, int x, int y) {
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

    public void insertElement(ElementType type) {
        this.search(false);

        InsertElementPacket.instance.send(PacketByteBufs.create().writeBlockPos(this.position).writeVarInt(this.x + Program.SIZE * this.y).writeIdentifier(type.id()));

        this.focusedSlot().element = type.defaultElement();
    }
}
