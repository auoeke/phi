package user11681.phi.program.element.type.constant;

import user11681.phi.program.element.Element;
import user11681.phi.program.element.group.ElementGroup;
import user11681.phi.program.element.type.SupplierElementType;

public interface ConstantElementType extends SupplierElementType {
    @Override
    default ElementGroup group() {
        return ElementGroup.constant;
    }

    @Override
    default int cost(Element element) {
        return 0;
    }
}
