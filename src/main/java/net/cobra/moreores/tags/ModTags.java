package net.cobra.moreores.tags;

import net.cobra.moreores.MoreOresModLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_RUBY_TOOLS = create("incorrect_for_ruby_tool");
        public static final TagKey<Block> NEEDS_RUBY_TOOLS = create("needs_ruby_tool");

        private static TagKey<Block> createVanilla(String id) {
            return BlockTags.create(ResourceLocation.withDefaultNamespace(id));
        }

        private static TagKey<Block> create(String id) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, id));
        }
    }

    public static class Items {
        public static final TagKey<Item> IS_GEMSTONE = create("rare/is_gemstone");
        public static final TagKey<Item> IS_RARE = create("rare/is_rare");
        public static final TagKey<Item> IS_METAL = create("is_metal");

        //Armor
        public static final TagKey<Item> REPAIRS_RUBY_ARMOR = create("repairs_ruby_armor");
        public static final TagKey<Item> REPAIRS_SAPPHIRE_ARMOR = create("repairs_sapphire_armor");

        private static TagKey<Item> create(String path) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, path));
        }
    }
}
