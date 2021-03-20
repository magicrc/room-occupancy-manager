package io.magicrc.rom.optimizer;

public class RoomOccupancyOptimizationResult {
    private final int premiumRoomOccupancy;
    private final int economyRoomOccupancy;
    private final int premiumRoomProfit;
    private final int economyRoomProfit;

    public RoomOccupancyOptimizationResult(
            int premiumRoomOccupancy,
            int economyRoomOccupancy,
            int premiumRoomProfit,
            int economyRoomProfit
    ) {
        this.premiumRoomOccupancy = premiumRoomOccupancy;
        this.economyRoomOccupancy = economyRoomOccupancy;
        this.premiumRoomProfit = premiumRoomProfit;
        this.economyRoomProfit = economyRoomProfit;
    }

    public int premiumRoomOccupancy() {
        return premiumRoomOccupancy;
    }

    public int economyRoomOccupancy() {
        return economyRoomOccupancy;
    }

    public int premiumRoomProfit() {
        return premiumRoomProfit;
    }

    public int economyRoomProfit() {
        return economyRoomProfit;
    }
}
