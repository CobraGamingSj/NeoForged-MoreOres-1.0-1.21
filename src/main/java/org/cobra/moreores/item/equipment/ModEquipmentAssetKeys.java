package org.cobra.moreores.item.equipment;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.cobra.moreores.MoreOresModLoader;

public interface ModEquipmentAssetKeys {
    ResourceKey<EquipmentAsset> RUBY = register("ruby");
    ResourceKey<EquipmentAsset> SAPPHIRE = register("sapphire");
    ResourceKey<EquipmentAsset> RADIANT = register("radiant");

    static ResourceKey<EquipmentAsset> register(String id) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, MoreOresModLoader.id(id));
    }
}
