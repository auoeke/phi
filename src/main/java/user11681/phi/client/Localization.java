package user11681.phi.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import user11681.phi.program.element.type.ElementType;

@Environment(EnvType.CLIENT)
public class Localization {
    public static final TranslatableText screen = new TranslatableText("menu.phi.spell_programmer");

    public static final TranslatableText noneType = new TranslatableText("phi.type.none");

    public static final TranslatableText entityType = new TranslatableText("phi.type.entity");
    public static final TranslatableText numberType = new TranslatableText("phi.type.number");
    public static final TranslatableText positionType = new TranslatableText("phi.type.position");
    public static final TranslatableText vectorType = new TranslatableText("phi.type.vector");

    public static final TranslatableText directionVariable = new TranslatableText("phi.variable.direction");
    public static final TranslatableText operandVariable = new TranslatableText("phi.variable.operand");
    public static final TranslatableText powerVariable = new TranslatableText("phi.variable.power");
    public static final TranslatableText productVariable = new TranslatableText("phi.variable.product");
    public static final TranslatableText targetVariable = new TranslatableText("phi.variable.target");

    @SuppressWarnings("ConstantConditions")
    public static String typeKey(ElementType type) {
        Identifier identifier = ElementType.registry.getId(type);

        return String.format("phi.element.%s.%s.%s", type.group().name.getKey(), identifier.getNamespace(), identifier.getPath());
    }
}
