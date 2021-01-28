package user11681.phi.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import user11681.phi.component.DriveComponent;
import user11681.phi.component.PhiComponent;
import user11681.phi.component.InterpreterComponent;
import user11681.phi.component.PhiComponents;
import user11681.phi.program.piece.Program;

public class InterpreterItem extends Item {
    public InterpreterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        InterpreterComponent programs = PhiComponents.interpreter.get(itemStack);
        DriveComponent drive = programs.drive();

        if (drive == null) {
            return TypedActionResult.pass(itemStack);
        }

        PhiComponent phi = PhiComponents.phi.get(user);
        Program program = drive.program;

        if (program == null) {
            return TypedActionResult.pass(itemStack);
        }

        int cost = program.cost();

        if (cost <= phi.phi) {
            phi.phi -= cost;

            program.execute(user);
        }

        return TypedActionResult.success(itemStack);
    }
}
