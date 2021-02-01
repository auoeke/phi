package user11681.phi.block;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import user11681.phi.network.client.OpenProgrammerPacket;

@SuppressWarnings("deprecation")
public class CompilerBlock extends BlockWithEntity {
    public CompilerBlock(Settings settings) {
        super(settings);
    }

    protected static AssemblerBlockEntity entity(BlockView world, BlockPos position) {
        return (AssemblerBlockEntity) world.getBlockEntity(position);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return AssemblerBlockEntity.type.instantiate(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        super.onUse(state, world, pos, player, hand, hit);

        if (!world.isClient) {
            OpenProgrammerPacket.instance.send(player, PacketByteBufs.create().writeBlockPos(pos).writeCompoundTag(entity(world, pos).program.toTag()));

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
