package com.nagarro;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {
    List<String> mobileList = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMobileList() {
        return Response.ok(mobileList).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addMobileList(String mobilename) {
        mobileList.add(mobilename);
        return Response.ok(mobilename).build();
    }

    @PUT
    @Path("/{oldMobileName}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobileList(@PathParam("oldMobileName") String oldMobileName,
                                     @QueryParam("newMobileName") String newMobileName) {
        mobileList = mobileList.stream().map(mobile -> {
            if(mobile.equalsIgnoreCase(oldMobileName)) {
                return newMobileName;
            } else {
                return mobile;
            }
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }

    @DELETE
    @Path("/{mobileToDelete}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobileToDelete") String mobileToDelete) {
        mobileList.remove(mobileToDelete);
        return Response.ok(mobileList).build();
    }
}
