package user11681.phi.program.piece.type;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import user11681.phi.Phi;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.type.function.AccelerateElementType;

public interface ElementType {
    Registry<ElementType> registry = new SimpleRegistry<>(RegistryKey.ofRegistry(Phi.id("pieces")), Lifecycle.stable());

    ElementType accelerate = register(Phi.id("accelerate"), new AccelerateElementType(Phi.id("textures/element/accelerate.png")));
    ElementType executor = register(Phi.id("executor"), new AccelerateElementType(Phi.id("textures/element/executor.png")));

    static <T extends ElementType> T register(Identifier identifier, T element) {
        return Registry.register(registry, identifier, element);
    }

    @Environment(EnvType.CLIENT)
    void render(MatrixStack matrixes, int x, int y);

    Element fromTag(CompoundTag tag);

    int cost(Element element);

    @SuppressWarnings("ConstantConditions")
    default Text name() {
        Identifier identifier = registry.getId(this);

        return new TranslatableText("element.%s.%s", identifier.getNamespace(), identifier.getPath());
    }
}
