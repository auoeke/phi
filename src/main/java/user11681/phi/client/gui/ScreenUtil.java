package user11681.phi.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextHandler;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ScreenUtil {
    public static final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    public static final TextHandler textHandler = MinecraftClient.getInstance().textRenderer.getTextHandler();

    private static final TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();

    public static boolean inside(double x, double y, double startX, double startY, double width, double height) {
        return x >= startX && x <= startX + width && y >= startY && y <= startY + height;
    }

    public static void bindTexture(Identifier texture) {
        textureManager.bindTexture(texture);
    }

    public static void drawTexture(Identifier texture, MatrixStack matrixes, int x, int y, int width, int height) {
        textureManager.bindTexture(texture);

        DrawableHelper.drawTexture(matrixes, x, y, 0, 0, width, height, width, height);
    }

    public static void drawTexture(Identifier texture, MatrixStack matrixes, int x, int y, int width, int height, int textureWidth, int textureHeight) {
        textureManager.bindTexture(texture);

        DrawableHelper.drawTexture(matrixes, x, y, 0, 0, width, height, textureWidth, textureHeight);
    }

    public static void drawTexture(Identifier texture, MatrixStack matrixes, int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight) {
        textureManager.bindTexture(texture);

        DrawableHelper.drawTexture(matrixes, x, y, u, v, width, height, textureWidth, textureHeight);
    }

    public static void drawCenteredText(MatrixStack matrixes, Text text, int x, int y, int color) {
        textRenderer.drawWithShadow(matrixes, text, x - textHandler.getWidth(text) / 2, y - textRenderer.fontHeight / 2F, color);
    }

    public static void drawCenteredText(MatrixStack matrixes, String text, int x, int y, int color) {
        textRenderer.drawWithShadow(matrixes, text, x - textHandler.getWidth(text) / 2, y - textRenderer.fontHeight / 2F, color);
    }
}
