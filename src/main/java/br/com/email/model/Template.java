package br.com.email.model;

import br.com.email.model.AA.EntidadeGenerica;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Template extends EntidadeGenerica {

    private String tituloTemplate;
    private String nomeTemplate;
    @Column(columnDefinition = "TEXT")
    private String template;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private TipoTemplate tipoTemplate;

    public enum TipoTemplate {
        VENDA,PRIMEIROCONTATO,PROMOCAO;
    }
}
