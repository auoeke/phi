package user11681.phi.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.TextureManager;

@Environment(EnvType.CLIENT)
public class PhiClient {
    public static final TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();
}
