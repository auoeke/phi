package user11681.phi.program.type;

import net.minecraft.text.Text;

public interface ValueType {
    SimpleType entity = new SimpleType("entity");
    SimpleType number = new SimpleType("number");
    SimpleType vector = new SimpleType("vector");
    SimpleType position = new SimpleType("position");

    Text name();
}
