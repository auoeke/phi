package user11681.phi.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import user11681.phi.Phi;
import user11681.phi.block.PhiBlocks;

public class PhiItems {
    public static final InterpreterItem interpreter = new InterpreterItem(new Item.Settings().group(Phi.itemGroup));

    public static final BlockItem programmer = new BlockItem(PhiBlocks.assembler, new Item.Settings().group(Phi.itemGroup));
}
