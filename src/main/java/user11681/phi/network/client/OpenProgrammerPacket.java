package user11681.phi.network.client;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import user11681.phi.client.PhiLocalization;
import user11681.phi.client.gui.ProgrammerScreen;
import user11681.phi.program.Program;

public class OpenProgrammerPacket extends AbstractClientPacket {
    public static final OpenProgrammerPacket instance = new OpenProgrammerPacket("open_programmer");

    public OpenProgrammerPacket(String path) {
        super(path);
    }

    @Override
    public void execute(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender) {
        client.openScreen(new ProgrammerScreen(PhiLocalization.screen, buffer.readBlockPos(), new Program(buffer.readCompoundTag())));
    }
}
