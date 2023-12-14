package br.com.email.model;


import br.com.email.model.AA.EntidadeGenerica;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ConfiguracaoEmail extends EntidadeGenerica {

    private String username;
    private String password;
    private String host;
    private String stmpPort;
    private Boolean sslSupport;
}
