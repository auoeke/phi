package user11681.phi.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import user11681.phi.Phi;
import user11681.phi.program.piece.Program;

@Environment(EnvType.CLIENT)
public class ProgrammerScreen extends Screen {
    private static final TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();

    private static final Identifier background = Phi.id("textures/gui/programmer.png");
    private static final Identifier frame = Phi.id("textures/gui/frame.png");
    private static final Identifier search = Phi.id("textures/gui/search.png");

    private static final int WIDTH = 176;
    private static final int HEIGHT = 176;

    private static final int SEARCH_WIDTH = 90;
    private static final int SEARCH_HEIGHT = 126;

    private final ProgramSearchFieldWidget searchField = new ProgramSearchFieldWidget(this.textRenderer, 0, 0, 64, 10, LiteralText.EMPTY);

    private Program program;

    private int x = 4;
    private int y = 4;

    private int backgroundX;
    private int backgroundY;

    private int gridX;
    private int gridY;

    private int frameX;
    private int frameY;

    private int searchX;
    private int searchY;

    private boolean searching;

    public ProgrammerScreen(Text title, Program program) {
        super(title);

        this.program = program;
    }

    @Override
    public void init() {
        super.init();

        this.backgroundX = (this.width - WIDTH) / 2;
        this.backgroundY = (this.height - HEIGHT) / 2;

        this.gridX = this.backgroundX + 8;
        this.gridY = this.backgroundY + 8;
    }

    @Override
    public void render(MatrixStack matrixes, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrixes);

        super.render(matrixes, mouseX, mouseY, delta);

        textureManager.bindTexture(background);
        drawTexture(matrixes, this.backgroundX, this.backgroundY, 0, 0, WIDTH, HEIGHT);

        this.frameX = this.gridX + this.x * 18;
        this.frameY = this.gridY + this.y * 18;

        this.renderFrame(matrixes);

        if (this.searching) {
            this.renderSearchWindow(matrixes);
        }
    }

    private void renderFrame(MatrixStack matrixes) {
        textureManager.bindTexture(frame);
        drawTexture(matrixes, this.frameX, this.frameY, 0, 0, 16, 16, 16, 16);
    }

    private void renderSearchWindow(MatrixStack matrixes) {
        RenderSystem.enableBlend();

        textureManager.bindTexture(search);
        drawTexture(matrixes, this.searchX, this.searchY, 0, 0, SEARCH_WIDTH, SEARCH_HEIGHT, 128, 128);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }

        if (this.searching) {

        } else {
            switch (keyCode) {
                case GLFW.GLFW_KEY_LEFT:
                case GLFW.GLFW_KEY_A:
                    if (this.x > 0) {
                        --this.x;
                    }

                    break;

                case GLFW.GLFW_KEY_RIGHT:
                case GLFW.GLFW_KEY_D:
                    if (this.x < Program.X - 1) {
                        ++this.x;
                    }

                    break;

                case GLFW.GLFW_KEY_S:
                case GLFW.GLFW_KEY_DOWN:
                    if (this.y < Program.Y - 1) {
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
                    this.searching = true;

                    this.searchX = Math.min(this.frameX, this.width - SEARCH_WIDTH);
                    this.searchY = Math.min(this.frameY, this.height - SEARCH_HEIGHT);

                    this.searchField.x = this.searchX + (SEARCH_WIDTH - this.searchField.getWidth());
                    this.searchField.y = this.searchY + 5;

                    this.addButton(this.searchField);

                    break;

                default:
                    return false;
            }
        }

        return true;
    }
}
