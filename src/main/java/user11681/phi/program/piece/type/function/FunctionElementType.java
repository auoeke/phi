package user11681.phi.program.piece.type.function;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import user11681.phi.program.piece.FunctionElement;
import user11681.phi.program.piece.Input;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.type.ElementType;

public interface FunctionElementType extends ElementType {
    TypedActionResult<Text> execute(FunctionElement piece, Input input);

    int cost(FunctionElement piece);

    @Override
    default int cost(Element element) {
        return this.cost((FunctionElement) element);
    }

    @Override
    default Element fromTag(CompoundTag tag) {
        FunctionElement piece = new FunctionElement(this);

        piece.power = tag.getInt("power");

        return piece;
    }
}
