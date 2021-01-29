package user11681.phi.program.element.type.function;

import user11681.phi.program.element.type.TransactionElementType;

public interface FunctionElementType<O> extends TransactionElementType<O> {
    @Override
    default String namespace() {
        return "function";
    }
}
