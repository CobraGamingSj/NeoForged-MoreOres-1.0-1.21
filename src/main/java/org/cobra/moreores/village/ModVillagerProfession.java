package org.cobra.moreores.village;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.data.village.TradeSets;

public class ModVillagerProfession {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, MoreOresModLoader.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, MoreOresModLoader.MOD_ID);

    public static final Holder<PoiType> JEWELLER_POI = POI_TYPES.register("jeweller_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.GEM_PURIFIER_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final Holder<VillagerProfession> JEWELLER = VILLAGER_PROFESSIONS.register("jeweller",
            () -> new VillagerProfession(Component.literal("Jeweller"), holder -> holder.value() == JEWELLER_POI.value(),
                    holder -> holder.value() == JEWELLER_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER, Int2ObjectMap.ofEntries(
                    Int2ObjectMap.entry(1, TradeSets.JEWELLER_LEVEL_1),
                    Int2ObjectMap.entry(2, TradeSets.JEWELLER_LEVEL_2),
                    Int2ObjectMap.entry(3, TradeSets.JEWELLER_LEVEL_3),
                    Int2ObjectMap.entry(4, TradeSets.JEWELLER_LEVEL_4),
                    Int2ObjectMap.entry(5, TradeSets.JEWELLER_LEVEL_5))));


    public static void register(IEventBus schoolBus) {
        POI_TYPES.register(schoolBus);
        VILLAGER_PROFESSIONS.register(schoolBus);
    }
}