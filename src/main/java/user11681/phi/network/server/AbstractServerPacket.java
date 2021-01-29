package user11681.phi.network.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import user11681.phi.network.AbstractPacket;

public abstract class AbstractServerPacket extends AbstractPacket implements ServerPlayNetworking.PlayChannelHandler {
    public AbstractServerPacket(String path) {
        super(path);
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
