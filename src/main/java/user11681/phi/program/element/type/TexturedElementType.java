package user11681.phi.program.element.type;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import user11681.phi.client.gui.ScreenUtil;
import user11681.phi.program.element.Element;

public abstract class TexturedElementType implements ElementType {
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
        ElementType.super.render(element, matrixes, x, y);

        ScreenUtil.drawTexture(this.texture, matrixes, x, y, 16, 16);
    }
}
