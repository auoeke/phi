package user11681.phi.program.element.group;

import com.mojang.serialization.Lifecycle;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import user11681.phi.Phi;

public class ElementGroup {
    public static final Registry<ElementGroup> registry = new SimpleRegistry<>(RegistryKey.ofRegistry(Phi.id("element_group")), Lifecycle.stable());

    public static final ElementGroup constant = register(Phi.id("constant"));
    public static final ElementGroup function = register(Phi.id("function"));
    public static final ElementGroup operator = register(Phi.id("operator"));
    public static final ElementGroup parameter = register(Phi.id("parameter"));

    public final TranslatableText name;

    private ElementGroup(Identifier identifier) {
        this.name = new TranslatableText(String.format("phi.element_group.%s.%s", identifier.getNamespace(), identifier.getPath()));
    }

    public static ElementGroup register(String namespace, String path) {
        return register(new Identifier(namespace, path));
    }

    public static ElementGroup register(Identifier identifier) {
        return Registry.register(registry, identifier, new ElementGroup(identifier));
    }
}
