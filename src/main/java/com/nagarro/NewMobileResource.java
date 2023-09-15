package com.nagarro;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

record Mobile(int id, String name, String brand, int ram, int storage) {}
@Path("/newmobile")
public class NewMobileResource {
    List<Mobile> mobileList = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList() {
        return Response.ok(mobileList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMobileToList(Mobile mobile) {
        mobileList.add(mobile);
        return Response.ok(mobileList).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobileToList(@PathParam("id") int id, Mobile updatedMobile) {
       mobileList = mobileList.stream().map(mobile -> {
           if(mobile.id() == id) {
               return updatedMobile;
           } else {
               return mobile;
           }
       }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMobileFromList(@PathParam("id") int id) {
        Optional<Mobile> op = mobileList.stream().filter(mobile -> mobile.id() == id).findFirst();
        if(op.isPresent()) {
            mobileList.remove(op.get());
            return Response.ok(mobileList).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileById(@PathParam("id") int id) {
        Optional<Mobile> op = mobileList.stream().filter(mobile -> mobile.id() == id).findFirst();
        if(op.isPresent()) {
            return Response.ok(op.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
