package user11681.phi.program.transaction;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class Variable {
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
