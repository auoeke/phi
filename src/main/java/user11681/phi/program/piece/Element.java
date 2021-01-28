package user11681.phi.program.piece;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import user11681.phi.program.piece.type.ElementType;

@SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException", "ConstantConditions"})
public class Element implements Cloneable {
    public final ElementType type;
    public final CompoundTag tag;

    public Connections ingoing;
    public Connections outgoing;

    public Element(ElementType type) {
        this(type, new CompoundTag());
    }

    public Element(ElementType type, CompoundTag tag) {
        this.type = type;
        this.tag = tag;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that instanceof Element) {
            Element element = (Element) that;

            return this.type == element.type && this.tag.equals(element.tag);
        }

        return false;
    }

    public CompoundTag toTag() {
        CompoundTag tag = this.tag.copy();

        tag.putString("id", ElementType.registry.getId(this.type).toString());

        return tag;
    }

    public static Element fromTag(CompoundTag tag) {
        String identifier = tag.getString("id");

        if (identifier.isEmpty()) {
            return null;
        }

        tag.remove("id");

        return new Element(ElementType.registry.get(new Identifier(identifier)));
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
