package user11681.phi.network.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import user11681.phi.network.AbstractPacket;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientPlayNetworking.PlayChannelHandler.class)
public abstract class AbstractClientPacket extends AbstractPacket implements ClientPlayNetworking.PlayChannelHandler {
    public AbstractClientPacket(String path) {
        super(path);
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
