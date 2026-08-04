package org.cobra.moreores.item.equipment.trim;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import org.cobra.moreores.MoreOresModLoader;

public class ModTrimPatterns {
    public static final ResourceKey<TrimPattern> GUARDIAN = of("guardian");

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        register(context, GUARDIAN);
    }

    public static void register(BootstrapContext<TrimPattern> context, ResourceKey<TrimPattern> key) {
        TrimPattern armorTrimPattern = new TrimPattern(getId(key), Component.translatable(Util.makeDescriptionId("trim_pattern", getId(key))), false);
        context.register(key, armorTrimPattern);
    }

    public static Identifier getId(ResourceKey<TrimPattern> key) {
        return key.identifier();
    }

    private static ResourceKey<TrimPattern> of(String id) {
        Identifier ID = MoreOresModLoader.id(id);
        return ResourceKey.create(Registries.TRIM_PATTERN, ID);
    }
}