package com.ads.gynvagas.jobboard.model;

import jakarta.persistence.Entity;

@Entity
public class Vaga {
    private String nome;
    private String descricao;
    private String empresa;
    private String contato;
    private Double salario;
}
