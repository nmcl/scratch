package com.example.gamelife3d.resource;

import com.example.gamelife3d.model.World3D;
import com.example.gamelife3d.service.GameOfLifeService;
import com.example.gamelife3d.service.RendererService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/render")
@Produces(MediaType.APPLICATION_OCTET_STREAM)
@RequestScoped
public class RenderResource {

    @Inject
    GameOfLifeService gameOfLifeService;

    @Inject
    RendererService rendererService;

    @GET
    @Path("/world/{id}/image")
    public Response getWorldImage(@PathParam("id") String id,
                                 @QueryParam("width") @DefaultValue("800") int width,
                                 @QueryParam("height") @DefaultValue("600") int height) {
        World3D world = gameOfLifeService.getWorld(id);
        if (world == null) {
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("World not found").build();
        }

        try {
            byte[] imageData = rendererService.renderWorldAsImage(id, world, width, height, world.getDepth());
            return Response.ok(imageData)
                          .header("Content-Disposition", "attachment; filename=\"game-of-life-" + id + ".png\"")
                          .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error rendering world: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/world/{id}/slice/{sliceIndex}/{sliceType}")
    public Response getSliceImage(@PathParam("id") String id,
                                 @PathParam("sliceIndex") int sliceIndex,
                                 @PathParam("sliceType") int sliceType,
                                 @QueryParam("width") @DefaultValue("800") int width,
                                 @QueryParam("height") @DefaultValue("600") int height) {
        World3D world = gameOfLifeService.getWorld(id);
        if (world == null) {
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("World not found").build();
        }

        if (sliceType < 0 || sliceType > 2) {
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity("Invalid slice type. Use 0, 1, or 2").build();
        }

        try {
            byte[] imageData = rendererService.renderSlice(world, sliceIndex, sliceType);
            return Response.ok(imageData)
                          .header("Content-Disposition", "attachment; filename=\"slice-" + sliceIndex + "-" + sliceType + ".png\"")
                          .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error rendering slice: " + e.getMessage()).build();
        }
    }
}
