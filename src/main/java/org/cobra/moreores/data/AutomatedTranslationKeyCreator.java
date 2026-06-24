package org.cobra.moreores.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.GemItem;
import org.cobra.moreores.item.ModItems;

public class AutomatedTranslationKeyCreator extends LanguageProvider {
    public AutomatedTranslationKeyCreator(PackOutput output) {
        super(output, MoreOresModLoader.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("gui.button.gp.start", "Start");
        add("gui.button.gp.pause", "Pause");
        add("gui.button.gp.resume", "Resume");
        add("gui.button.gp.stop", "Stop");
        add("itemGroup.moreores.gemstones", "Gemstones");
        add("upgrade.moreores.ruby_upgrade", "Smithing Template");
        add("upgrade.moreores.radiant_upgrade", "Smithing Template");
        add("item.moreores.smithing_template.applies_to", "Applies to:");
        add("item.moreores.smithing_template.ruby_upgrade.applies_to", "Netherite Equipment");
        add("item.moreores.smithing_template.ruby_upgrade.ingredients", "Ruby");
        add("item.moreores.smithing_template.radiant_upgrade.applies_to", "Sapphire Equipment");
        add("item.moreores.smithing_template.radiant_upgrade.ingredients", "Sapphire");
        add("item.moreores.smithing_template.ingredients", "Ingredients:");
        add("advancement.moreores.gems",  "Is that a gem?");
        add("advancement.moreores.gems.desc",  "Collect a gemstone");
        add("advancement.moreores.ruby_armor",  "Cover me in Ruby");
        add("advancement.moreores.ruby_armor.desc",  "Equip a Ruby Armor");
        add("advancement.moreores.radiant_sword",  "Overpowered!");
        add("advancement.moreores.radiant_sword.desc",  "Get a Radiant Sword");
        add("advancement.moreores.gems_all",  "The gems?");
        add("advancement.moreores.gems_all.desc",  "Collect every gemstone");
        add("enchantment.moreores.thunder_striker",  "Thunder Striker");
        add("entity.minecraft.villager.jeweller",  "Jeweller");
        add("trim_pattern.moreores.guardian",  "Guardian Armor Trim");
        add("entity.moreores.gem_arrow",  "Gem Arrow");
        
        for (Item item : BuiltInRegistries.ITEM) {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            
            if (id.getNamespace().equals(MoreOresModLoader.MOD_ID)) {

                if(item == ModItems.RADIANT.get() || item == ModItems.RADIANT_DUST.get() || item == ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get() || item == ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE.get() ||
                        item == ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get() || item == ModBlocks.GEM_CRYSTALLIZER_BLOCK.get().asItem() || item == ModBlocks.GEM_PURIFIER_BLOCK.get().asItem()) {
                    continue;
                }

                if(item instanceof GemItem gemItem) {
                    String gemIdentifiedPath = BuiltInRegistries.ITEM.getKey(gemItem).getPath();
                    add(gemItem, MoreOresModLoader.formatName(gemIdentifiedPath));
                    add("trim_material." + MoreOresModLoader.MOD_ID + "." + gemIdentifiedPath, MoreOresModLoader.formatName(gemIdentifiedPath) + " Material");
                    continue;
                }

                String path = id.getPath();

                String translatedName = MoreOresModLoader.formatName(path);

                add(item, translatedName);
            }
        }

        add(ModItems.RADIANT.get(), "§1Radiant§r");
        add(ModItems.RADIANT_DUST.get(), "§2Radiant Dust§r");
        add(ModBlocks.GEM_PURIFIER_BLOCK.get(), "Gem Purifier");
        add(ModBlocks.GEM_CRYSTALLIZER_BLOCK.get(), "Gem Crystallizer");
        add(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), "Ruby Upgrade");
        add(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), "Radiant Upgrade");
        add(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE.get(), "Guardian Armor Trim");
    }
}
