package org.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum EnergyActivity implements StringRepresentable {
    OFF("off"),
    INSERTING("inserting"),
    EXTRACTING("extracting");

    private final String name;

    EnergyActivity(String name) {
        this.name = name;
    }

    public static final Codec<EnergyActivity> CODEC = StringRepresentable.fromValues(EnergyActivity::values);

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
