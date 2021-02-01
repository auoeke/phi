package user11681.phi.program.element.type;

import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import user11681.phi.Phi;
import user11681.phi.client.Localization;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.group.ElementGroup;
import user11681.phi.program.element.type.constant.EulerConstant;
import user11681.phi.program.element.type.constant.NumberConstant;
import user11681.phi.program.element.type.constant.PiConstant;
import user11681.phi.program.element.type.constant.TauConstant;
import user11681.phi.program.element.type.function.AccelerateFunction;
import user11681.phi.program.element.type.function.BlinkFunction;
import user11681.phi.program.element.type.function.ExplodeFunction;
import user11681.phi.program.element.type.operator.MultiplyOperator;
import user11681.phi.program.element.type.operator.RotationOperator;
import user11681.phi.program.element.type.parameter.AttackerParameter;
import user11681.phi.program.element.type.parameter.ExecutorParameter;

public interface ElementType {
    Registry<ElementType> registry = new SimpleRegistry<>(RegistryKey.ofRegistry(Phi.id("element_type")), Lifecycle.stable());

    ElementType euler = register(Phi.id("euler"), new EulerConstant());
    ElementType number = register(Phi.id("number"), new NumberConstant());
    ElementType pi = register(Phi.id("pi"), new PiConstant());
    ElementType tau = register(Phi.id("tau"), new TauConstant());

    ElementType accelerate = register(Phi.id("accelerate"), new AccelerateFunction(Phi.id("textures/element/function/accelerate.png")));
    ElementType blink = register(Phi.id("blink"), new BlinkFunction(Phi.id("textures/element/function/blink.png")));
    ElementType explode = register(Phi.id("explode"), new ExplodeFunction(Phi.id("textures/element/function/explode.png")));

    ElementType multiply = register(Phi.id("multiply"), new MultiplyOperator(Phi.id("textures/element/operator/multiply.png")));
    ElementType rotation = register(Phi.id("rotation"), new RotationOperator(Phi.id("textures/element/operator/rotation.png")));

    ElementType attacker = register(Phi.id("attacker"), new AttackerParameter(Phi.id("textures/element/parameter/attacker.png")));
    ElementType executor = register(Phi.id("executor"), new ExecutorParameter(Phi.id("textures/element/parameter/executor.png")));

    static <T extends ElementType> T register(Identifier identifier, T element) {
        return Registry.register(registry, identifier, element);
    }

    @Environment(EnvType.CLIENT)
    void render(Element element, MatrixStack matrixes, int x, int y);

    int cost(Element element);

    ElementGroup group();

    default Identifier id() {
        return registry.getId(this);
    }

    default Element defaultElement() {
        return new Element(this);
    }

    @Environment(EnvType.CLIENT)
    default List<Text> tooltip() {
        return new ObjectArrayList<>(new Text[]{new LiteralText(String.format("%s: %s", this.group().name.getString(), this.name().getString()))});
    }

    default Text name() {
        return new TranslatableText(Localization.typeKey(this));
    }
}
