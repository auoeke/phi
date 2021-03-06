package user11681.phi.program.element.type.operator;

import user11681.phi.program.element.Element;
import user11681.phi.program.element.group.ElementGroup;
import user11681.phi.program.element.type.TransactionElementType;

public interface OperatorElementType<O> extends TransactionElementType<O> {
    @Override
    default ElementGroup group() {
        return ElementGroup.operator;
    }

    @Override
    default int cost(Element element) {
        return 0;
    }
}
