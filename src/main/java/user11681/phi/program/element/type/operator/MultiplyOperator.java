package user11681.phi.program.element.type.operator;

import java.util.Collections;
import java.util.List;
import net.minecraft.util.Identifier;
import user11681.phi.client.Localization;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.transaction.ValueType;
import user11681.phi.program.transaction.Variable;

public class MultiplyOperator extends TexturedElementType implements OperatorElementType<Double> {
    public MultiplyOperator(Identifier texture) {
        super(texture);
    }

    @Override
    public Variable output() {
        return ValueType.number.variable(Localization.productVariable);
    }

    @Override
    public List<Variable> input() {
        return Collections.singletonList(ValueType.number.variable(Localization.operandVariable));
    }

    @Override
    public void process(Element element, Transaction<Double> transaction) {

    }
}
