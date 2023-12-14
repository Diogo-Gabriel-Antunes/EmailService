package br.com.email.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Email {

    @Id
    private String uuid;
    private String owner;
    private String emailFrom;
    private String emailToto;
    private String title;
    private String text;
    private boolean send;
    private LocalDateTime sendDate;
}
