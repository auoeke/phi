package user11681.phi.program.piece.type.parameter;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import user11681.phi.program.ProgramContext;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.SelectorElement;
import user11681.phi.program.piece.type.TexturedElementType;

public class ExecutorElementType extends TexturedElementType implements ParameterElementType<Entity> {
    public ExecutorElementType(Identifier texture) {
        super(texture);
    }

    @Override
    public Element fromTag(CompoundTag tag) {
        return new SelectorElement(this);
    }

    @Override
    public Entity get(ProgramContext context) {
        return context.executor;
    }
}
