package user11681.phi.block;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import user11681.phi.Phi;

@SuppressWarnings("deprecation")
public class ProgrammerBlock extends BlockWithEntity {
    public ProgrammerBlock(Settings settings) {
        super(settings);
    }

    protected static ProgrammerBlockEntity entity(BlockView world, BlockPos position) {
        return (ProgrammerBlockEntity) world.getBlockEntity(position);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return ProgrammerBlockEntity.type.instantiate(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        super.onUse(state, world, pos, player, hand, hit);

        if (!world.isClient) {
            ServerPlayNetworking.send((ServerPlayerEntity) player, Phi.channel, PacketByteBufs.create().writeCompoundTag(entity(world, pos).pieces));

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}