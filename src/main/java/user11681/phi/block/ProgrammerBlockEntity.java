package user11681.phi.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import user11681.phi.Phi;

public class ProgrammerBlockEntity extends BlockEntity {
    public static final BlockEntityType<ProgrammerBlockEntity> type = FabricBlockEntityTypeBuilder.create(ProgrammerBlockEntity::new, PhiBlocks.programmer).build();

    protected CompoundTag pieces = new CompoundTag();

    public ProgrammerBlockEntity(BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);

        CompoundTag pieces = tag.getCompound(Phi.ID);

        if (pieces != null) {
            this.pieces = pieces;
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag).put(Phi.ID, this.pieces);

        return tag;
    }
}