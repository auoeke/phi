package user11681.phi.program.piece.type;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import user11681.phi.Phi;
import user11681.phi.program.piece.Element;
import user11681.phi.program.piece.type.function.AccelerateElementType;

public interface ElementType {
    Registry<ElementType> registry = new SimpleRegistry<>(RegistryKey.ofRegistry(Phi.id("pieces")), Lifecycle.stable());

    ElementType accelerate = new AccelerateElementType(Phi.id("textures/program/accelerate.png"));

    @Environment(EnvType.CLIENT)
    void render(MatrixStack matrixes, int x, int y);

    Element fromTag(CompoundTag tag);

    int cost(Element element);
}
