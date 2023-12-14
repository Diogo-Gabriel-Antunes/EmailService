package br.com.email.repository;

import br.com.email.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template,String> {
    Page<Template> findAllByAtivo(Pageable pagina, Boolean ativo);
}
