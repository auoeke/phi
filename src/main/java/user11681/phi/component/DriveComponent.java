package user11681.phi.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import user11681.phi.program.piece.Program;

public class DriveComponent implements AutoSyncedComponent {
    public final ItemStack itemStack;

    public Program program;

    public DriveComponent(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.program = Program.fromTag(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        this.program.toTag(tag);
    }
}
