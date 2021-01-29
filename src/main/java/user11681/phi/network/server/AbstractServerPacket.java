package user11681.phi.network.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import user11681.phi.network.AbstractPacket;

public abstract class AbstractServerPacket extends AbstractPacket implements ServerPlayNetworking.PlayChannelHandler {
    public AbstractServerPacket(String path) {
        super(path);
    }

    protected abstract void execute(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender);

    @Override
    public final void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender) {
        PacketByteBuf copy = PacketByteBufs.copy(buffer);

        server.execute(() -> this.execute(server, player, handler, copy, sender));
    }

    @Override
    public void register() {
        ServerPlayNetworking.registerGlobalReceiver(this.channel, this);
    }

    @Environment(EnvType.CLIENT)
    public void send(PacketByteBuf information) {
        ClientPlayNetworking.send(this.channel, information);
    }
}
