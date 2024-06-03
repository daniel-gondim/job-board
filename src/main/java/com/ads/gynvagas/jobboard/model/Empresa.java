package com.ads.gynvagas.jobboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "empresas")
@Data
public class Empresa {
    @MongoId
    private String id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cnpj")
    private String cnpj;
    @JsonProperty("endereco")
    private String endereco;
    @JsonProperty("telefone")
    private String telefone;
    @JsonProperty("email")
    private String email;
}
