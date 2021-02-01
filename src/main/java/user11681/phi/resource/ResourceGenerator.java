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

public class ResourceGenerator implements ModInitializer {
    public static final RuntimeResourcePack resources = RuntimeResourcePack.create(Phi.id(Phi.ID).toString(), 7);

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
        type(ElementType.pi, "\u03c0");
        type(ElementType.tau, "\u03c4");

        type(ElementType.accelerate, "accelerate");
        type(ElementType.blink, "blink");
        type(ElementType.explode, "accelerate");

        type(ElementType.multiply, "multiply");
        type(ElementType.rotation, "rotation");

        type(ElementType.attacker, "attacker");
        type(ElementType.executor, "executor");

        variable(Localization.directionVariable, "Direction");
        variable(Localization.operandVariable, "Operand");
        variable(Localization.powerVariable, "Power");
        variable(Localization.productVariable, "Product");
        variable(Localization.targetVariable, "Target");

        resources.addLang(Phi.id("en_us"), lang);
        lang = null;
    }

    private static void group(ElementGroup group, String name) {
        lang.entry(group.name.getKey(), name);
    }

    private static void type(ElementType type, String name) {
        lang.entry(Localization.typeKey(type), name);
    }

    private static void variable(TranslatableText text, String name) {
        lang.entry(text.getKey(), name);
    }
}
