package br.com.email.DTO;

import br.com.email.model.ConfiguracaoEmail;
import br.com.email.model.Template;
import lombok.Data;


public record EmailDTO(ConfiguracaoEmail configuracaoEmail, ClienteDTO cliente, Template template, String enviante) {
}
