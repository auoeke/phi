package user11681.phi.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import user11681.phi.Phi;

@Environment(EnvType.CLIENT)
public class Textures {
    public static final Identifier elementBase = Phi.id("textures/element/base.png");

    public static final Identifier arrow = Phi.id("textures/gui/arrow.png");
    public static final Identifier arrows = Phi.id("textures/gui/arrows.png");
    public static final Identifier background = Phi.id("textures/gui/programmer.png");
    public static final Identifier focusFrame = Phi.id("textures/gui/focus_frame.png");
    public static final Identifier focusedBorder = Phi.id("textures/gui/focused_slot.png");
    public static final Identifier hoverFrame = Phi.id("textures/gui/hover_frame.png");
    public static final Identifier sidebar = Phi.id("textures/gui/programmer_sidebar.png");
}
