package org.cobra.moreores.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RewardState extends SavedData {
    private final Set<UUID> playerClaimedRewards = new HashSet<>();
    public static final Codec<RewardState> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.list(Codec.STRING).fieldOf("players").forGetter(state ->
                            state.playerClaimedRewards.stream().map(UUID::toString).toList()
                    )
            ).apply(instance, list -> {
                RewardState state = new RewardState();
                for (String s : list) {
                    state.playerClaimedRewards.add(UUID.fromString(s));
                }
                return state;
            })
    );

    public static final SavedDataType<RewardState> TYPE =
            new SavedDataType<>(
                    "moreores_birthday_rewards",
                    RewardState::new,
                    CODEC,
                    null // Not required
            );

    public boolean hasClaimed(UUID uuid) {
        return playerClaimedRewards.contains(uuid);
    }

    public void setClaimed(UUID uuid) {
        playerClaimedRewards.add(uuid);
        setDirty();
    }

    public static RewardState get(ServerLevel world) {
       return world.getDataStorage().computeIfAbsent(TYPE);
    }
}
