package dev.ultreon.mods.micacraft.mixin.common;

import dev.ultreon.mods.micacraft.MicaCraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.PanoramaRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PanoramaRenderer.class)
public class PanoramaRendererMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void err422$injectRender(GuiGraphics guiGraphics, int i, int j, float f, float g, CallbackInfo ci) {
        if (MicaCraft.isDisabled()) {
            return;
        }

        MicaCraft.clear();
        guiGraphics.pose().translate(0, 0, 1000);

        ci.cancel();
    }
}
