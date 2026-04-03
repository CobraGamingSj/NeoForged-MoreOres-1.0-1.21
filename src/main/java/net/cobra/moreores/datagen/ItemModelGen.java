package net.cobra.moreores.datagen;

import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.equipment.trim.ModTrimMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class ItemModelGen extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(ModTrimMaterials.WHITE_TOPAZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(ModTrimMaterials.RUBY, 0.4F);
        trimMaterials.put(ModTrimMaterials.RADIANT, 0.4F);
        trimMaterials.put(ModTrimMaterials.PYROPE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(ModTrimMaterials.TOPAZ, 0.6F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);;
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(ModTrimMaterials.GREEN_SAPPHIRE, 0.7F);
        trimMaterials.put(ModTrimMaterials.GREEN_GARNET, 0.7F);
        trimMaterials.put(ModTrimMaterials.PERIDOT, 0.7F);
        trimMaterials.put(ModTrimMaterials.JADE, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(ModTrimMaterials.SAPPHIRE, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
        trimMaterials.put(ModTrimMaterials.BLUE_GARNET, 1.0F);
        trimMaterials.put(ModTrimMaterials.PINK_GARNET, 1.0F);
    }
    public ItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MoreOresModLoader.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE.get());

        musicDiscItem(ModItems.MUSIC_DISC_TASWELL);
        musicDiscItem(ModItems.MUSIC_DISC_DREITON);
        musicDiscItem(ModItems.MUSIC_DISC_BIOME_FEST);
        musicDiscItem(ModItems.MUSIC_DISC_ARIA_MATH);
        musicDiscItem(ModItems.MUSIC_DISC_INFINITE_AMETHYST);
        musicDiscItem(ModItems.MUSIC_DISC_FEATHERFALL);
        musicDiscItem(ModItems.MUSIC_DISC_ENDLESS);
        musicDiscItem(ModItems.MUSIC_DISC_DEEPER);
        musicDiscItem(ModItems.MUSIC_DISC_WATCHER);

        trimmedArmorItem(ModItems.RUBY_HELMET);
        trimmedArmorItem(ModItems.RUBY_CHESTPLATE);
        trimmedArmorItem(ModItems.RUBY_LEGGINGS);
        trimmedArmorItem(ModItems.RUBY_BOOTS);
    }

    private ItemModelBuilder musicDiscItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/template_music_disc")) // Use the "generated" model for flat items like discs
                .texture("layer0",
                        ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "item/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = MoreOresModLoader.MOD_ID; // Change this to your mod id

        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = "";
                if (armorItem.toString().contains("helmet")) {
                    armorType = "helmet";
                } else if (armorItem.toString().contains("chestplate")) {
                    armorType = "chestplate";
                } else if (armorItem.toString().contains("leggings")) {
                    armorType = "leggings";
                } else if (armorItem.toString().contains("boots")) {
                    armorType = "boots";
                }

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID,"item/" + item.getId().getPath()));
    }

}
