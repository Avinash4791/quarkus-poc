package com.nagarro;

import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.logging.Logger;

import java.util.List;

@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TvSeriesProxy {
    @GET
    @Path("/shows/{id}")
    TVSeries getTvSeriesById(@PathParam("id") int id);

    @GET
    @Path("/search/people")
    JsonArray searchPeopleByName(@QueryParam("q") String personName);
}
