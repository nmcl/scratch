package com.example.gamelife3d.resource;

import com.example.gamelife3d.model.Cell;
import com.example.gamelife3d.model.World3D;
import com.example.gamelife3d.service.GameOfLifeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/game-of-life")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class GameOfLifeResource {

    @Inject
    GameOfLifeService gameOfLifeService;

    @POST
    @Path("/world/{id}")
    public Response createWorld(@PathParam("id") String id, 
                               @QueryParam("width") @DefaultValue("50") int width,
                               @QueryParam("height") @DefaultValue("50") int height,
                               @QueryParam("depth") @DefaultValue("50") int depth) {
        try {
            World3D world = gameOfLifeService.createWorld(id, width, height, depth);
            return Response.ok(world).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error creating world: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/world/{id}")
    public Response getWorld(@PathParam("id") String id) {
        World3D world = gameOfLifeService.getWorld(id);
        if (world == null) {
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("World not found").build();
        }
        return Response.ok(world).build();
    }

    @POST
    @Path("/world/{id}/start")
    public Response startSimulation(@PathParam("id") String id,
                                   @QueryParam("delay") @DefaultValue("100") int delay) {
        try {
            gameOfLifeService.startSimulation(id, delay);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error starting simulation: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/world/{id}/stop")
    public Response stopSimulation(@PathParam("id") String id) {
        try {
            gameOfLifeService.stopSimulation(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error stopping simulation: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/world/{id}/randomize")
    public Response randomizeWorld(@PathParam("id") String id,
                                  @QueryParam("density") @DefaultValue("30") int density) {
        try {
            gameOfLifeService.randomizeWorld(id, density);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error randomizing world: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/world/{id}/clear")
    public Response clearWorld(@PathParam("id") String id) {
        try {
            gameOfLifeService.clearWorld(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error clearing world: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/world/{id}/cell/{x}/{y}/{z}")
    public Response setCellAlive(@PathParam("id") String id,
                                @PathParam("x") int x,
                                @PathParam("y") int y,
                                @PathParam("z") int z,
                                @QueryParam("alive") @DefaultValue("true") boolean alive) {
        try {
            if (alive) {
                gameOfLifeService.setCellAlive(id, x, y, z);
            } else {
                gameOfLifeService.setCellDead(id, x, y, z);
            }
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error setting cell state: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/world/{id}/alive-cells")
    public Response getAliveCells(@PathParam("id") String id) {
        World3D world = gameOfLifeService.getWorld(id);
        if (world == null) {
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("World not found").build();
        }
        List<Cell> aliveCells = world.getAliveCells();
        return Response.ok(aliveCells).build();
    }

    @GET
    @Path("/world/{id}/generation")
    public Response getGeneration(@PathParam("id") String id) {
        World3D world = gameOfLifeService.getWorld(id);
        if (world == null) {
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("World not found").build();
        }
        return Response.ok(world.getGeneration()).build();
    }

    @DELETE
    @Path("/world/{id}")
    public Response destroyWorld(@PathParam("id") String id) {
        try {
            gameOfLifeService.destroyWorld(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error destroying world: " + e.getMessage()).build();
        }
    }
}
