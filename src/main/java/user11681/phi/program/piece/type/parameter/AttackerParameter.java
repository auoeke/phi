package user11681.phi.program.piece.type.parameter;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import user11681.phi.program.ProgramContext;
import user11681.phi.program.piece.type.TexturedElementType;

public class AttackerParameter extends TexturedElementType implements ParameterElementType<Entity> {
    public AttackerParameter(Identifier texture) {
        super(texture);
    }

    @Override
    public Entity get(ProgramContext context) {
        return null;
    }
}
