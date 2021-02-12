package com.github.mateusnere.service;

import com.github.mateusnere.model.CotacaoDolarConsumerRequest;
import com.github.mateusnere.model.CotacaoDolarProducerResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;


@RegisterRestClient(configKey = "configKey")
public interface CotacaoDolarConsumerService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cotacaoDolarDia")
    CotacaoDolarProducerResponse getCotacaoDolarDia(CotacaoDolarConsumerRequest cotacaoDolarConsumerRequest);
}
