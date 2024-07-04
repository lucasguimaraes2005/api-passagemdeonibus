package com.unisales.passagemdeonibus.domain.passagemrequest;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassagemRequest {
    private String onibus;
    private String usuario;
    private String dataViagem;
    private String horaViagem;
    private Double preco;
}
