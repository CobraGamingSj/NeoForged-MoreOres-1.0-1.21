package net.cobra.moreores;

import com.mojang.logging.LogUtils;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.entity.ModBlockEntityType;
import net.cobra.moreores.item.ModCreativeModeTabs;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.sound.ModSoundEvents;
import net.cobra.moreores.world.inventory.GemPolisherScreen;
import net.cobra.moreores.world.inventory.ModMenuType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import java.util.Locale;

@Mod(MoreOresModLoader.MOD_ID)
public class MoreOresModLoader {
    public static final String MOD_ID = "moreores";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MoreOresModLoader(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModSoundEvents.register(modEventBus);
        ModBlockEntityType.register(modEventBus);
        ModMenuType.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            ItemStack taswell = new ItemStack(ModItems.MUSIC_DISC_TASWELL.get());
            ItemStack dreiton = new ItemStack(ModItems.MUSIC_DISC_DREITON.get());
            ItemStack biome_fest = new ItemStack(ModItems.MUSIC_DISC_BIOME_FEST.get());
            ItemStack aria_math = new ItemStack(ModItems.MUSIC_DISC_ARIA_MATH.get());
            ItemStack infinite_amethyst = new ItemStack(ModItems.MUSIC_DISC_INFINITE_AMETHYST.get());
            ItemStack featherfall = new ItemStack(ModItems.MUSIC_DISC_FEATHERFALL.get());
            ItemStack endless = new ItemStack(ModItems.MUSIC_DISC_ENDLESS.get());
            ItemStack deeper = new ItemStack(ModItems.MUSIC_DISC_DEEPER.get());
            ItemStack watcher = new ItemStack(ModItems.MUSIC_DISC_WATCHER.get());

            event.insertAfter(new ItemStack(Items.MUSIC_DISC_OTHERSIDE), taswell, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(taswell, dreiton, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(dreiton, biome_fest, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(biome_fest, aria_math, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(aria_math, infinite_amethyst, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(infinite_amethyst, featherfall, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(featherfall, endless, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(endless, deeper, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(deeper, watcher, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            ItemStack netherite= new ItemStack(Items.NETHERITE_INGOT);
            ItemStack energy_ingot= new ItemStack(ModItems.ENERGY_INGOT.get());

            ItemStack diamond = new ItemStack(Items.DIAMOND);
            ItemStack ruby = new ItemStack(ModItems.RUBY.get());
            ItemStack radiant = new ItemStack(ModItems.RADIANT.get());
            ItemStack sapphire = new ItemStack(ModItems.SAPPHIRE.get());
            ItemStack green_sapphire = new ItemStack(ModItems.GREEN_SAPPHIRE.get());
            ItemStack blue_garnet = new ItemStack(ModItems.BLUE_GARNET.get());
            ItemStack pink_garnet = new ItemStack(ModItems.PINK_GARNET.get());
            ItemStack green_garnet = new ItemStack(ModItems.GREEN_GARNET.get());
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
            event.insertAfter(green_garnet, topaz, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(topaz, white_topaz, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(white_topaz, peridot, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(peridot, jade, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(jade, pyrope, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(netherite, energy_ingot, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            ItemStack netherite_block = new ItemStack(Blocks.NETHERITE_BLOCK);
            ItemStack ruby_block = new ItemStack(ModBlocks.RUBY_BLOCK);
            ItemStack energy_block = new ItemStack(ModBlocks.ENERGY_BLOCK);
            event.insertAfter(netherite_block, ruby_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(netherite_block, energy_block, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
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
//
        }

        @SubscribeEvent
        public static void regBlockEntityScreen(RegisterMenuScreensEvent event) {
            event.register(ModMenuType.GEM_POLISHER_MENU.get(), GemPolisherScreen::new);
        }
    }
}