package br.com.email.model.AA;

import br.com.email.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Setter
@MappedSuperclass
public class EntidadeGenerica {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "usuario_uuid")
    @JsonIgnore
    private Usuario usuario;
    @Column(name = "dataatualização")
    private LocalDateTime dataAtualizacao;
    @Column(name = "dataintegração")
    private LocalDateTime dataIntegracao;

    @PrePersist
    public void prePersist(){
        this.setDataIntegracao(LocalDateTime.now());
        setPropriedades();
    }

    @PreUpdate
    public void preUpdate(){
        setPropriedades();
    }

    private void setPropriedades() {
        this.setDataAtualizacao(LocalDateTime.now());
    }
}
