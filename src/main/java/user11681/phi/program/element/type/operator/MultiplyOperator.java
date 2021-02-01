package user11681.phi.program.element.type.operator;

import java.util.Arrays;
import java.util.List;
import net.minecraft.util.Identifier;
import user11681.phi.Localization;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.type.NamedVariable;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.type.ValueType;
import user11681.phi.program.type.Variable;

public class MultiplyOperator extends TexturedElementType implements OperatorElementType<Double> {
    public MultiplyOperator(Identifier texture) {
        super(texture);
    }

    @Override
    public Variable output() {
        return Variable.variable(ValueType.number);
    }

    @Override
    public List<NamedVariable> input() {
        return Arrays.asList(Variable.named(ValueType.number, Localization.multiplicand), Variable.named(ValueType.number, Localization.multiplier));
    }

    @Override
    public void process(Element element, Transaction<Double> transaction) {

    }
}
