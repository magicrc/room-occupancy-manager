package io.magicrc.rom.service;

import io.magicrc.rom.api.message.OptimizeRoomOccupancyRequest;
import io.magicrc.rom.api.message.OptimizeRoomOccupancyResponse;
import io.magicrc.rom.optimizer.RoomOccupancyOptimizationResult;
import io.magicrc.rom.optimizer.RoomOccupancyOptimizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RoomOccupancyOptimizationServiceTest {
    private RoomOccupancyOptimizationService service;
    private RoomOccupancyOptimizer optimizer;

    @BeforeEach
    public void setUp() {
        optimizer = mock(RoomOccupancyOptimizer.class);
        service = new RoomOccupancyOptimizationService(optimizer);
    }

    @Test
    void shouldOptimizeRoomOccupancyUsingRoomOccupancyOptimizer() {
        var request = OptimizeRoomOccupancyRequest.builder().freePremiumRoomsAmount(1).freeEconomyRoomsAmount(2).build();
        var result = new RoomOccupancyOptimizationResult(3, 4, 5, 6);

        given(optimizer.optimize(any(), eq(1), eq(2))).willReturn(result);

        OptimizeRoomOccupancyResponse response = service.optimizeRoomOccupancy(request);

        assertThat(response.premiumRoomOccupancy().occupied()).isEqualTo(3);
        assertThat(response.premiumRoomOccupancy().profit()).isEqualTo(5);
        assertThat(response.economyRoomOccupancy().occupied()).isEqualTo(4);
        assertThat(response.economyRoomOccupancy().profit()).isEqualTo(6);
    }
}
