package user11681.phi.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ScreenUtil {
    private static final TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();

    public static boolean inside(double x, double y, double startX, double startY, double width, double height) {
        return x >= startX && x <= startX + width && y >= startY && y <= startY + height;
    }

    public static void bindTexture(Identifier texture) {
        textureManager.bindTexture(texture);
    }
}
