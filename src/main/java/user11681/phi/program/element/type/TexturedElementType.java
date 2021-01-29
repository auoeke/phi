package user11681.phi.program.element.type;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import user11681.phi.Phi;
import user11681.phi.client.PhiClient;
import user11681.phi.program.element.Element;

public abstract class TexturedElementType implements ElementType {
    @Environment(EnvType.CLIENT)
    protected static final Identifier elementBase = Phi.id("textures/element/base.png");

    @Environment(EnvType.CLIENT)
    protected Identifier texture;

    public TexturedElementType(Identifier texture) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            this.texture = texture;
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(Element element, MatrixStack matrixes, int x, int y) {
        PhiClient.textureManager.bindTexture(elementBase);
        DrawableHelper.drawTexture(matrixes, x, y, 0, 0, 16, 16, 16, 16);

        PhiClient.textureManager.bindTexture(this.texture);
        DrawableHelper.drawTexture(matrixes, x, y, 0, 0, 16, 16, 16, 16);
    }
}
