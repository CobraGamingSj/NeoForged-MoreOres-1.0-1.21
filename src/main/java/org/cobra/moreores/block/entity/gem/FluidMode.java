package org.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum FluidMode implements StringRepresentable{
    NONE("off"),
    FILLING("filling"),
    EMPTYING("emptying");

    private final String name;

    FluidMode(String name) {
        this.name = name;
    }

    public static final Codec<FluidMode> CODEC = StringRepresentable.fromValues(FluidMode::values);

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
