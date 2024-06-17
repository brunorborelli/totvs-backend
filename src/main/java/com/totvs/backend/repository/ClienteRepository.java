package com.totvs.backend.repository;

import com.totvs.backend.model.cliente.Cliente;
import com.totvs.backend.model.telefone.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Query(value = "SELECT * FROM cliente WHERE status = true", nativeQuery = true)
    List<Cliente> findAllClientes();

    @Query(value = "SELECT * FROM cliente WHERE id = ?1 AND status = true", nativeQuery = true)
    Optional<Cliente> findClienteById(Long id);

    @Query(value = "SELECT * FROM cliente WHERE nome = ?1 AND status = true", nativeQuery = true)
    List<Cliente> findByNome(String cliente);
}
