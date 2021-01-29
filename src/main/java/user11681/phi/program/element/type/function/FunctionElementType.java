package user11681.phi.program.element.type.function;

import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.Input;
import user11681.phi.program.element.type.ElementType;

public interface FunctionElementType extends ElementType {
    TypedActionResult<Text> execute(Element element, Input input);

    @Override
    default String namespace() {
        return "function";
    }
}
