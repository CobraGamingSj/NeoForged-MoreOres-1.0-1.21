package org.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum MachineStatus implements StringRepresentable {
    STOPPED("stopped"),
    RUNNING("running"),
    PAUSED("paused");

    private final String name;

    MachineStatus(String name) {
        this.name = name;
    }

    public static final Codec<MachineStatus> CODEC = StringRepresentable.fromValues(MachineStatus::values);

    public boolean isIdle() {
        return this == STOPPED;
    }

    public boolean isRunning() {
        return this == RUNNING;
    }

    public boolean isPaused() {
        return this == PAUSED;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
