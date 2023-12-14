package br.com.email.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    private String email;
    private String senha;
    private LocalDateTime dataIntegração;
    private LocalDateTime dataAtualização;
}
