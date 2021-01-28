package user11681.phi.program.piece.type.function;

import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.Input;
import user11681.phi.program.piece.type.ElementType;

public interface FunctionElementType extends ElementType {
    TypedActionResult<Text> execute(Element element, Input input);
}
