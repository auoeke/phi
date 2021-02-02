package user11681.phi.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Locale;
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
import user11681.phi.util.Util;

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

    private ObjectArrayList<ElementSlot> slots = new ObjectArrayList<>();

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
        matrixes.push();
        matrixes.translate(this.backgroundX, this.backgroundY, 0);

        RenderSystem.enableBlend();

        ScreenUtil.drawTexture(search, matrixes, 0, 0, WIDTH, HEIGHT, 128, 128);

        this.hovered = null;

        for (ElementSlot slot : this.slots) {
            if (this.hovered != null && ScreenUtil.inside(mouseX, mouseY, slot.x, slot.y, 16, 16)) {
                this.hovered = slot;
            }

            slot.render(matrixes);
        }

        if (!Screen.hasControlDown() || this.renderTooltip(this.focused, matrixes)) {
            this.renderTooltip(this.hovered, matrixes, mouseX, mouseY);
        }

        matrixes.pop();

        super.render(matrixes, mouseX, mouseY, delta);
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

                this.focus(this.slots.get((this.slots.indexOf(this.focused) + change) % this.slots.size()));
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
        Util.ifNonnull(this.slot(mouseX, mouseY), (ElementSlot slot) -> this.screen.insertElement(slot.element.type));

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
        String filter = this.getText().toLowerCase(Locale.ROOT);
        int index = 0;

        ObjectArrayList<ElementSlot> newSlots = new ObjectArrayList<>();

        for (ElementType type : ElementType.registry) {
            if (type.name().getString().toLowerCase(Locale.ROOT).contains(filter)) {
                newSlots.add(new ElementSlot().element(type).position(BORDER_PADDING + (16 + PADDING) * (index % ROW_LENGTH), 20 + 18 * (index / ROW_LENGTH)));

                ++index;
            }
        }

        if (newSlots.size() != this.slots.size()) {
            int focusedIndex = this.slots.indexOf(this.focused);

            if (focusedIndex >= 0 && focusedIndex == newSlots.indexOf(this.focused)) {
                this.focus(newSlots.get(focusedIndex));
            } else if (newSlots.isEmpty()) {
                this.focus(null);
            } else {
                this.focus(newSlots.get(0));
            }

            this.slots = newSlots;
        }
    }

    private void focus(ElementSlot slot) {
        Util.ifNonnull(this.focused, () -> this.focused.focused = false);

        this.focused = slot;

        Util.ifNonnull(slot, () -> slot.focused = true);
    }

    private ElementSlot slot(double x, double y) {
        for (ElementSlot slot : this.slots) {
            if (ScreenUtil.inside(x, y, slot.x, slot.y, 16, 16)) {
                return slot;
            }
        }

        return null;
    }
}
