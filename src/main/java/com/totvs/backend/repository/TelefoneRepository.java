package com.totvs.backend.repository;

import com.totvs.backend.model.telefone.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
