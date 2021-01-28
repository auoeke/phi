package user11681.phi.program;

import com.mojang.serialization.Lifecycle;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import user11681.phi.Phi;
import user11681.phi.program.piece.type.ElementType;

public class ElementRegistry extends SimpleRegistry<ElementType> {
    public ElementRegistry() {
        super(RegistryKey.ofRegistry(Phi.id("pieces")), Lifecycle.stable());
    }

}
