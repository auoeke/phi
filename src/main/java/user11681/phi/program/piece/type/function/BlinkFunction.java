package user11681.phi.program.piece.type.function;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.Input;
import user11681.phi.program.piece.type.TexturedElementType;

public class BlinkFunction extends TexturedElementType implements FunctionElementType {
    public BlinkFunction(Identifier texture) {
        super(texture);
    }

    @Override
    public TypedActionResult<Text> execute(Element element, Input input) {
        return null;
    }

    @Override
    public int cost(Element element) {
        return 200 * element.tag.getInt("power");
    }
}
