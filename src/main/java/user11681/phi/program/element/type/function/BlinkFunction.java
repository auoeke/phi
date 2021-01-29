package user11681.phi.program.element.type.function;

import java.util.Collection;
import java.util.Collections;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.transaction.Variable;

public class BlinkFunction extends TexturedElementType implements FunctionElementType<Void> {
    public BlinkFunction(Identifier texture) {
        super(texture);
    }

    @Override
    public Variable output() {
        return null;
    }

    @Override
    public Collection<Variable> input() {
        return Collections.singleton(Variable.entity);
    }

    @Override
    public void process(Element element, Transaction<Void> transaction) {
        Entity entity = transaction.get("target");
        Vec3d position = entity.getPos();
        int power = element.tag.getInt("power");

        entity.setPosition(position.x, position.y, position.z);
    }

    @Override
    public int cost(Element element) {
        return 200 * element.tag.getInt("power");
    }
}
