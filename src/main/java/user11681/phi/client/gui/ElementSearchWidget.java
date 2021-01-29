package user11681.phi.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import user11681.phi.Phi;
import user11681.phi.client.PhiClient;
import user11681.phi.program.element.type.ElementType;

public class ElementSearchWidget extends TextFieldWidget {
    public static final int WIDTH = 89;
    public static final int HEIGHT = 125;

    private static final int ROW_LENGTH = 4;
    private static final int BORDER_PADDING = 7;
    private static final int PADDING = (WIDTH - 16 * ROW_LENGTH - BORDER_PADDING * 2) / (ROW_LENGTH - 1);

    private static final Identifier search = Phi.id("textures/gui/search.png");

    public ElementSlot hovered;
    public ElementSlot focused;

    private ReferenceArrayList<ElementSlot> slots = new ReferenceArrayList<>();

    public int backgroundX;
    public int backgroundY;

    public ElementSearchWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text text) {
        super(textRenderer, x, y, width, height, text);

        this.setHasBorder(false);
    }

    @Override
    public void render(MatrixStack matrixes, int mouseX, int mouseY, float delta) {
        RenderSystem.enableBlend();

        PhiClient.textureManager.bindTexture(search);
        drawTexture(matrixes, this.backgroundX, this.backgroundY, 0, 0, WIDTH, HEIGHT, 128, 128);

        super.render(matrixes, mouseX, mouseY, delta);

        this.hovered = null;

        for (ElementSlot slot : this.slots) {
            slot.render(matrixes);

            if (mouseX >= slot.x && mouseX <= slot.x + 16 && mouseY >= slot.y && mouseY <= slot.y + 16) {
                this.hovered = slot;
            }
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
                newSlots.add(new ElementSlot().element(type).position(this.backgroundX + BORDER_PADDING + (16 + PADDING) * order, this.backgroundY + 10 * (2 + order / ROW_LENGTH)));

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
}
