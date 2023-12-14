package br.com.email.Controller;

import br.com.email.DTO.EmailDTO;
import br.com.email.model.Email;
import br.com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("send-email")
@EnableAsync
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public void send(@RequestBody List<EmailDTO> emailsDTOS){
        for (EmailDTO emailsDTO : emailsDTOS) {
            emailService.sendEmail(emailsDTO);
        }
    }
}
