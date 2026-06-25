package org.cobra.moreores.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.tags.TagEntry;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.village.ModVillagerProfession;

import java.util.concurrent.CompletableFuture;

public class PointOfInterestTypeTagGen extends PoiTypeTagsProvider {
    public PointOfInterestTypeTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreOresModLoader.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .add(TagEntry.element(ModVillagerProfession.JEWELLER_POI.unwrapKey().get().identifier()));
    }
}
