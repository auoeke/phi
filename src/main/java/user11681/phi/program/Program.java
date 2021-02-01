package user11681.phi.program;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import user11681.phi.program.element.Element;
import user11681.phi.util.ProgramGrid;

@SuppressWarnings("MethodDoesntCallSuperMethod")
public class Program implements Cloneable {
    public static final int SIZE = 9;

    public final ProgramGrid<Element> elements = new ProgramGrid<>();

    public Program() {}

    public Program(CompoundTag tag) {
        this.fromTag(tag);
    }

    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();
        ListTag elements = new ListTag();

        tag.put("elements", elements);

        this.elements.forEach((int x, int y, Element element) -> elements.add(element == null ? new CompoundTag() : element.toTag()));

        return tag;
    }

    public void fromTag(CompoundTag tag) {
        ListTag elements = tag.getList("elements", NbtType.COMPOUND);

        if (elements != null) {
            this.elements.forEach((int i) -> this.elements.set(i, Element.fromTag(elements.getCompound(i))));
        }
    }

    public void execute(PlayerEntity owner) {
    }

    public int cost() {
        int cost = 0;

        for (Element element : this.elements) {
            cost += element.cost();
        }

        return cost;
    }

    @Override
    public Program clone() {
        Program program = new Program();

        program.elements.forEach((int x, int y) -> program.elements.set(x, y, this.elements.get(x, y).clone()));

        return program;
    }
}
