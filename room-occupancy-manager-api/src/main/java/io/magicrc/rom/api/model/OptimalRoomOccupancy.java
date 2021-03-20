package io.magicrc.rom.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * Holds information about optimal occupancy of rooms and profit made.
 */
@Value.Immutable
@JsonSerialize(as = ImmutableOptimalRoomOccupancy.class)
@JsonDeserialize(as = ImmutableOptimalRoomOccupancy.class)
public interface OptimalRoomOccupancy {
    static ImmutableOptimalRoomOccupancy.Builder builder() {
        return ImmutableOptimalRoomOccupancy.builder();
    }

    /**
     * Amount of occupied rooms.
     */
    int occupied();

    /**
     * Profit made.
     */
    int profit();
}
