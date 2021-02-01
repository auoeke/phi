package user11681.phi.program.element.type.constant;

import net.minecraft.client.util.math.MatrixStack;
import user11681.phi.client.gui.ScreenUtil;
import user11681.phi.program.element.Element;
import user11681.phi.program.type.ValueType;
import user11681.phi.program.type.Variable;

public class TauConstant implements ConstantElementType {
    @Override
    public void render(Element element, MatrixStack matrixes, int x, int y) {
        ScreenUtil.textRenderer.drawWithShadow(matrixes, "\u03c4", x, y, 0xFFFFFFFF);
    }

    @Override
    public Variable output() {
        return Variable.variable(ValueType.number);
    }
}
