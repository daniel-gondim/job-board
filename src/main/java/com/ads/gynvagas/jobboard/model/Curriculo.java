package com.ads.gynvagas.jobboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "curriculos")
@Data
public class Curriculo {

    @MongoId
    private String id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("habilidades")
    private List<String> habilidades;

    @JsonProperty("experiencia_profissional")
    private List<ExperienciaProfissional> experienciaProfissional;

    @JsonProperty("educacao")
    private List<Educacao> educacao;

    @Data
    public static class ExperienciaProfissional {
        @JsonProperty("cargo")
        private String cargo;

        @JsonProperty("empresa")
        private String empresa;

        @JsonProperty("descricao")
        private String descricao;

        @JsonProperty("data_inicio")
        private String dataInicio;

        @JsonProperty("data_fim")
        private String dataFim;
    }

    @Data
    public static class Educacao {
        @JsonProperty("grau")
        private String grau;

        @JsonProperty("instituicao")
        private String instituicao;

        @JsonProperty("curso")
        private String curso;

        @JsonProperty("data_inicio")
        private String dataInicio;

        @JsonProperty("data_fim")
        private String dataFim;
    }
}
