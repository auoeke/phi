package user11681.phi.program.piece;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import user11681.phi.program.piece.type.ElementType;

@SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException", "ConstantConditions"})
public class Element implements Cloneable {
    public final ElementType type;

    public Connections ingoing;
    public Connections outgoing;

    public Element(ElementType type) {
        this.type = type;
    }

    public void toTag(CompoundTag tag) {
        tag.putString("id", ElementType.registry.getId(this.type).toString());
    }

    public static Element fromTag(CompoundTag tag) {
        ElementType type = ElementType.registry.get(new Identifier(tag.getString("id")));

        if (type == null) {
            return null;
        }

        tag.remove("id");

        return type.fromTag(tag);
    }

    @Override
    protected Element clone() {
        try {
            return (Element) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public int cost() {
        return type.cost(this);
    }
}
