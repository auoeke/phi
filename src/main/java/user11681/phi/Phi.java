package user11681.phi;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import java.util.List;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import user11681.phi.block.ProgrammerBlockEntity;
import user11681.phi.component.DriveComponent;
import user11681.phi.component.PhiComponent;
import user11681.phi.component.InterpreterComponent;
import user11681.phi.component.PhiComponents;
import user11681.phi.item.DriveItem;
import user11681.phi.item.PhiItems;
import user11681.phi.network.ProgrammerScreenPacket;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientModInitializer.class)
public class Phi implements ModInitializer, ClientModInitializer, EntityComponentInitializer, ItemComponentInitializer {
    public static final String ID = "phi";

    public static final Identifier channel = id(ID);

    public static final ItemGroup itemGroup = FabricItemGroupBuilder
        .create(id(ID))
        .icon(PhiItems.interpreter::getDefaultStack)
        .appendItems((List<ItemStack> stacks) -> stacks.add(PhiItems.programmer.getDefaultStack()))
        .build();

    public static Identifier id(String path) {
        return new Identifier(ID, path);
    }

    private static void item(String path, Item item) {
        Registry.register(Registry.ITEM, id(path), item);
    }

    @Override
    public void onInitialize() {
        item("cad", PhiItems.interpreter);
        item("programmer", PhiItems.programmer);

        Registry.register(Registry.BLOCK_ENTITY_TYPE, id("programmer"), ProgrammerBlockEntity.type);

        ClientPlayNetworking.registerGlobalReceiver(channel, new ProgrammerScreenPacket());
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {}

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        registry.registerFor(PhiItems.interpreter, PhiComponents.interpreter, InterpreterComponent::new);
        registry.registerFor(DriveItem.class::isInstance, PhiComponents.drive, DriveComponent::new);
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PhiComponents.phi, PhiComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
