package io.magicrc.rom.optimizer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.partitioningBy;

@Component
public class RoomOccupancyOptimizer {
    public RoomOccupancyOptimizationResult optimize(List<Guest> guests, int freePremiumRooms, int freeEconomyRoom) {
        validate(guests, freePremiumRooms, freeEconomyRoom);

        var partitionedGuests = partition(guests);
        var premiumGuests = partitionedGuests.get(true);
        var economyGuests = partitionedGuests.get(false);

        var premiumRooms = new HotelRooms(freePremiumRooms);
        var economyRooms = new HotelRooms(freeEconomyRoom);

        premiumRooms.accommodateGuests(premiumGuests);
        if (economyRooms.hasNotEnoughSpaceFor(economyGuests) && premiumRooms.hasFreeRooms()) {
            economyGuests = premiumRooms.accommodateGuests(economyGuests);
        }
        economyRooms.accommodateGuests(economyGuests);

        return new RoomOccupancyOptimizationResult(premiumRooms.occupied, economyRooms.occupied, premiumRooms.profit, economyRooms.profit);
    }

    private void validate(List<Guest> guests, int freePremiumRooms, int freeEconomyRoom) {
        if (guests == null || guests.isEmpty()) {
            throw new IllegalArgumentException("Guest list can not be null or empty");
        }
        if (freePremiumRooms < 0) {
            throw new IllegalArgumentException("freePremiumRooms can not be negative value");
        }
        if (freeEconomyRoom < 0) {
            throw new IllegalArgumentException("freeEconomyRoom can not be negative value");
        }
    }

    private Map<Boolean, List<Guest>> partition(List<Guest> unpartitionedGuests) {
        var guests = new ArrayList<>(unpartitionedGuests);
        guests.sort(reverseOrder(comparingInt(Guest::amount)));
        return guests.stream().collect(partitioningBy(Guest::isPremium));
    }

    private static class HotelRooms {
        private final int amount;
        private int occupied;
        private int profit;

        private HotelRooms(int amount) {
            this.amount = amount;
        }

        private List<Guest> accommodateGuests(List<Guest> guests) {
            var accommodatedGuests = guests.subList(0, min(freeRooms(), guests.size()));
            profit += accommodatedGuests.stream().mapToInt(Guest::amount).sum();
            occupied += accommodatedGuests.size();
            return guests.subList(accommodatedGuests.size(), guests.size());
        }

        private int freeRooms() {
            return amount - occupied;
        }

        private boolean hasNotEnoughSpaceFor(List<Guest> guests) {
            return guests.size() > freeRooms() ;
        }

        private boolean hasFreeRooms() {
            return freeRooms() > 0;
        }
    }
}
