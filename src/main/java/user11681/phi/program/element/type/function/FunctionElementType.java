package user11681.phi.program.element.type.function;

import org.jetbrains.annotations.Nullable;
import user11681.phi.program.element.type.TransactionElementType;
import user11681.phi.program.transaction.Variable;

public interface FunctionElementType<O> extends TransactionElementType<O> {
    @Nullable
    @Override
    Variable output();

    @Override
    default String namespace() {
        return "function";
    }
}
