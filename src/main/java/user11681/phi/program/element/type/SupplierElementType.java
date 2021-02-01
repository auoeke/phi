package user11681.phi.program.element.type;

import java.util.List;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import user11681.phi.program.type.Variable;

public interface SupplierElementType extends ElementType {
    Variable output();

    @Override
    default List<Text> tooltip() {
        List<Text> tooltip = ElementType.super.tooltip();

        tooltip.add(LiteralText.EMPTY);

        Variable output = this.output();

        if (output != null) {
            tooltip.add(new LiteralText(String.format("Output: %s", output.format().getString())));
        }

        return tooltip;
    }
}
