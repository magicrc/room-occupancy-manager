package io.magicrc.rom.optimizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.magicrc.rom.optimizer.RoomOccupancyOptimizationResultAsert.assertThat;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoomOccupancyOptimizerTest {
    private RoomOccupancyOptimizer optimizer;
    private List<Guest> guests;

    @BeforeEach
    public void setUp() {
        optimizer = new RoomOccupancyOptimizer();
        guests = Stream.of(23, 45, 155, 374, 22, 99, 100, 101, 115, 209).map(Guest::new).collect(toList());
    }

    @ParameterizedTest
    @MethodSource("expectedRoomOccupancyOptimizationResult")
    void shouldCorrectlyOptimizeRoomOccupancy(int freePremiumRooms, int freeEconomyRooms, RoomOccupancyOptimizationResult expectedResult) {
        var result = optimizer.optimize(guests, freePremiumRooms, freeEconomyRooms);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGuestListIsNull() {
        assertThatThrownBy(() -> optimizer.optimize(null, 1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Guest list can not be null or empty");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGuestListIsEmpty() {
        assertThatThrownBy(() -> optimizer.optimize(new ArrayList<>(), 1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Guest list can not be null or empty");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFreePremiumRoomsIsNegative() {
        assertThatThrownBy(() -> optimizer.optimize(guests, -1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("freePremiumRooms can not be negative value");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFreeEconomyRoomsIsNegative() {
        assertThatThrownBy(() -> optimizer.optimize(guests, 1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("freeEconomyRoom can not be negative value");
    }

    private static Stream<Arguments> expectedRoomOccupancyOptimizationResult() {
        return Stream.of(
                Arguments.of(3, 3, new RoomOccupancyOptimizationResult(3, 3, 738, 167)),
                Arguments.of(7, 5, new RoomOccupancyOptimizationResult(6, 4, 1054, 189)),
                Arguments.of(2, 7, new RoomOccupancyOptimizationResult(2, 4, 583, 189)),
                Arguments.of(7, 1, new RoomOccupancyOptimizationResult(7, 1, 1153, 45)),
                Arguments.of(0, 0, new RoomOccupancyOptimizationResult(0, 0, 0, 0))
        );
    }
}
