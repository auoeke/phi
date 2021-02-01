package user11681.phi.program.element.type.function;

import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import user11681.phi.Localization;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.TexturedElementType;
import user11681.phi.program.type.NamedVariable;
import user11681.phi.program.transaction.Transaction;
import user11681.phi.program.type.ValueType;
import user11681.phi.program.type.Variable;

public class BlinkFunction extends TexturedElementType implements FunctionElementType<Void> {
    public BlinkFunction(Identifier texture) {
        super(texture);
    }

    @Nullable
    @Override
    public Variable output() {
        return null;
    }

    @Override
    public List<NamedVariable> input() {
        return Arrays.asList(Variable.named(ValueType.entity, Localization.targetVariable), Variable.named(ValueType.number, Localization.powerVariable));
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
