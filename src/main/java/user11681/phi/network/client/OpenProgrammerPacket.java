package user11681.phi.network.client;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import user11681.phi.client.PhiLocalization;
import user11681.phi.client.gui.ProgrammerScreen;
import user11681.phi.program.piece.Program;

@SuppressWarnings("ConstantConditions")
public class OpenProgrammerPacket extends AbstractClientPacket {
    public static final OpenProgrammerPacket instance = new OpenProgrammerPacket("open_programmer");

    public OpenProgrammerPacket(String path) {
        super(path);
    }

    @Override
    public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender responseSender) {
        client.openScreen(new ProgrammerScreen(PhiLocalization.screen, Program.fromTag(buffer.readCompoundTag())));
    }
}
