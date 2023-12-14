package br.com.email.repository;

import br.com.email.model.ConfiguracaoEmail;
import br.com.email.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoEmailRepository extends JpaRepository<ConfiguracaoEmail,String> {
    Page<ConfiguracaoEmail> findAllByUsername(Pageable pagina, String username);
}
