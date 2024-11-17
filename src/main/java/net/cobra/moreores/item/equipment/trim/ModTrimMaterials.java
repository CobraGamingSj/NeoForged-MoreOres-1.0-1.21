package net.cobra.moreores.item.equipment.trim;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.item.ModItems;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.Map;

public class ModTrimMaterials {
    public static final ResourceKey<TrimMaterial> RUBY = of("ruby");
    public static final ResourceKey<TrimMaterial> RADIANT = of("radiant");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = of("sapphire");
    public static final ResourceKey<TrimMaterial> GREEN_SAPPHIRE = of("green_sapphire");
    public static final ResourceKey<TrimMaterial> BLUE_GARNET = of("blue_garnet");
    public static final ResourceKey<TrimMaterial> PINK_GARNET = of("pink_garnet");
    public static final ResourceKey<TrimMaterial> GREEN_GARNET = of("green_garnet");
    public static final ResourceKey<TrimMaterial> TOPAZ = of("topaz");
    public static final ResourceKey<TrimMaterial> WHITE_TOPAZ = of("white_topaz");
    public static final ResourceKey<TrimMaterial> PERIDOT = of("peridot");
    public static final ResourceKey<TrimMaterial> JADE = of("jade");
    public static final ResourceKey<TrimMaterial> PYROPE = of("pyrope");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, RUBY, ModItems.RUBY.get(), Style.EMPTY.withColor(TextColor.fromRgb(16711680)), 0.51f);
        register(context, RADIANT, ModItems.RADIANT.get(), Style.EMPTY.withColor(TextColor.fromRgb(11337728)), 0.52f);
        register(context, SAPPHIRE, ModItems.SAPPHIRE.get(), Style.EMPTY.withColor(TextColor.fromRgb(262399)), 0.53f);
        register(context, GREEN_SAPPHIRE, ModItems.GREEN_SAPPHIRE.get(), Style.EMPTY.withColor(TextColor.fromRgb(47150)), 0.54f);
        register(context, BLUE_GARNET, ModItems.BLUE_GARNET.get(), Style.EMPTY.withColor(TextColor.fromRgb(2490598)), 0.55f);
        register(context, PINK_GARNET, ModItems.PINK_GARNET.get(), Style.EMPTY.withColor(TextColor.fromRgb(15728836)), 0.56f);
        register(context, GREEN_GARNET, ModItems.GREEN_GARNET.get(), Style.EMPTY.withColor(TextColor.fromRgb(2480896)), 0.57f);
        register(context, TOPAZ, ModItems.TOPAZ.get(), Style.EMPTY.withColor(TextColor.fromRgb(13384960)), 0.58f);
        register(context, WHITE_TOPAZ, ModItems.WHITE_TOPAZ.get(), Style.EMPTY.withColor(TextColor.fromRgb(15794162)), 0.59f);
        register(context, PERIDOT, ModItems.PERIDOT.get(), Style.EMPTY.withColor(TextColor.fromRgb(65314)), 0.61f);
        register(context, JADE, ModItems.JADE.get(), Style.EMPTY.withColor(TextColor.fromRgb(10420139)), 0.62f);
        register(context, PYROPE, ModItems.PYROPE.get(), Style.EMPTY.withColor(TextColor.fromRgb(14680064)), 0.63f);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item, Style style, float itemModelIndex) {
        TrimMaterial trimMaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of());
        context.register(trimKey, trimMaterial);
    }

    private static ResourceKey<TrimMaterial> of(String id) {
        ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, id);
        return ResourceKey.create(Registries.TRIM_MATERIAL, ID);
    }
}
