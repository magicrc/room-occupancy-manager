package io.magicrc.rom.optimizer;

import org.assertj.core.api.AbstractAssert;

public class RoomOccupancyOptimizationResultAsert extends AbstractAssert<RoomOccupancyOptimizationResultAsert, RoomOccupancyOptimizationResult> {
    public RoomOccupancyOptimizationResultAsert(RoomOccupancyOptimizationResult roomOccupancyOptimizationResult) {
        super(roomOccupancyOptimizationResult, RoomOccupancyOptimizationResultAsert.class);
    }

    public static RoomOccupancyOptimizationResultAsert assertThat(RoomOccupancyOptimizationResult actual) {
        return new RoomOccupancyOptimizationResultAsert(actual);
    }

    public void isEqualTo(RoomOccupancyOptimizationResult expected) {
        isNotNull();
        if (actual.economyRoomOccupancy() != expected.economyRoomOccupancy()) {
            failWithActualExpectedAndMessage(actual.economyRoomOccupancy(), expected.economyRoomOccupancy(), "Invalid [economyRoomOccupancy]");
        }

        if (actual.economyRoomProfit() != expected.economyRoomProfit()) {
            failWithActualExpectedAndMessage(actual.economyRoomProfit(), expected.economyRoomProfit(), "Invalid [economyRoomProfit]");
        }

        if (actual.premiumRoomOccupancy() != expected.premiumRoomOccupancy()) {
            failWithActualExpectedAndMessage(actual.premiumRoomOccupancy(), expected.premiumRoomOccupancy(), "Invalid [premiumRoomOccupancy]");
        }

        if (actual.premiumRoomProfit() != expected.premiumRoomProfit()) {
            failWithActualExpectedAndMessage(actual.premiumRoomProfit(), expected.premiumRoomProfit(), "Invalid [premiumRoomProfit]");
        }
    }
}
