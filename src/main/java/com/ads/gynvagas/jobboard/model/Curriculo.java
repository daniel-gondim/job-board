package com.ads.gynvagas.jobboard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "curriculos")
@Data
public class Curriculo {

    @Id
    private String id;

    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private Endereco endereco;
    private List<Experiencia> experiencias;
    private List<Formacao> formacoes;

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
    }
}
