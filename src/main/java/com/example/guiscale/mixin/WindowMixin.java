package com.example.guiscale.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Window.class)
public abstract class WindowMixin {
    // Interface is always 640 virtual units wide (= scale 3 on 1920px, like your screenshots).
    // Smaller number = bigger GUI (480 = scale 4 on 1920px).
    private static final double TARGET_GUI_WIDTH = 640.0;

    @ModifyVariable(method = "setScaleFactor", at = @At("HEAD"), argsOnly = true)
    private double guiscale$override(double original) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.options == null) return original;

        int chosen = mc.options.getGuiScale().getValue();
        if (chosen == 4 || chosen == 0) {
            Window self = (Window) (Object) this;
            return Math.max(1.0, self.getFramebufferWidth() / TARGET_GUI_WIDTH);
        }
        return original;
    }
}
