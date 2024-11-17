package net.cobra.moreores.item.equipment.trim;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.item.ModItems;
import net.minecraft.world.item.equipment.trim.TrimPattern;

public class ModTrimPatterns {
    public static final ResourceKey<TrimPattern> GUARDIAN = of("guardian");

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        register(context, ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE.get(), GUARDIAN);
    }

    private static void register(BootstrapContext<TrimPattern> context, Item item, ResourceKey<TrimPattern> trimPatternKey) {
        TrimPattern trimpattern = new TrimPattern(trimPatternKey.location(), BuiltInRegistries.ITEM.wrapAsHolder(item), Component.translatable(Util.makeDescriptionId("trim_pattern", trimPatternKey.location())), false);
        context.register(trimPatternKey, trimpattern);
    }

    private static ResourceKey<TrimPattern> of(String id) {
        ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, id);
        return ResourceKey.create(Registries.TRIM_PATTERN, ID);
    }
}
