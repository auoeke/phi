package user11681.phi.program.element.type;

import java.util.List;
import user11681.phi.program.element.Element;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.transaction.Variable;

public interface TransactionElementType<O> extends ElementType {
    Variable output();

    List<Variable> input();

    void process(Element element, Transaction<O> transaction);
}
