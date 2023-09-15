package com.nagarro;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.net.URL;

//record TVSeries1( int id, URL url, String name, String type, String language){}
@Path("/tvseries")
public class TVSeriesResource {
    @RestClient
    TvSeriesProxy proxy;

    public static final Logger logger = Logger.getLogger(TVSeriesResource.class);

    int i  = 0;

    //example of fault tolerance using fallback
    @GET
    @Fallback(fallbackMethod = "getTvSeriesFallBack")
    @Path("/{id}")
    public Response getTvSeriesById(@PathParam("id") int id) {
        return Response.ok(proxy.getTvSeriesById(id)).build();
    }

    public Response getTvSeriesFallBack(int id) {
        return Response.ok("Site is under Maintenance").build();
    }

    //example of fault tolerance using @Retry, @Timeout & @CircuitBreaker
    @GET
    //@Retry(maxRetries = 2)
   // @Timeout(1000)
    @Fallback(fallbackMethod = "searchPeopleByNameFallback")
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 10000)
    public Response searchPeopleByName(@QueryParam("q") String personName) {
        logger.info("trying to search people by name" + i++);
        return Response.ok(proxy.searchPeopleByName(personName)).build();
    }

    public Response searchPeopleByNameFallback(String personName) {
        return Response.ok("Site is under Maintenance").build();
    }
}
