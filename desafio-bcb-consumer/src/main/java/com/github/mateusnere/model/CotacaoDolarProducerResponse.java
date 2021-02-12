package com.github.mateusnere.model;

import java.math.BigDecimal;

public class CotacaoDolarProducerResponse {

    private BigDecimal valorCotacao;
    private String dataCotacao;

    public BigDecimal getValorCotacao() {
        return valorCotacao;
    }

    public void setValorCotacao(BigDecimal valorCotacao) {
        this.valorCotacao = valorCotacao;
    }

    public String getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(String dataCotacao) {
        this.dataCotacao = dataCotacao;
    }
}
