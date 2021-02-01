package user11681.phi.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import user11681.phi.Phi;
import user11681.phi.client.gui.ElementSlot;
import user11681.phi.client.gui.ProgrammerScreen;
import user11681.phi.client.gui.ProgrammerScreenAware;
import user11681.phi.client.gui.ScreenUtil;
import user11681.phi.program.element.type.ElementType;

public class ElementSearchWidget extends TextFieldWidget implements ProgrammerScreenAware {
    public static final int WIDTH = 89;
    public static final int HEIGHT = 125;

    private static final int ROW_LENGTH = 4;
    private static final int BORDER_PADDING = 7;
    private static final int PADDING = (WIDTH - 16 * ROW_LENGTH - BORDER_PADDING * 2) / (ROW_LENGTH - 1);

    private static final Identifier search = Phi.id("textures/gui/search.png");

    private final ProgrammerScreen screen;

    public ElementSlot focused;
    public ElementSlot hovered;

    private ReferenceArrayList<ElementSlot> slots = new ReferenceArrayList<>();

    public int backgroundX;
    public int backgroundY;

    public ElementSearchWidget(TextRenderer textRenderer, ProgrammerScreen screen, int x, int y, int width, int height) {
        super(textRenderer, x, y, width, height, LiteralText.EMPTY);

        this.screen = screen;

        this.setHasBorder(false);
    }

    @Override
    public ProgrammerScreen screen() {
        return this.screen;
    }

    @Override
    public void render(MatrixStack matrixes, int mouseX, int mouseY, float delta) {
        RenderSystem.enableBlend();

        ScreenUtil.bindTexture(search);
        drawTexture(matrixes, this.backgroundX, this.backgroundY, 0, 0, WIDTH, HEIGHT, 128, 128);

        super.render(matrixes, mouseX, mouseY, delta);

        this.hovered = null;

        for (ElementSlot slot : this.slots) {
            slot.render(matrixes);

            if (mouseX >= slot.x && mouseX <= slot.x + 16 && mouseY >= slot.y && mouseY <= slot.y + 16) {
                this.hovered = slot;
            }
        }

        if (!Screen.hasControlDown() || this.renderTooltip(this.focused, matrixes)) {
            this.renderTooltip(this.hovered, matrixes, mouseX, mouseY);
        }
    }

    @Override
    public boolean charTyped(char chr, int keyCode) {
        if (super.charTyped(chr, keyCode)) {
            this.computeElements();

            return true;
        }

        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_TAB) {
            if (!this.slots.isEmpty()) {
                int change = (modifiers & GLFW.GLFW_MOD_SHIFT) == 0 ? 1 : this.slots.size() - 1;

                this.focused.focused = false;
                this.focused = this.slots.get((this.slots.indexOf(this.focused) + change) % this.slots.size());
                this.focused.focused = true;
            }

            return true;
        }

        boolean previouslyEmpty = this.getText().isEmpty();

        if (super.keyPressed(keyCode, scanCode, modifiers) && keyCode == GLFW.GLFW_KEY_BACKSPACE) {
            if (!previouslyEmpty && this.getText().isEmpty()) {
                this.computeElements();
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        ElementSlot slot = this.slot(mouseX, mouseY);

        if (slot != null) {
            this.screen.insertElement(slot.element.type);
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    public void init(int backgroundX, int backgroundY) {
        this.backgroundX = backgroundX;
        this.backgroundY = backgroundY;

        this.x = backgroundX + (WIDTH - this.width) / 2;
        this.y = backgroundY + 5;

        this.slots.clear();

        this.computeElements();
    }

    public void deinit() {
        this.hovered = null;
        this.focused = null;

        this.setText("");
    }

    private void computeElements() {
        String searchText = this.getText();
        int order = 0;

        ReferenceArrayList<ElementSlot> newSlots = new ReferenceArrayList<>();

        for (ElementType type : ElementType.registry) {
            if (type.name().getString().contains(searchText)) {
                newSlots.add(new ElementSlot().element(type).position(this.backgroundX + BORDER_PADDING + (16 + PADDING) * (order % ROW_LENGTH), this.backgroundY + 20 + 18 * (order / ROW_LENGTH)));

                ++order;
            }
        }

        if (newSlots.size() != this.slots.size()) {
            int focusedIndex = this.slots.indexOf(this.focused);

            if (focusedIndex >= 0 && focusedIndex == newSlots.indexOf(this.focused)) {
                this.focused = newSlots.get(focusedIndex);
            }

            this.slots = newSlots;

            if (newSlots.isEmpty()) {
                if (this.focused != null) {
                    this.focused.focused = false;
                    this.focused = null;
                }
            } else {
                this.focused = this.slots.get(0);
                this.focused.focused = true;
            }
        }
    }

    private ElementSlot slot(double x, double y) {
        for (ElementSlot slot : this.slots) {
            if (x >= slot.x && x <= slot.x + 16 && y >= slot.y && y <= slot.y + 16) {
                return slot;
            }
        }

        return null;
    }
}
