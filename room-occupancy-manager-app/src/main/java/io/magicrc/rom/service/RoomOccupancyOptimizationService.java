package io.magicrc.rom.service;

import io.magicrc.rom.api.RoomOccupancyOptimization;
import io.magicrc.rom.api.message.OptimizeRoomOccupancyRequest;
import io.magicrc.rom.api.message.OptimizeRoomOccupancyResponse;
import io.magicrc.rom.api.model.OptimalRoomOccupancy;
import io.magicrc.rom.optimizer.Guest;
import io.magicrc.rom.optimizer.RoomOccupancyOptimizer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class RoomOccupancyOptimizationService implements RoomOccupancyOptimization {
    private static final List<Guest> GUESTS = Stream.of(23, 45, 155, 374, 22, 99, 100, 101, 115, 209).map(Guest::new).collect(toList());

    private final RoomOccupancyOptimizer optimizer;

    public RoomOccupancyOptimizationService(RoomOccupancyOptimizer optimizer) {
        this.optimizer = optimizer;
    }

    @Override
    public OptimizeRoomOccupancyResponse optimizeRoomOccupancy(OptimizeRoomOccupancyRequest request) {
        var result = optimizer.optimize(GUESTS, request.freePremiumRoomsAmount(), request.freeEconomyRoomsAmount());

        var economyRoomOccupancy = OptimalRoomOccupancy.builder()
                .occupied(result.economyRoomOccupancy())
                .profit(result.economyRoomProfit())
                .build();
        var premiumRoomOccupancy = OptimalRoomOccupancy.builder()
                .occupied(result.premiumRoomOccupancy())
                .profit(result.premiumRoomProfit())
                .build();

        return OptimizeRoomOccupancyResponse.builder()
                .economyRoomOccupancy(economyRoomOccupancy)
                .premiumRoomOccupancy(premiumRoomOccupancy)
                .build();
    }
}
