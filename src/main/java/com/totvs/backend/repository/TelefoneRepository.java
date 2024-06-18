package com.totvs.backend.repository;

import com.totvs.backend.model.telefone.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface de repositório para operações de banco de dados relacionadas à entidade Telefone.
 * Estende JpaRepository.
 */
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    /**
     * Busca telefones ativos pelo número de telefone.
     *
     * @param telefone Número de telefone a ser buscado.
     * @return Lista de telefones encontrados com o número especificado.
     */
    @Query(value = "SELECT * FROM telefone WHERE telefone = ?1 AND status = true", nativeQuery = true)
    List<Telefone> findByTelefone(String telefone);
}
