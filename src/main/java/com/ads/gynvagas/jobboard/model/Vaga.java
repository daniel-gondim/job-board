package com.ads.gynvagas.jobboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "vagas")
@Data
public class Vaga {
    @MongoId
    private String id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("empresa")
    private String empresa;

    @JsonProperty("contato")
    private String contato;

    @JsonProperty("salario")
    private Double salario;
}