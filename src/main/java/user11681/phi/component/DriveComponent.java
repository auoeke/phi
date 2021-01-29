package user11681.phi.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import user11681.phi.program.Program;

public class DriveComponent implements AutoSyncedComponent {
    public final ItemStack itemStack;

    public Program program;

    public DriveComponent(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        CompoundTag elements = tag.getCompound("program");

        if (elements != null) {
            this.program = new Program(elements);
        }
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.put("program", this.program.toTag());
    }
}
