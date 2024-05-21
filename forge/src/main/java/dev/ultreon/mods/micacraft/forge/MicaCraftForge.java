package dev.ultreon.mods.micacraft.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.ultreon.mods.micacraft.MicaCraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MicaCraft.MOD_ID)
public class MicaCraftForge {
    public MicaCraftForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(MicaCraft.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        MicaCraft.init();
    }
}