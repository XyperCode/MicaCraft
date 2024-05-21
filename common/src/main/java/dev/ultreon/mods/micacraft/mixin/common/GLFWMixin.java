package dev.ultreon.mods.micacraft.mixin.common;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.WindowEventHandler;
import dev.ultreon.mods.micacraft.MicaCraft;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.ByteBuffer;

@Mixin(value = GLFW.class, remap = false)
public class GLFWMixin {

    @Inject(method = "nglfwCreateWindow", at = @At("HEAD"), remap = false)
    private static void err422$injectRender(int width, int height, long title, long monitor, long share, CallbackInfoReturnable<Long> cir) {
        if (MicaCraft.isDisabled()) {
            return;
        }

        // Configure the window to be transparent
        GLFW.glfwWindowHint(GLFW.GLFW_TRANSPARENT_FRAMEBUFFER, GLFW.GLFW_TRUE);
    }
}
