package user11681.phi.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class InterpreterComponent implements AutoSyncedComponent {
    public final ItemStack itemStack;
    public final List<ItemStack> drives = new ArrayList<>();

    protected int programIndex;

    public InterpreterComponent(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public DriveComponent drive() {
        if (this.programIndex < 0) {
            return null;
        }

        ItemStack drive = this.drives.get(this.programIndex);

        if (drive == null) {
            return null;
        }

        return PhiComponents.drive.get(drive);
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        ListTag drives = tag.getList("drives", NbtType.COMPOUND);

        if (drives != null) {
            for (Tag drive : drives) {
                this.drives.add(ItemStack.fromTag((CompoundTag) drive));
            }
        }

        this.programIndex = tag.contains("programIndex") ? tag.getInt("programIndex") : -1;
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("programIndex", this.programIndex);

        ListTag drives = new ListTag();

        for (ItemStack drive : this.drives) {
            CompoundTag driveTag = new CompoundTag();

            drive.toTag(driveTag);
            drives.add(driveTag);
        }

        tag.put("drives", drives);
    }
}
