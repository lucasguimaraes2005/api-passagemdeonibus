package com.unisales.passagemdeonibus.domain.passagem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassagemRepository extends JpaRepository<Passagem, String> {
    List<Passagem> findByUsuarioId(String usuarioId);
    List<Passagem> findByOnibusId(String onibusId);

}
