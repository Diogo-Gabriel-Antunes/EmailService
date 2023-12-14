package br.com.email.DTO;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class ClienteDTO {

    private String nome;
    private String sobrenome;
    private String cargo;
    private Contato contato;
    private String setor;
    private Double minFaturamento;
    private Double maxFaturamento;

    @Data
    public class Contato{
        private String telefone;
        private String telefone2;
        private String email;
    }
}
