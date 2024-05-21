package dev.ultreon.mods.micacraft.mixin.common;

public class DwmSystemBackdropType {
    /**
     * Disable backdrop type
     */
    public static final int DISABLE = 0x00000001; // None

    /**
     * Windows 11 Mica theme
     */
    public static final int MAIN_WINDOW = 0x00000002; // Mica

    /**
     * Windows 10 Acrylic theme
     */
    public static final int TRANSIENT_WINDOW = 0x00000003; // Acrylic

    /**
     * Windows 11 Tabbed theme
     */
    public static final int TABBED_WINDOW = 0x00000004; // Tabbed
}
