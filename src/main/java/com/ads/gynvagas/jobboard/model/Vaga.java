package com.ads.gynvagas.jobboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Vaga {
    @Id
    private String nome;
    private String descricao;
    private String empresa;
    private String contato;
    private Double salario;
}
