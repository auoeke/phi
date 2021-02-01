package user11681.phi.program.transaction;

import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class NamedVariable extends Variable {
    public final Text name;

    protected NamedVariable(ValueType type, Text name) {
        super(type);

        this.name = name;
    }

    @Override
    public Text format() {
        return new LiteralText(String.format("%s (%s)", this.name.getString(), super.format().getString()));
    }
}
