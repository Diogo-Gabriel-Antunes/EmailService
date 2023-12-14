package br.com.email.DTO;

public record ConfiguracaoEmailDTO(String username,String password,String host,String stmpPort, Boolean sslSupport) {
}
