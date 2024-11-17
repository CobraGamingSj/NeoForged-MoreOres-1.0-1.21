package net.cobra.moreores.block.jukebox;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.sound.ModSoundEvents;

import java.util.function.Supplier;

public interface ModJukeboxSongs {
    ResourceKey<JukeboxSong> TASWELL = of("taswell");
    ResourceKey<JukeboxSong> DREITON = of("dreiton");
    ResourceKey<JukeboxSong> BIOME_FEST = of("biome_fest");
    ResourceKey<JukeboxSong> ARIA_MATH = of("aria_math");
    ResourceKey<JukeboxSong> FEATHERFALL = of("featherfall");
    ResourceKey<JukeboxSong> ENDLESS = of("endless");
    ResourceKey<JukeboxSong> INFINITE_AMETHYST = of("infinite_amethyst");
    ResourceKey<JukeboxSong> DEEPER = of("deeper");
    ResourceKey<JukeboxSong> WATCHER = of("watcher");

    static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, TASWELL, ModSoundEvents.MUSIC_DISC_TASWELL, 514, 1);
        register(context, DREITON, ModSoundEvents.MUSIC_DISC_DREITON, 497, 2);
        register(context, BIOME_FEST, ModSoundEvents.MUSIC_DISC_BIOME_FEST, 376, 3);
        register(context, ARIA_MATH, ModSoundEvents.MUSIC_DISC_ARIA_MATH, 309, 4);
        register(context, FEATHERFALL, ModSoundEvents.MUSIC_DISC_FEATHERFALL, 344, 7);
        register(context, ENDLESS, ModSoundEvents.MUSIC_DISC_ENDLESS, 402, 6);
        register(context, INFINITE_AMETHYST, ModSoundEvents.MUSIC_DISC_INFINITE_AMETHYST, 271, 5);
        register(context, DEEPER, ModSoundEvents.MUSIC_DISC_DEEPER, 198, 15);
        register(context, WATCHER, ModSoundEvents.MUSIC_DISC_WATCHER, 344, 8);
    }

    private static ResourceKey<JukeboxSong> of(String id) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, MoreOresModLoader.prefix(id));
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), (float)lengthInSeconds, comparatorOutput));
    }

}
