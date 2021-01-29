package user11681.phi.network.server;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import user11681.phi.block.ProgrammerBlockEntity;
import user11681.phi.program.element.type.ElementType;

public class InsertElementPacket extends AbstractServerPacket {
    public static final InsertElementPacket instance = new InsertElementPacket("insert_element");

    public InsertElementPacket(String path) {
        super(path);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void execute(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender) {
        ProgrammerBlockEntity programmer = (ProgrammerBlockEntity) player.world.getBlockEntity(buffer.readBlockPos());
        int index = buffer.readVarInt();
        ElementType type = ElementType.registry.get(buffer.readIdentifier());

        programmer.program.elements.set(index, type.defaultElement());
    }
}
