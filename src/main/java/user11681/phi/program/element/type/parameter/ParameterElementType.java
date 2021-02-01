package user11681.phi.program.element.type.parameter;

import user11681.phi.program.ProgramContext;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.group.ElementGroup;
import user11681.phi.program.element.type.ElementType;

public interface ParameterElementType<R> extends ElementType {
    R get(ProgramContext context);

    @Override
    default int cost(Element element) {
        return 0;
    }

    @Override
    default ElementGroup group() {
        return ElementGroup.parameter;
    }
}
