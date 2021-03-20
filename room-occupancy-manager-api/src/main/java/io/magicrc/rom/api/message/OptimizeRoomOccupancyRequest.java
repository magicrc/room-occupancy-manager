package io.magicrc.rom.api.message;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * Room occupancy optimization request.
 */
@Value.Immutable
@JsonSerialize(as = ImmutableOptimizeRoomOccupancyRequest.class)
@JsonDeserialize(as = ImmutableOptimizeRoomOccupancyRequest.class)
public interface OptimizeRoomOccupancyRequest {
    static ImmutableOptimizeRoomOccupancyRequest.Builder builder() {
        return ImmutableOptimizeRoomOccupancyRequest.builder();
    }

    /**
     * Amount of free economy rooms to take into consideration for optimization process.
     */
    int freeEconomyRoomsAmount();

    /**
     * Amount of premium economy rooms to take into consideration for optimization process.
     */
    int freePremiumRoomsAmount();
}
