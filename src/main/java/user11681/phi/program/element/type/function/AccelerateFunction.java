package user11681.phi.program.element.type.function;

import java.util.Arrays;
import java.util.Collection;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.transaction.Transaction;
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
    public Collection<Variable> input() {
        return Arrays.asList(Variable.entity, Variable.vector);
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
