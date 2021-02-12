package com.github.mateusnere.resource;

import com.github.mateusnere.model.CotacaoDolarProducerResponse;
import com.github.mateusnere.model.CotacaoDolarRequest;
import com.github.mateusnere.model.CotacaoResponse;
import com.github.mateusnere.service.CotacaoDolarService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cotacaoDolarDia")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CotacaoDolarResource {

    @Inject
    @RestClient
    CotacaoDolarService cotacaoDolarService;

    @POST
    @Operation(summary = "Buscar cotação do dia", description = "Buscar cotação de um dia específico na API do Banco Central")
    @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = CotacaoDolarProducerResponse.class)))
    public Response retornarCotacaoDoDia(CotacaoDolarRequest requisicao) {

        CotacaoResponse respostaAPIBanco = cotacaoDolarService.getCotacaoDolarDia(requisicao.getDiaCotacao());

        CotacaoDolarProducerResponse resposta = montaRespostaProducer(respostaAPIBanco);
        return Response.status(Response.Status.OK).entity(resposta).build();
    }

    private CotacaoDolarProducerResponse montaRespostaProducer(CotacaoResponse respostaAPIBanco) {

        CotacaoDolarProducerResponse resposta = new CotacaoDolarProducerResponse();
        if(respostaAPIBanco.getValue() != null) {
            resposta.setValorCotacao(respostaAPIBanco.getValue().get(0).getCotacaoVenda());
            resposta.setDataCotacao(respostaAPIBanco.getValue().get(0).getDataHoraCotacao());
        }
        return resposta;
    }
}
