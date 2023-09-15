package com.nagarro.databaseexample;

import com.nagarro.databaseexample.model.Laptop;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.Optional;

@Path("/laptop")
public class LapatopResource {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLaptops() {
        return Response.ok(Laptop.listAll()).build();
    }
    @POST
    @Path("/add")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveLaptop(Laptop laptop) {
        Laptop.persist(laptop);
        if(laptop.isPersistent()) {
            return Response.created(URI.create("/laptop/" + laptop.id)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLaptopById(@PathParam("id") int id) {
       return Response.ok(Laptop.findById(id)).build();

    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLaptopById(@PathParam("id") int id, Laptop laptop) {
        Optional<Laptop> lap = Laptop.findByIdOptional(id);
        if (lap.isPresent()) {
            Laptop dbLaptop = lap.get();
            dbLaptop.setName(laptop.getName());
            dbLaptop.setBrand(laptop.getBrand());
            dbLaptop.setRam(laptop.getRam());
            dbLaptop.setStorage(laptop.getStorage());
            Laptop.persist(dbLaptop);
            return Response.created(URI.create("/laptop/" + laptop.id)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLaptop(@PathParam("id") int id) {
        if (Laptop.deleteById(id)) {
            return Response.ok(Laptop.listAll()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
