package io.magicrc.rom.api.message;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.magicrc.rom.api.model.OptimalRoomOccupancy;
import org.immutables.value.Value;

/**
 * Room occupancy optimization response.
 */
@Value.Immutable
@JsonSerialize(as = ImmutableOptimizeRoomOccupancyResponse.class)
@JsonDeserialize(as = ImmutableOptimizeRoomOccupancyResponse.class)
public interface OptimizeRoomOccupancyResponse {
    static ImmutableOptimizeRoomOccupancyResponse.Builder builder() {
        return ImmutableOptimizeRoomOccupancyResponse.builder();
    }

    /**
     * Optimal occupancy for premium rooms.
     */
    OptimalRoomOccupancy premiumRoomOccupancy();

    /**
     * Optimal occupancy for economy rooms.
     */
    OptimalRoomOccupancy economyRoomOccupancy();
}
