package user11681.phi.program.type;

import net.minecraft.text.TranslatableText;

public class SimpleType implements ValueType {
    private final TranslatableText name;

    public SimpleType(String type) {
        this.name = new TranslatableText("phi.type." + type);
    }

    @Override
    public TranslatableText name() {
        return this.name;
    }
}
