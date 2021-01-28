package user11681.phi.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import user11681.phi.client.PhiLocalization;
import user11681.phi.client.gui.ProgrammerScreen;
import user11681.phi.program.piece.Program;

@SuppressWarnings("ConstantConditions")
@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientPlayNetworking.PlayChannelHandler.class)
public class ProgrammerScreenPacket implements ClientPlayNetworking.PlayChannelHandler {
    @Environment(EnvType.CLIENT)
    @Override
    public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender responseSender) {
        client.openScreen(new ProgrammerScreen(PhiLocalization.screen, Program.fromTag(buffer.readCompoundTag())));
    }
}
