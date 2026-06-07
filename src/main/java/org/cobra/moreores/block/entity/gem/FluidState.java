package org.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public enum FluidState implements StringRepresentable{
    IDLE("idle"),
    FILLING("filling"),
    EMPTYING("emptying");

    private final String name;

    FluidState(String name) {
        this.name = name;
    }

    public static final Codec<FluidState> CODEC = StringRepresentable.fromValues(FluidState::values);

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
