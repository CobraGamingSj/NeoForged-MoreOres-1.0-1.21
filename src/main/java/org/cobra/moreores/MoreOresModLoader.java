package org.cobra.moreores;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.datafix.fixes.StriderGravityFix;
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

        ModVillagerProfession.register(schoolBus);

        EnchantmentEffects.register(schoolBus);
        
        schoolBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            ItemStack diamond = new ItemStack(Items.DIAMOND);
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

            event.insertAfter(diamond, ruby, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
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
            ItemStack ruby_block = new ItemStack(ModBlocks.RUBY_BLOCK);
            event.insertAfter(netherite_block, ruby_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            ItemStack deepslateDiamondOre = new ItemStack(Blocks.DEEPSLATE_DIAMOND_ORE);
            ItemStack rubyOre = new ItemStack(ModBlocks.RUBY_ORE);
            ItemStack deepslateRubyOre = new ItemStack(ModBlocks.DEEPSLATE_RUBY_ORE);

            event.insertAfter(deepslateDiamondOre, rubyOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(rubyOre, deepslateRubyOre, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
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
