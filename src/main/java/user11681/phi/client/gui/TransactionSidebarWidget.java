package user11681.phi.client.gui;

import java.util.Collections;
import java.util.List;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;

public class TransactionSidebarWidget extends AbstractParentElement implements Drawable {
    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

    }
}
