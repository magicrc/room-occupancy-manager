package io.magicrc.rom.api;

import io.magicrc.rom.api.message.OptimizeRoomOccupancyRequest;
import io.magicrc.rom.api.message.OptimizeRoomOccupancyResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("/v1.0/room-occupancy-optimization/")
public interface RoomOccupancyOptimization {
    /**
     * Optimize premium and economy rooms occupancy using constant (internal) list of guests payments.
     */
    @POST
    @Path("/optimize")
    OptimizeRoomOccupancyResponse optimizeRoomOccupancy(OptimizeRoomOccupancyRequest request);
}
