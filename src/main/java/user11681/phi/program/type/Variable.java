package user11681.phi.program.type;

import net.minecraft.text.Text;

public class Variable {
    public final ValueType type;

    protected Variable(ValueType type) {
        this.type = type;
    }

    public static Variable variable(ValueType type) {
        return new Variable(type);
    }

    public static NamedVariable named(ValueType type, Text name) {
        return new NamedVariable(type, name);
    }

    public Text format() {
        return this.type.name();
    }
}
