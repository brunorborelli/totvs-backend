package com.totvs.backend.repository;

import com.totvs.backend.model.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para operações de banco de dados relacionadas à entidade Endereco.
 * Estende JpaRepository.
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
