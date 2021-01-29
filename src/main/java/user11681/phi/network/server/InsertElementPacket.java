package user11681.phi.network.server;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class InsertElementPacket extends AbstractServerPacket {
    public static final InsertElementPacket instance = new InsertElementPacket("insert_element");

    public InsertElementPacket(String path) {
        super(path);
    }

    @Override
    public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {

    }
}
