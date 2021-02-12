package com.github.mateusnere.service;

import com.github.mateusnere.model.CotacaoResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RegisterRestClient(configKey = "configKey")
public interface CotacaoDolarService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/CotacaoDolarDia(dataCotacao='{diaCotacao}')")
    CotacaoResponse getCotacaoDolarDia(
            @PathParam("diaCotacao") String diaCotacao
    );
}
