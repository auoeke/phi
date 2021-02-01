package user11681.phi.program.element.type.constant;

import net.minecraft.client.util.math.MatrixStack;
import user11681.phi.client.Localization;
import user11681.phi.client.gui.ScreenUtil;
import user11681.phi.program.element.Element;
import user11681.phi.program.transaction.ValueType;
import user11681.phi.program.transaction.Variable;

public class EulerConstant implements ConstantElementType {
    @Override
    public void render(Element element, MatrixStack matrixes, int x, int y) {
        ScreenUtil.textRenderer.drawWithShadow(matrixes, "e", x, y, 0xFFFFFFFF);
    }

    @Override
    public Variable output() {
        return ValueType.number.variable(Localization.numberType);
    }
}
