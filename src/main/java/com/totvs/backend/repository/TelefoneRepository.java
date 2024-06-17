package com.totvs.backend.repository;

import com.totvs.backend.model.telefone.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    @Query(value = "SELECT * FROM telefone WHERE telefone = ?1 AND status = true", nativeQuery = true)
    List<Telefone> findByTelefone(String telefone);
}
