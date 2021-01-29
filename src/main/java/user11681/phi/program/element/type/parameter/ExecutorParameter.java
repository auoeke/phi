package user11681.phi.program.element.type.parameter;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import user11681.phi.program.ProgramContext;
import user11681.phi.program.element.type.TexturedElementType;

public class ExecutorParameter extends TexturedElementType implements ParameterElementType<Entity> {
    public ExecutorParameter(Identifier texture) {
        super(texture);
    }

    @Override
    public Entity get(ProgramContext context) {
        return context.executor;
    }
}
