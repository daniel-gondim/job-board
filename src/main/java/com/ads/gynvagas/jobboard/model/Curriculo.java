package com.ads.gynvagas.jobboard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "curriculos")
@Data
public class Curriculo {

    @Id
    private String id;

    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String cep;
    private String logradouro;
    private String cidade;
    private String uf;
    private String empresa;
    private String funcao;
    private String instituicaoDeEnsino;
    private String curso;
    private String nivel;
 /*   
    @Data
    public static class Endereco {
        private String cep;
        private String logradouro;
        private String cidade;
        private String uf;
    }

    @Data
    public static class Experiencia {
        private String empresa;
        private String funcao;
    }

    @Data
    public static class Formacao {
        private String curso;
        private String nivel;
    } */
}
