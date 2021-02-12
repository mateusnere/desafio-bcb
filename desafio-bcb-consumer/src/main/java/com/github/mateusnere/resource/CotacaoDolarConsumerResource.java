package com.github.mateusnere.resource;

import com.github.mateusnere.model.CotacaoDolarConsumerRequest;
import com.github.mateusnere.model.CotacaoDolarConsumerResponse;
import com.github.mateusnere.model.CotacaoDolarProducerResponse;
import com.github.mateusnere.service.CotacaoDolarConsumerService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("/cotacao")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CotacaoDolarConsumerResource {

    @Inject
    @RestClient
    CotacaoDolarConsumerService cotacaoDolarConsumerService;

    @POST
    @Operation(summary = "Buscar cotação do dia e do dia anterior", description = "Buscar cotação de um dia específico e de seu dia útil anterior na API desafio-bcb-producer")
    @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = CotacaoDolarConsumerResponse.class)))
    public Response mostrarCotacaoDoDiaEDiaAnterior(CotacaoDolarConsumerRequest requisicao) {

        CotacaoDolarProducerResponse respostaService = cotacaoDolarConsumerService.getCotacaoDolarDia(requisicao);
        CotacaoDolarProducerResponse respostaServiceDiaAnterior = cotacaoDolarConsumerService.getCotacaoDolarDia(retornaDiaUtilAnterior(requisicao.getDiaCotacao()));

        CotacaoDolarConsumerResponse resposta = montaResposta(respostaService, respostaServiceDiaAnterior);

        return Response.status(Response.Status.OK).entity(resposta).build();
    }

    public CotacaoDolarConsumerResponse montaResposta(CotacaoDolarProducerResponse respostaService, CotacaoDolarProducerResponse respostaServiceDiaAnterior) {

        CotacaoDolarConsumerResponse resposta = new CotacaoDolarConsumerResponse();
        resposta.setDataCotacao(respostaService.getDataCotacao());
        resposta.setValorCotacao(respostaService.getValorCotacao());
        resposta.setDataCotacaoDiaAnterior(respostaServiceDiaAnterior.getDataCotacao());
        resposta.setValorCotacaoDiaAnterior(respostaServiceDiaAnterior.getValorCotacao());

        return resposta;
    }

    public CotacaoDolarConsumerRequest retornaDiaUtilAnterior(String diaCotacao) {

        LocalDate diaAnterior;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate diaCotacaoDateTime = LocalDate.parse(diaCotacao, formatter);
        int diaDaSemana = diaCotacaoDateTime.getDayOfWeek().getValue();
        if(diaDaSemana == 7) {
            diaAnterior = diaCotacaoDateTime.minusDays(2L);
        } else if(diaDaSemana == 1) {
            diaAnterior = diaCotacaoDateTime.minusDays(3L);
        } else {
            diaAnterior = diaCotacaoDateTime.minusDays(1L);
        }

        CotacaoDolarConsumerRequest requisicao = new CotacaoDolarConsumerRequest();
        requisicao.setDiaCotacao(diaAnterior.getMonthValue() + "-" + diaAnterior.getDayOfMonth() + "-" + diaAnterior.getYear());
        return requisicao;
    }

}
