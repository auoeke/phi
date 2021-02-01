package user11681.phi.program.element.type.constant;

import net.minecraft.client.util.math.MatrixStack;
import user11681.phi.client.gui.ScreenUtil;
import user11681.phi.program.element.Element;
import user11681.phi.program.type.ValueType;
import user11681.phi.program.type.Variable;

public class PiConstant implements ConstantElementType {
    @Override
    public void render(Element element, MatrixStack matrixes, int x, int y) {
        ConstantElementType.super.render(element, matrixes, x, y);

        ScreenUtil.drawCenteredText(matrixes, "π", x + 8, y + 8, 0xFFFFFFFF);
    }

    @Override
    public Variable output() {
        return Variable.variable(ValueType.number);
    }
}
