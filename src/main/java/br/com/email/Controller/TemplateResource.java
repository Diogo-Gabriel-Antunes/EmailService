package br.com.email.Controller;


import br.com.email.DTO.TemplateDTO;
import br.com.email.DTO.TemplateDTORepresentacao;
import br.com.email.model.Template;
import br.com.email.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("template")
public class TemplateResource {

    @Autowired
    private TemplateRepository templateRepository;
    @GetMapping
    public List<TemplateDTORepresentacao> getAll(@PageableDefault(size = 10,sort = {"nomeTemplate"}) Pageable pagina,@RequestParam(required = false) Boolean ativo){
        Page<Template> all = null;

        if(ativo != null){
           all = templateRepository.findAllByAtivo(pagina,ativo);
        }else{
            all = templateRepository.findAll(pagina);
        }
        List<Template> templates = all.get().toList();
        Stream<TemplateDTORepresentacao> templateDTORepresentacaoStream = templates.stream().map(template -> {
            String ativoStr = "";
            if (template.getAtivo()) {
                ativoStr = "Ativo";
            } else {
                ativoStr = "Desativado";
            }
            return new TemplateDTORepresentacao(template.getUuid(),template.getNomeTemplate(), template.getTemplate(), ativoStr);

        });

        return templateDTORepresentacaoStream.toList();
    }

    @GetMapping("/{uuid}")
    public Template getOne(@PathVariable String uuid){
        Optional<Template> template = templateRepository.findById(uuid);
        return template.orElse(null);
    }

    @PostMapping
    public ResponseEntity<Template> create(@RequestBody Template template){
        template.setAtivo(true);
        Template save = templateRepository.save(template);

        return ResponseEntity.ok(save);

    }

    @PutMapping("/{uuid}")
    public ResponseEntity update(@PathVariable String uuid,@RequestBody TemplateDTO templateDTO){
        Optional<Template> template = templateRepository.findById(uuid);
        if(!template.isEmpty()){
            Template template1 = template.get();
            template1.setTemplate(templateDTO.template());
            template1.setNomeTemplate(templateDTO.nomeTemplate());
            templateRepository.save(template1);
            return ResponseEntity.ok(template1);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/ativar-desativar/{uuid}")
    public ResponseEntity ativarDesativar(@PathVariable String uuid){
        Optional<Template> template = templateRepository.findById(uuid);
        if(!template.isEmpty()){
            Template template1 = template.get();
            if(template1.getAtivo()){
                template1.setAtivo(false);
            }else{
                template1.setAtivo(true);
            }
            templateRepository.save(template1);
            return ResponseEntity.ok(template1);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable String uuid){
        templateRepository.deleteById(uuid);
        return  ResponseEntity.ok().build();
    }
}
