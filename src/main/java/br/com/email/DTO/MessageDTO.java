package br.com.email.DTO;

import lombok.Data;

@Data
public class MessageDTO {

    private String owner;
    private String from;
    private String to;
    private String title;
    private String text;
}
