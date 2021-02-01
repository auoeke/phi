package user11681.phi.program.element.type.function;

import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import user11681.phi.client.Localization;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.transaction.NamedVariable;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.transaction.ValueType;
import user11681.phi.program.transaction.Variable;

public class AccelerateFunction extends TexturedElementType implements FunctionElementType<Void> {
    public AccelerateFunction(Identifier texture) {
        super(texture);
    }

    @Override
    public Variable output() {
        return null;
    }

    @Override
    public List<NamedVariable> input() {
        return Arrays.asList(Variable.named(ValueType.entity, Localization.targetVariable), Variable.named(ValueType.vector, Localization.directionVariable), Variable.named(ValueType.number, Localization.powerVariable));
    }

    @Override
    public void process(Element element, Transaction<Void> transaction) {
        Entity target = transaction.get("target");
        Vec3d direction = ((Vec3d) transaction.get("direction")).normalize();
        double multiplier = 0.1 * element.tag.getInt("power");

        target.addVelocity(direction.x * multiplier, direction.y * multiplier, direction.z * multiplier);
    }

    @Override
    public int cost(Element piece) {
        return 200 * piece.tag.getInt("power");
    }
}
