package com.github.mateusnere.model;

import java.util.List;

public class CotacaoResponse {

    private List<CotacaoDolarResponse> value;

    public List<CotacaoDolarResponse> getValue() {
        return value;
    }

    public void setValue(List<CotacaoDolarResponse> value) {
        this.value = value;
    }
}
