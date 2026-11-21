package com.example.guiscale.mixin;

import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Window.class)
public abstract class WindowMixin {
    // GUI is always about 640 units wide (scale 3 on 1920px, like your screenshots).
    // Smaller number = bigger GUI (480 = scale 4 on 1920px).
    private static final float TARGET_GUI_WIDTH = 640.0f;

    @Inject(method = "calculateScaleFactor", at = @At("RETURN"), cancellable = true)
    private void guiscale$override(int guiScale, boolean forceUnicodeFont, CallbackInfoReturnable<Integer> cir) {
        // Only when GUI Scale is set to 4 or Auto (0)
        if (guiScale == 4 || guiScale == 0) {
            Window self = (Window) (Object) this;
            int scale = Math.round(self.getFramebufferWidth() / TARGET_GUI_WIDTH);
            cir.setReturnValue(Math.max(1, scale));
        }
    }
}
