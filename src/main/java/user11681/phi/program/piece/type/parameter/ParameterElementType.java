package user11681.phi.program.piece.type.parameter;

import user11681.phi.program.ProgramContext;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.type.ElementType;

public interface ParameterElementType<R> extends ElementType {
    R get(ProgramContext context);

    @Override
    default int cost(Element element) {
        return 0;
    }
}
