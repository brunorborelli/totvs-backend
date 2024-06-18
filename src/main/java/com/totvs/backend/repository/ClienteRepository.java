package com.totvs.backend.repository;

import com.totvs.backend.model.cliente.Cliente;
import com.totvs.backend.model.telefone.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Interface de repositório para operações de banco de dados relacionadas à entidade Cliente.
 * Estende JpaRepository.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca todos os clientes ativos.
     *
     * @return Lista de clientes ativos.
     */
    @Query(value = "SELECT * FROM cliente WHERE status = true", nativeQuery = true)
    List<Cliente> findAllClientes();

    /**
     * Busca um cliente ativo pelo ID.
     *
     * @param id ID do cliente a ser buscado.
     * @return Cliente encontrado, se existir.
     */
    @Query(value = "SELECT * FROM cliente WHERE id = ?1 AND status = true", nativeQuery = true)
    Optional<Cliente> findClienteById(Long id);

    /**
     * Busca clientes ativos pelo nome.
     *
     * @param nome Nome do cliente a ser buscado.
     * @return Lista de clientes encontrados com o nome especificado.
     */
    @Query(value = "SELECT * FROM cliente WHERE nome = ?1 AND status = true", nativeQuery = true)
    List<Cliente> findByNome(String cliente);
}
