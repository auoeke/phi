package user11681.phi.resource;

import java.util.List;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.lang.JLang;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.ResourcePack;
import net.minecraft.text.TranslatableText;
import user11681.phi.Phi;
import user11681.phi.client.Localization;
import user11681.phi.program.element.group.ElementGroup;
import user11681.phi.program.element.type.ElementType;
import user11681.phi.program.type.SimpleType;
import user11681.phi.program.type.ValueType;

public class ResourceGenerator implements ModInitializer {
    private static final RuntimeResourcePack resources = RuntimeResourcePack.create(Phi.id(Phi.ID).toString(), 7);

    private static transient JLang lang = JLang.lang();

    @Override
    public void onInitialize() {
        RRPCallback.EVENT.register(((List<ResourcePack> resourcePacks) -> resourcePacks.add(resources)));

        group(ElementGroup.constant, "Constant");
        group(ElementGroup.function, "Function");
        group(ElementGroup.operator, "Operator");
        group(ElementGroup.parameter, "Parameter");

        type(ElementType.euler, "Euler's constant");
        type(ElementType.number, "number");
        type(ElementType.pi, "π");
        type(ElementType.tau, "τ");

        type(ElementType.accelerate, "accelerate");
        type(ElementType.blink, "blink");
        type(ElementType.explode, "explode");

        type(ElementType.multiply, "multiply");
        type(ElementType.rotation, "rotation");

        type(ElementType.attacker, "attacker");
        type(ElementType.executor, "executor");

        type(ValueType.entity, "Entity");
        type(ValueType.number, "Number");
        type(ValueType.position, "Position");
        type(ValueType.vector, "Vector");

        text(Localization.directionVariable, "Direction");
        text(Localization.operandVariable, "Operand");
        text(Localization.multiplicand, "Multiplicand");
        text(Localization.multiplier, "Multiplier");
        text(Localization.powerVariable, "Power");
        text(Localization.productVariable, "Product");
        text(Localization.targetVariable, "Target");

        resources.addLang(Phi.id("en_us"), lang);
        lang = null;
    }

    private static void group(ElementGroup group, String name) {
        lang.entry(group.name.getKey(), name);
    }

    private static void type(ElementType type, String name) {
        lang.entry(Localization.typeKey(type), name);
    }

    private static void type(SimpleType type, String name) {
        lang.entry(type.name().getKey(), name);
    }

    private static void text(TranslatableText text, String name) {
        lang.entry(text.getKey(), name);
    }
}
