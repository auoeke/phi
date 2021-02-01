package user11681.phi.program.element.type;

import java.util.List;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import user11681.phi.program.element.Element;
import user11681.phi.program.transaction.NamedVariable;
import user11681.phi.program.transaction.Transaction;

public interface TransactionElementType<O> extends SupplierElementType {
    List<NamedVariable> input();

    void process(Element element, Transaction<O> transaction);

    @Override
    default List<Text> tooltip() {
        List<Text> tooltip = SupplierElementType.super.tooltip();

        tooltip.add(new LiteralText("Inputs:"));

        this.input().forEach((NamedVariable variable) -> tooltip.add(new LiteralText(String.format("- %s", variable.format().getString()))));

        return tooltip;
    }
}
