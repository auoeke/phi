package user11681.phi.program.piece;

import java.util.function.Consumer;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import user11681.phi.Phi;

@SuppressWarnings("MethodDoesntCallSuperMethod")
public class Program implements Cloneable {
    public static final int X = 9;
    public static final int Y = 9;

    protected final Element[][] grid = new Element[9][9];

    public void toTag(CompoundTag tag) {
        for (Element[] elements : this.grid) {
            for (Element element : elements) {
                element.toTag(tag);
            }
        }
    }

    public static Program fromTag(CompoundTag tag) {
        Program program = new Program();

        if (tag.contains(Phi.ID)) {
            ListTag list = tag.getList(Phi.ID, NbtType.LIST);

            for (int i = 0, size = list.size(); i < size; i++) {
                ListTag pieces = (ListTag) list.get(i);

                for (int j = 0, tagsSize = pieces.size(); j < tagsSize; j++) {
                    program.grid[i][j] = Element.fromTag(pieces.getCompound(j));
                }
            }

        }

        return program;
    }

    public void execute(PlayerEntity owner) {
    }

    public int cost() {
        int cost = 0;

        for (Element[] column : this.grid) {
            for (Element element : column) {
                cost += element.cost();
            }
        }

        return cost;
    }

    @Override
    public Program clone() {
        Program program = new Program();

        for (int i = 0, gridLength = this.grid.length; i < gridLength; i++) {
            Element[] elements = this.grid[i];

            for (int j = 0, piecesLength = elements.length; j < piecesLength; j++) {
                program.grid[i][j] = elements[j].clone();
            }
        }

        return program;
    }

    public Element get(int x, int y) {
        return this.grid[x][y];
    }

    public void set(int x, int y, Element element) {
        this.grid[x][y] = element;
    }

    public void forEach(Consumer<Element> action) {
        for (int i = 0, gridLength = grid.length; i < gridLength; i++) {
            Element[] elements = grid[i] = grid[i].clone();

            for (int j = 0, piecesLength = elements.length; j < piecesLength; j++) {
                action.accept(elements[i]);
            }
        }
    }
}
