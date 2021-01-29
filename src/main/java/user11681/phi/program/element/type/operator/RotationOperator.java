package user11681.phi.program.element.type.operator;

import java.util.Collection;
import java.util.Collections;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.transaction.Variable;

public class RotationOperator extends TexturedElementType implements OperatorElementType<Vec3d> {
    public RotationOperator(Identifier texture) {
        super(texture);
    }

    @Override
    public Variable output() {
        return Variable.vector;
    }

    @Override
    public Collection<Variable> input() {
        return Collections.singleton(Variable.entity);
    }

    @Override
    public void process(Element element, Transaction<Vec3d> transaction) {

    }
}
