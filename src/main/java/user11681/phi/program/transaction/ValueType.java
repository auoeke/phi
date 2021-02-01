package user11681.phi.program.transaction;

import net.minecraft.text.Text;
import user11681.phi.client.Localization;

public interface ValueType {
    ValueType entity = () -> Localization.entityType;
    ValueType number = () -> Localization.numberType;
    ValueType vector = () -> Localization.vectorType;
    ValueType position = () -> Localization.positionType;

    Text name();
}
