package br.com.email.repository;

import br.com.email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email,String> {

    List<Email> findBySendFalse();
}
