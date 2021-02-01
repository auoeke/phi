package user11681.phi.program.element.type.function;

import java.util.Arrays;
import java.util.List;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import user11681.phi.Localization;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.type.NamedVariable;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.type.ValueType;
import user11681.phi.program.type.Variable;

public class ExplodeFunction extends TexturedElementType implements FunctionElementType<Void> {
    public ExplodeFunction(Identifier texture) {
        super(texture);
    }

    @Override
    @Nullable
    public Variable output() {
        return null;
    }

    @Override
    public List<NamedVariable> input() {
        return Arrays.asList(Variable.named(ValueType.position, Localization.targetVariable), Variable.named(ValueType.number, Localization.powerVariable));
    }

    @Override
    public void process(Element element, Transaction<Void> transaction) {

    }

    @Override
    public int cost(Element element) {
        int power = element.tag.getInt("power");

        return 100 * power;
    }
}
