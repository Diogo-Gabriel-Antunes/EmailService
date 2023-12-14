package br.com.email.Controller;


import br.com.email.DTO.ConfiguracaoEmailDTO;
import br.com.email.DTO.TemplateDTO;
import br.com.email.DTO.TemplateDTORepresentacao;
import br.com.email.model.ConfiguracaoEmail;
import br.com.email.model.Template;
import br.com.email.repository.ConfiguracaoEmailRepository;
import br.com.email.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("configuracao-email")
public class ConfiguracaoEmailResource {

    @Autowired
    private ConfiguracaoEmailRepository configuracaoEmailRepository;

    @GetMapping
    public List<ConfiguracaoEmail> getAll(@PageableDefault(size = 10, sort = {"dataIntegracao"}) Pageable pagina, @RequestParam(required = false) String username) {
        Page<ConfiguracaoEmail> all = null;
        if (username != null) {
            all = configuracaoEmailRepository.findAllByUsername(pagina, username);
        } else {
            all = configuracaoEmailRepository.findAll(pagina);
        }

        return all.stream().toList();
    }


    @GetMapping("/{uuid}")
    public ConfiguracaoEmail getOne(@PathVariable String uuid) {
        Optional<ConfiguracaoEmail> configuracaoEmail = configuracaoEmailRepository.findById(uuid);
        return configuracaoEmail.orElse(null);
    }

    @PostMapping
    public ResponseEntity<ConfiguracaoEmail> create(@RequestBody ConfiguracaoEmail configuracaoEmail) {
        ConfiguracaoEmail configuracaoEmailSaved = configuracaoEmailRepository.save(configuracaoEmail);

        return ResponseEntity.ok(configuracaoEmailSaved);

    }

    @PutMapping("/{uuid}")
    public ResponseEntity update(@PathVariable String uuid, @RequestBody ConfiguracaoEmailDTO configuracaoEmailDTO) {
        Optional<ConfiguracaoEmail> configuracaoEmail = configuracaoEmailRepository.findById(uuid);
        if (!configuracaoEmail.isEmpty()) {
            ConfiguracaoEmail configuracaoEmail1 = configuracaoEmail.get();
            configuracaoEmail1.setUsername(configuracaoEmailDTO.username());
            configuracaoEmail1.setPassword(configuracaoEmailDTO.password());
            configuracaoEmail1.setHost(configuracaoEmailDTO.host());
            configuracaoEmail1.setStmpPort(configuracaoEmailDTO.stmpPort());
            configuracaoEmail1.setSslSupport(configuracaoEmailDTO.sslSupport());
            configuracaoEmailRepository.save(configuracaoEmail1);
            return ResponseEntity.ok(configuracaoEmail1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable String uuid) {
        configuracaoEmailRepository.deleteById(uuid);
        return ResponseEntity.ok().build();
    }
}
