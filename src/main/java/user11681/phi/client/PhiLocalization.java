package user11681.phi.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class PhiLocalization {
    public static final TranslatableText screen = new TranslatableText("menu.phi.spell_programmer");

    public static final TranslatableText noneType = new TranslatableText("phi.type.none");

    public static final TranslatableText entityType = new TranslatableText("phi.type.entity");
    public static final TranslatableText numberType = new TranslatableText("phi.type.number");
    public static final TranslatableText vectorType = new TranslatableText("phi.type.vector");
}
