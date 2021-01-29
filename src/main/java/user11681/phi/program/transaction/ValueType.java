package user11681.phi.program.transaction;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import user11681.phi.client.PhiLocalization;

public interface ValueType {
    ValueType none = () -> PhiLocalization.noneType;

    ValueType entity = () -> PhiLocalization.entityType;
    ValueType number = () -> PhiLocalization.numberType;
    ValueType vector = () -> PhiLocalization.vectorType;

    Text name();

    default Variable variable(String name) {
        return new Variable(this, new TranslatableText(name));
    }

    default Variable variable(Text name) {
        return new Variable(this, name);
    }
}
