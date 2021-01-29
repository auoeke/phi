package user11681.phi.program.element.type;

import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import user11681.phi.Phi;
import user11681.phi.program.element.Element;
import user11681.phi.program.element.type.function.AccelerateFunction;
import user11681.phi.program.element.type.function.BlinkFunction;
import user11681.phi.program.element.type.operator.MultiplyOperator;
import user11681.phi.program.element.type.operator.RotationOperator;
import user11681.phi.program.element.type.parameter.AttackerParameter;
import user11681.phi.program.element.type.parameter.ExecutorParameter;

public interface ElementType {
    Registry<ElementType> registry = new SimpleRegistry<>(RegistryKey.ofRegistry(Phi.id("pieces")), Lifecycle.stable());

    ElementType accelerate = register(Phi.id("accelerate"), new AccelerateFunction(Phi.id("textures/element/function/accelerate.png")));
    ElementType blink = register(Phi.id("blink"), new BlinkFunction(Phi.id("textures/element/function/blink.png")));

    ElementType attacker = register(Phi.id("attacker"), new AttackerParameter(Phi.id("textures/element/parameter/attacker.png")));
    ElementType executor = register(Phi.id("executor"), new ExecutorParameter(Phi.id("textures/element/parameter/executor.png")));

    ElementType multiply = register(Phi.id("multiply"), new MultiplyOperator(Phi.id("textures/element/operator/multiply.png")));
    ElementType rotation = register(Phi.id("rotation"), new RotationOperator(Phi.id("textures/element/operator/rotation.png")));

    static <T extends ElementType> T register(Identifier identifier, T element) {
        return Registry.register(registry, identifier, element);
    }

    @Environment(EnvType.CLIENT)
    void render(Element element, MatrixStack matrixes, int x, int y);

    int cost(Element element);

    String namespace();

    default Identifier id() {
        return registry.getId(this);
    }

    default Element defaultElement() {
        return new Element(this);
    }

    @Environment(EnvType.CLIENT)
    default List<Text> tooltip() {
        List<Text> lines = new ObjectArrayList<>();

        lines.add(new LiteralText(String.format("%s: %s", I18n.translate("phi.namespace." + this.namespace()), this.name().getString())));

        return lines;
    }

    @SuppressWarnings("ConstantConditions")
    default Text name() {
        Identifier identifier = registry.getId(this);

        return new TranslatableText(String.format("phi.element.%s.%s.%s", this.namespace(), identifier.getNamespace(), identifier.getPath()));
    }
}
