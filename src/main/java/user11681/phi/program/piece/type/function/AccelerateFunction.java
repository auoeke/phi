package user11681.phi.program.piece.type.function;

import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.Input;
import user11681.phi.program.piece.type.TexturedElementType;

public class AccelerateFunction extends TexturedElementType implements FunctionElementType {
    public AccelerateFunction(Identifier texture) {
        super(texture);
    }

    @Override
    public TypedActionResult<Text> execute(Element element, Input input) {
        Entity target = input.get("target");
        Vec3d direction = ((Vec3d) input.get("direction")).normalize();
        double multiplier = 0.1 * element.tag.getInt("power");

        target.addVelocity(direction.x * multiplier, direction.y * multiplier, direction.z * multiplier);

        return TypedActionResult.success(null);
    }

    @Override
    public int cost(Element piece) {
        return 200 * piece.tag.getInt("power");
    }
}
