package dev.ultreon.mods.micacraft;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.ultreon.mods.micacraft.mixin.common.DwmSystemBackdropType;
import net.minecraft.Util;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.opengl.GL20;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class MicaCraft
{
	public static final String MOD_ID = "micacraft";
	private static final Logger LOGGER = LoggerFactory.getLogger("MicaCraft");
	private static final int FALSE = 0;
	private static final int TRUE = 1;

	private static final int DARK_MODE = TRUE;
	private static boolean disabled;

	public static boolean isDisabled() {
		return disabled;
	}

	public static void init() {
		if (!Files.exists(Path.of("acrylic.dll"))) {
			try {
				Files.copy(Objects.requireNonNull(MicaCraft.class.getResourceAsStream("/acrylic.dll")), Path.of("acrylic.dll"), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				LOGGER.error("Failed to copy Acrylic.dll", e);
			}
		}

		ClientLifecycleEvent.CLIENT_STARTED.register(client -> {
			long peer = GLFWNativeWin32.glfwGetWin32Window(client.getWindow().getWindow());

			if (Util.getPlatform() == Util.OS.WINDOWS) {
				if (isWindows10()) {
//					if (!Acrylic.applyAcrylic(peer)) {
//						LOGGER.warn("Unable to set window composition attribute");
//						MicaCraft.disabled = true;
//					}
					// TODO: Add support for Windows 10

					LOGGER.warn("Windows 10 not supported");
				} else if (isWindows11()) {
                    if (!Acrylic.applyTabbed(peer)) {
                        LOGGER.warn("Unable to set immersive dark mode attribute");
                        MicaCraft.disabled = true;
                    }
				} else {
					LOGGER.warn("Unsupported Windows version");
					MicaCraft.disabled = true;
				}
			} else if (Util.getPlatform() == Util.OS.OSX) {
				if (!Acrylic.applyVibrancy(peer)) {
					LOGGER.warn("Failed to set window composition attribute");
					MicaCraft.disabled = true;
                }
			} else {
				LOGGER.warn("Unsupported platform");
				MicaCraft.disabled = true;
			}
		});
    }

	private static boolean isWindows11() {
		return Util.getPlatform() == Util.OS.WINDOWS
				&& (System.getProperty("os.name").toLowerCase().contains("windows 11"));
	}

	private static boolean isWindows10() {
		return Util.getPlatform() == Util.OS.WINDOWS
				&& (System.getProperty("os.name").toLowerCase().contains("windows 10"));
	}

	public static boolean isSupported() {
		return (Util.getPlatform() == Util.OS.WINDOWS && (isWindows10() || isWindows11()))
				|| Util.getPlatform() == Util.OS.OSX;
	}

    public static void clear() {
		RenderSystem.clear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT, Util.getPlatform() == Util.OS.OSX);
    }
}
