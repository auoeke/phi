package user11681.phi.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import user11681.phi.Phi;

public class PhiComponents {
    public static final ComponentKey<PhiComponent> phi = ComponentRegistryV3.INSTANCE.getOrCreate(Phi.id("phi"), PhiComponent.class);
    public static final ComponentKey<DriveComponent> drive = ComponentRegistryV3.INSTANCE.getOrCreate(Phi.id("drive"), DriveComponent.class);
    public static final ComponentKey<InterpreterComponent> interpreter = ComponentRegistryV3.INSTANCE.getOrCreate(Phi.id("interpreter"), InterpreterComponent.class);
}
