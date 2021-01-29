package user11681.phi.program.transaction;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class Variable {
    public static final Variable entity = new Variable(ValueType.entity, "phi.variable.entity");
    public static final Variable number = new Variable(ValueType.number, "phi.variable.number");
    public static final Variable vector = new Variable(ValueType.vector, "phi.variable.vector");

    public final ValueType type;
    public final Text name;


    public Variable(ValueType type, String name) {
        this(type, new TranslatableText(name));
    }

    public Variable(ValueType type, Text name) {
        this.type = type;
        this.name = name;
    }
}
