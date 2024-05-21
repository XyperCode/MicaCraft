package dev.ultreon.mods.micacraft.fabric;

import dev.ultreon.mods.micacraft.MicaCraft;
import net.fabricmc.api.ModInitializer;

public class MicaCraftFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MicaCraft.init();
    }
}