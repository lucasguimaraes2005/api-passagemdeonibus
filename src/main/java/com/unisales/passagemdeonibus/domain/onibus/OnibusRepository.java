package com.unisales.passagemdeonibus.domain.onibus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OnibusRepository extends JpaRepository<Onibus, String> {
    Optional<Onibus> findByPlaca(String placa);
}
