package user11681.phi.network;

import net.minecraft.util.Identifier;
import user11681.phi.Phi;

public abstract class AbstractPacket {
    public final Identifier channel;

    public AbstractPacket(String path) {
        this.channel = Phi.id(path);
    }

    public abstract void register();
}
