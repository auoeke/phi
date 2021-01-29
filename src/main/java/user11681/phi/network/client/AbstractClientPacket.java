package user11681.phi.network.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import user11681.phi.network.AbstractPacket;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientPlayNetworking.PlayChannelHandler.class)
public abstract class AbstractClientPacket extends AbstractPacket implements ClientPlayNetworking.PlayChannelHandler {
    public AbstractClientPacket(String path) {
        super(path);
    }

    protected abstract void execute(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender);

    @Override
    public final void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender) {
        PacketByteBuf copy = PacketByteBufs.copy(buffer);

        client.execute(() -> this.execute(client, handler, copy, sender));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void register() {
        ClientPlayNetworking.registerGlobalReceiver(this.channel, this);
    }

    public void send(PlayerEntity receiver, PacketByteBuf information) {
        this.send((ServerPlayerEntity) receiver, information);
    }

    public void send(ServerPlayerEntity receiver, PacketByteBuf information) {
        ServerPlayNetworking.send(receiver, this.channel, information);
    }
}
