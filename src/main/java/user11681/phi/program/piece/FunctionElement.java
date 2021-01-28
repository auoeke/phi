package user11681.phi.program.piece;

import net.minecraft.nbt.CompoundTag;
import user11681.phi.program.piece.type.ElementType;

public class FunctionElement extends Element {
    public int power;

    public FunctionElement(ElementType type) {
        super(type);
    }

    @Override
    public void toTag(CompoundTag tag) {
        super.toTag(tag);

        tag.putInt("power", this.power);
    }
}
