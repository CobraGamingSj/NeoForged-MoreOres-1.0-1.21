package net.cobra.moreores.sound;

import net.cobra.moreores.MoreOresModLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, MoreOresModLoader.MOD_ID);

    public static final Supplier<SoundEvent> MUSIC_DISC_TASWELL = register("music_disc.taswell");
    public static final Supplier<SoundEvent> MUSIC_DISC_DREITON = register("music_disc.dreiton");
    public static final Supplier<SoundEvent> MUSIC_DISC_BIOME_FEST = register("music_disc.biome_fest");
    public static final Supplier<SoundEvent> MUSIC_DISC_ARIA_MATH = register("music_disc.aria_math");
    public static final Supplier<SoundEvent> MUSIC_DISC_FEATHERFALL = register("music_disc.featherfall");
    public static final Supplier<SoundEvent> MUSIC_DISC_ENDLESS = register("music_disc.endless");
    public static final Supplier<SoundEvent> MUSIC_DISC_INFINITE_AMETHYST = register("music_disc.infinite_amethyst");
    public static final Supplier<SoundEvent> MUSIC_DISC_DEEPER = register("music_disc.deeper");
    public static final Supplier<SoundEvent> MUSIC_DISC_WATCHER = register("music_disc.watcher");

    private static Supplier<SoundEvent> register(String soundId) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, soundId);
        return SOUND_EVENTS.register(soundId, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
