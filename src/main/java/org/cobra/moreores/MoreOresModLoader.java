package org.cobra.moreores;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.client.menu.GemCrystallizerScreen;
import org.cobra.moreores.block.client.menu.GemPurifierScreen;
import org.cobra.moreores.block.client.menu.ModMenuType;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.enchantment.entity.effect.EnchantmentEffects;
import org.cobra.moreores.item.ModCreativeModeTabs;
import org.cobra.moreores.item.ModItems;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.cobra.moreores.recipe.ModRecipeSerializer;
import org.cobra.moreores.recipe.ModRecipeType;
import org.cobra.moreores.recipe.book.ModRecipeBookCategories;
import org.cobra.moreores.recipe.display.ModRecipeDisplays;
import org.cobra.moreores.village.ModVillagerProfession;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(MoreOresModLoader.MOD_ID)
public class MoreOresModLoader {
    public static final String MOD_ID = "moreores";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Identifier id(String id) {
        return Identifier.fromNamespaceAndPath(MOD_ID, id);
    }

    public static String formatName(String path) {
        String[] words = path.split("_");
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < words.length; i++) {
            String word = words[i];

            builder.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1));

            if(i < words.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
    
    public static ResourceKey<Recipe<?>> recipeKey(String id) {
        return ResourceKey.create(Registries.RECIPE, id(id));
    }
    
    public MoreOresModLoader(IEventBus schoolBus, ModContainer modContainer) {
        schoolBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModItems.register(schoolBus);

        ModBlocks.register(schoolBus);

        ModCreativeModeTabs.register(schoolBus);

        ModBlockEntityType.register(schoolBus);

        ModMenuType.register(schoolBus);

        ModRecipeSerializer.register(schoolBus);
        ModRecipeType.register(schoolBus);
        ModRecipeBookCategories.register(schoolBus);
        ModRecipeDisplays.register(schoolBus);

        ModVillagerProfession.register(schoolBus);

        EnchantmentEffects.register(schoolBus);
        
        schoolBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, MoreOresConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            ItemStack energyIngot = new ItemStack(ModItems.ENERGY_INGOT.get());
            ItemStack netIngot = new ItemStack(Items.NETHERITE_INGOT);
            ItemStack ruby = new ItemStack(ModItems.RUBY.get());
            ItemStack radiant = new ItemStack(ModItems.RADIANT.get());
            ItemStack sapphire = new ItemStack(ModItems.SAPPHIRE.get());
            ItemStack green_sapphire = new ItemStack(ModItems.GREEN_SAPPHIRE.get());
            ItemStack blue_garnet = new ItemStack(ModItems.BLUE_GARNET.get());
            ItemStack pink_garnet = new ItemStack(ModItems.PINK_GARNET.get());
            ItemStack green_garnet = new ItemStack(ModItems.GREEN_GARNET.get());
            ItemStack kyawthuite = new ItemStack(ModItems.KYAWTHUITE.get());
            ItemStack topaz = new ItemStack(ModItems.TOPAZ.get());
            ItemStack white_topaz = new ItemStack(ModItems.WHITE_TOPAZ.get());
            ItemStack peridot = new ItemStack(ModItems.PERIDOT.get());
            ItemStack jade = new ItemStack(ModItems.JADE.get());
            ItemStack pyrope = new ItemStack(ModItems.PYROPE.get());

            event.insertBefore(netIngot, energyIngot, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(netIngot, ruby, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ruby, radiant, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(radiant, sapphire, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(sapphire, green_sapphire, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(green_sapphire, blue_garnet, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(blue_garnet, pink_garnet, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(pink_garnet, green_garnet, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(green_garnet, kyawthuite, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(kyawthuite, topaz, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(topaz, white_topaz, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(white_topaz, peridot, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(peridot, jade, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(jade, pyrope, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            ItemStack netherite_block = new ItemStack(Blocks.NETHERITE_BLOCK);
            ItemStack energy_block = new ItemStack(ModBlocks.ENERGY_BLOCK);
            ItemStack ruby_block = new ItemStack(ModBlocks.RUBY_BLOCK);
            ItemStack sapphire_block = new ItemStack(ModBlocks.SAPPHIRE_BLOCK);
            ItemStack greenSapphire_block = new ItemStack(ModBlocks.GREEN_SAPPHIRE_BLOCK);
            ItemStack blueGarnet_block = new ItemStack(ModBlocks.BLUE_GARNET_BLOCK);
            ItemStack pinkGarnet_block = new ItemStack(ModBlocks.PINK_GARNET_BLOCK);
            ItemStack greenGarnet_block = new ItemStack(ModBlocks.GREEN_GARNET_BLOCK);
            ItemStack kyawthuite_block = new ItemStack(ModBlocks.KYAWTHUITE_BLOCK);
            ItemStack topaz_block = new ItemStack(ModBlocks.TOPAZ_BLOCK);
            ItemStack whiteTopaz_block = new ItemStack(ModBlocks.WHITE_TOPAZ_BLOCK);
            ItemStack peridot_block = new ItemStack(ModBlocks.PERIDOT_BLOCK);
            ItemStack jade_block = new ItemStack(ModBlocks.JADE_BLOCK);
            ItemStack pyrope_block = new ItemStack(ModBlocks.PYROPE_BLOCK);

            ItemStack crimsonGarnet_block = new ItemStack(ModBlocks.CRIMSON_GARNET_BLOCK);
            ItemStack crystallite_block = new ItemStack(ModBlocks.CRYSTALLITE_BLOCK);
            ItemStack radiantAmethyst_block = new ItemStack(ModBlocks.RADIANT_AMETHYST_BLOCK);
            ItemStack moonstone_block = new ItemStack(ModBlocks.MOONSTONE_BLOCK);
            ItemStack limestone_block = new ItemStack(ModBlocks.LIMESTONE_BLOCK);
            ItemStack quartsidian_block = new ItemStack(ModBlocks.QUARTSIDIAN_BLOCK);
            ItemStack alexandrite_block = new ItemStack(ModBlocks.ALEXANDRITE_BLOCK);
            ItemStack orangeZircon_block = new ItemStack(ModBlocks.ORANGE_ZIRCON_BLOCK);
            ItemStack opal_block = new ItemStack(ModBlocks.OPAL_BLOCK);
            ItemStack grandidierite_block = new ItemStack(ModBlocks.GRANDIDIERITE_BLOCK);
            ItemStack redBeryl_block = new ItemStack(ModBlocks.RED_BERYL_BLOCK);
            ItemStack kashmirSapphire_block = new ItemStack(ModBlocks.KASHMIR_SAPPHIRE_BLOCK);

            event.insertBefore(netherite_block, energy_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(netherite_block, ruby_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ruby_block, sapphire_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(sapphire_block, greenSapphire_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(greenSapphire_block, blueGarnet_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(blueGarnet_block, pinkGarnet_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(pinkGarnet_block, greenGarnet_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(greenGarnet_block, kyawthuite_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(kyawthuite_block, topaz_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(topaz_block, whiteTopaz_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(whiteTopaz_block, peridot_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(peridot_block, jade_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(jade_block, pyrope_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(pyrope_block, crimsonGarnet_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(crimsonGarnet_block, crystallite_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(crystallite_block, radiantAmethyst_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(radiantAmethyst_block, moonstone_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(moonstone_block, limestone_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(limestone_block, quartsidian_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(quartsidian_block, alexandrite_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(alexandrite_block, orangeZircon_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(orangeZircon_block, opal_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(opal_block, grandidierite_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(grandidierite_block, redBeryl_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(redBeryl_block, kashmirSapphire_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            ItemStack deepslateDiamondOre = new ItemStack(Blocks.DEEPSLATE_DIAMOND_ORE);
            ItemStack rubyOre = new ItemStack(ModBlocks.RUBY_ORE);
            ItemStack deepslateRubyOre = new ItemStack(ModBlocks.DEEPSLATE_RUBY_ORE);
            ItemStack sapphireOre = new ItemStack(ModBlocks.SAPPHIRE_ORE);
            ItemStack deepslateSapphireOre = new ItemStack(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
            ItemStack greenSapphireOre = new ItemStack(ModBlocks.GREEN_SAPPHIRE_ORE);
            ItemStack deepslateGreenSapphireOre = new ItemStack(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE);
            ItemStack blueGarnetOre = new ItemStack(ModBlocks.BLUE_GARNET_ORE);
            ItemStack deepslateBlueGarnetOre = new ItemStack(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE);
            ItemStack pinkGarnetOre = new ItemStack(ModBlocks.PINK_GARNET_ORE);
            ItemStack deepslatePinkGarnetOre = new ItemStack(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
            ItemStack greenGarnetOre = new ItemStack(ModBlocks.GREEN_GARNET_ORE);
            ItemStack deepslateGreenGarnetOre = new ItemStack(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE);
            ItemStack kyawthuiteOre = new ItemStack(ModBlocks.KYAWTHUITE_ORE);
            ItemStack deepslateKyawthuiteOre = new ItemStack(ModBlocks.DEEPSLATE_KYAWTHUITE_ORE);
            ItemStack topazOre = new ItemStack(ModBlocks.TOPAZ_ORE);
            ItemStack deepslateTopazOre = new ItemStack(ModBlocks.DEEPSLATE_TOPAZ_ORE);
            ItemStack whiteTopazOre = new ItemStack(ModBlocks.WHITE_TOPAZ_ORE);
            ItemStack deepslateWhiteTopazOre = new ItemStack(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE);
            ItemStack peridotOre = new ItemStack(ModBlocks.PERIDOT_ORE);
            ItemStack deepslatePeridotOre = new ItemStack(ModBlocks.DEEPSLATE_PERIDOT_ORE);
            ItemStack jadeOre = new ItemStack(ModBlocks.JADE_ORE);
            ItemStack deepslateJadeOre = new ItemStack(ModBlocks.DEEPSLATE_JADE_ORE);
            ItemStack pyropeOre = new ItemStack(ModBlocks.PYROPE_ORE);
            ItemStack deepslatePyropeOre = new ItemStack(ModBlocks.DEEPSLATE_PYROPE_ORE);
            ItemStack eclipseOre = new ItemStack(ModBlocks.ECLIPSE_GEM_ORE);

            event.insertAfter(deepslateDiamondOre, rubyOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(rubyOre, deepslateRubyOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateRubyOre, sapphireOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(sapphireOre, deepslateSapphireOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateSapphireOre, greenSapphireOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(greenSapphireOre, deepslateGreenSapphireOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateGreenSapphireOre, blueGarnetOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(blueGarnetOre, deepslateBlueGarnetOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateBlueGarnetOre, pinkGarnetOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(pinkGarnetOre, deepslatePinkGarnetOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslatePinkGarnetOre, greenGarnetOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(greenGarnetOre, deepslateGreenGarnetOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateGreenGarnetOre, kyawthuiteOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(kyawthuiteOre, deepslateKyawthuiteOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateKyawthuiteOre, topazOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(topazOre, deepslateTopazOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateTopazOre, whiteTopazOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(whiteTopazOre, deepslateWhiteTopazOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateWhiteTopazOre, peridotOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(peridotOre, deepslatePeridotOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslatePeridotOre, jadeOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(jadeOre, deepslateJadeOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslateJadeOre, pyropeOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(pyropeOre, deepslatePyropeOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deepslatePyropeOre, eclipseOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }


        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            ItemStack netSword = new ItemStack(Items.NETHERITE_SWORD);
            ItemStack rubySword = new ItemStack(ModItems.RUBY_SWORD.get());
            ItemStack sapphireSword = new ItemStack(ModItems.SAPPHIRE_SWORD.get());
            ItemStack radSword = new ItemStack(ModItems.RADIANT_SWORD.get());
            ItemStack netSpear = new ItemStack(Items.NETHERITE_SPEAR);
            ItemStack rubySpear = new ItemStack(ModItems.RUBY_SPEAR.get());
            ItemStack sapSpear = new ItemStack(ModItems.SAPPHIRE_SPEAR.get());
            ItemStack netBoots = new ItemStack(Items.NETHERITE_BOOTS);
            ItemStack rubyHelm = new ItemStack(ModItems.RUBY_HELMET.get());
            ItemStack rubyPlate = new ItemStack(ModItems.RUBY_CHESTPLATE.get());
            ItemStack rubyLgg = new ItemStack(ModItems.RUBY_LEGGINGS.get());
            ItemStack rubyBoots = new ItemStack(ModItems.RUBY_BOOTS.get());
            ItemStack sapHelm = new ItemStack(ModItems.SAPPHIRE_HELMET.get());
            ItemStack sapPlate = new ItemStack(ModItems.SAPPHIRE_CHESTPLATE.get());
            ItemStack sapLgg = new ItemStack(ModItems.SAPPHIRE_LEGGINGS.get());
            ItemStack sapBoots = new ItemStack(ModItems.SAPPHIRE_BOOTS.get());
            ItemStack radHelm = new ItemStack(ModItems.RADIANT_HELMET.get());
            ItemStack radPlate = new ItemStack(ModItems.RADIANT_CHESTPLATE.get());
            ItemStack radLgg = new ItemStack(ModItems.RADIANT_LEGGINGS.get());
            ItemStack radBoots = new ItemStack(ModItems.RADIANT_BOOTS.get());
            
            event.insertAfter(netSword, rubySword, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(rubySword, sapphireSword, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(sapphireSword, radSword, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(netSpear, rubySpear, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(rubySpear, sapSpear, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(netBoots, rubyHelm, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(rubyHelm, rubyPlate, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(rubyPlate, rubyLgg, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(rubyLgg, rubyBoots, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(rubyBoots, sapHelm, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(sapHelm, sapPlate, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(sapPlate, sapLgg, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(sapLgg, sapBoots, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(sapBoots, radHelm, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(radHelm, radPlate, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(radPlate, radLgg, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(radLgg, radBoots, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
           
        }
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuType.GEM_PURIFIER.get(), GemPurifierScreen::new);
            event.register(ModMenuType.GEM_CRYSTALLIZER.get(), GemCrystallizerScreen::new);
        }
    }
}
