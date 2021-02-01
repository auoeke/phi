package user11681.phi.program.transaction;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import user11681.phi.client.Localization;

public interface ValueType {
    ValueType none = () -> Localization.noneType;

    ValueType entity = () -> Localization.entityType;
    ValueType number = () -> Localization.numberType;
    ValueType vector = () -> Localization.vectorType;
    ValueType position = () -> Localization.positionType;

    Text name();

    default Variable variable(String name) {
        return new Variable(this, new TranslatableText(name));
    }

    default Variable variable(Text name) {
        return new Variable(this, name);
    }
}
