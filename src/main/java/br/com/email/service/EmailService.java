package br.com.email.service;

import br.com.email.DTO.ClienteDTO;
import br.com.email.DTO.EmailDTO;
import br.com.email.model.ConfiguracaoEmail;
import br.com.email.model.Email;
import br.com.email.model.Template;
import br.com.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository repository;

    private final JavaMailSenderImpl sender;



    private void configSender(JavaMailSenderImpl sender, ConfiguracaoEmail configuracaoEmail) {
        sender.setHost(configuracaoEmail.getHost());
        sender.setPort(Integer.valueOf(configuracaoEmail.getStmpPort()));
        sender.setPassword(configuracaoEmail.getPassword());
        sender.setUsername(configuracaoEmail.getUsername());
    }

    public void sendEmail(EmailDTO emailsDTO) {
        try {
            configSender(sender,emailsDTO.configuracaoEmail());
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(emailsDTO.configuracaoEmail().getUsername());
            message.setTo(emailsDTO.cliente().getContato().getEmail());
            message.setSubject(emailsDTO.template().getTituloTemplate());
            String text = montaText(emailsDTO.template(),emailsDTO.cliente());
            message.setText(text);



            sender.send(message);
        } catch (MailException ex) {
            log.warn("Erro na tentativa de envio de email. Message: {}", ex.getMessage());
        }

    }

    private String montaText(Template template, ClienteDTO clienteDTO) {
        String text = template.getTemplate();
        if(Template.TipoTemplate.VENDA.equals(template.getTipoTemplate())){
            if(template.getTemplate().contains("{NomeCliente}")){
                text.replace("{NomeCliente}",clienteDTO.getNome());
            }

            if(template.getTemplate().contains("{EmailCliente}")){
                text.replace("{EmailCliente}",clienteDTO.getContato().getEmail());
            }


        }else if(Template.TipoTemplate.PRIMEIROCONTATO.equals(template.getTipoTemplate())){
            if(template.getTemplate().contains("{NomeCliente}")){
                text.replace("{NomeCliente}",clienteDTO.getNome());
            }

        }else if(Template.TipoTemplate.PROMOCAO.equals(template.getTipoTemplate())){
            if(template.getTemplate().contains("{NomeCliente}")){
                text.replace("{NomeCliente}",clienteDTO.getNome());
            }

            if(template.getTemplate().contains("{EmailCliente}")){
                text.replace("{EmailCliente}",clienteDTO.getContato().getEmail());
            }
        }

        return text;
    }
}
