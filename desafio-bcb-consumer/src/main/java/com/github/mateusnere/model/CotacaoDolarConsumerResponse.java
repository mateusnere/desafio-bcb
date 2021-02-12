package com.github.mateusnere.model;

import java.math.BigDecimal;

public class CotacaoDolarConsumerResponse {

    private BigDecimal valorCotacao;

    private String dataCotacao;

    private BigDecimal valorCotacaoDiaAnterior;

    private String dataCotacaoDiaAnterior;

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

    public BigDecimal getValorCotacaoDiaAnterior() {
        return valorCotacaoDiaAnterior;
    }

    public void setValorCotacaoDiaAnterior(BigDecimal valorCotacaoDiaAnterior) {
        this.valorCotacaoDiaAnterior = valorCotacaoDiaAnterior;
    }

    public String getDataCotacaoDiaAnterior() {
        return dataCotacaoDiaAnterior;
    }

    public void setDataCotacaoDiaAnterior(String dataCotacaoDiaAnterior) {
        this.dataCotacaoDiaAnterior = dataCotacaoDiaAnterior;
    }
}
