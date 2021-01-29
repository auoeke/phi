package user11681.phi.program.element.type.operator;

import java.util.Collection;
import java.util.Collections;
import net.minecraft.util.Identifier;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.transaction.Variable;

public class MultiplyOperator extends TexturedElementType implements OperatorElementType<Double> {
    public MultiplyOperator(Identifier texture) {
        super(texture);
    }

    @Override
    public Variable output() {
        return Variable.number;
    }

    @Override
    public Collection<Variable> input() {
        return Collections.singleton(Variable.number);
    }

    @Override
    public void process(Element element, Transaction<Double> transaction) {

    }
}
