package com.nagarro.databaseexample;

import com.nagarro.databaseexample.model.AadharCard;
import com.nagarro.databaseexample.model.Citizen;
import com.nagarro.databaseexample.model.Laptop;
import com.nagarro.databaseexample.repository.CitizenRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/citizen")
public class MappingResource {

    @Inject
    CitizenRepository repository;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCitizens() {
        return Response.ok(repository.listAll()).build();
    }
    @POST
    @Path("/add")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveCitizen(Citizen citizen) {
        AadharCard card = new AadharCard();
        card.setAadharNo(1235L);
        card.setCompany("Comp");
        card.setCitizen(citizen);
        citizen.setAadharCard(card);
        repository.persist(citizen);
        if(repository.isPersistent(citizen)) {
            return Response.created(URI.create("/laptop/" + citizen.getId())).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
