package com.ads.gynvagas.jobboard.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
public class Candidato {
    @MongoId
    private String nome;
    private String cpf;
    private String email;
    private int id;
}
