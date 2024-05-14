package com.ads.gynvagas.jobboard.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
public class Vaga {
    @MongoId
    private String nome;
    private String descricao;
    private String empresa;
    private String contato;
    private Double salario;
}
