package user11681.phi.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

public class PhiComponent implements AutoSyncedComponent {
    public final PlayerEntity player;

    public int phi;

    public PhiComponent(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.phi = tag.getInt("phi");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("phi", this.phi);
    }
}
