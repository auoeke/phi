package user11681.phi.program.element.type.constant;

import net.minecraft.client.util.math.MatrixStack;
import user11681.phi.client.gui.ScreenUtil;
import user11681.phi.program.element.Element;
import user11681.phi.program.transaction.ValueType;
import user11681.phi.program.transaction.Variable;

public class NumberConstant implements ConstantElementType {
    @Override
    public void render(Element element, MatrixStack matrixes, int x, int y) {
        ScreenUtil.textRenderer.drawWithShadow(matrixes, String.valueOf(element.tag.getInt("number")), x, y, 0xFFFFFFFF);
    }

    @Override
    public Variable output() {
        return Variable.variable(ValueType.number);
    }
}