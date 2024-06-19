package com.totvs.backend.controller;

import com.totvs.backend.model.cliente.dto.ClienteRequestDTO;
import com.totvs.backend.model.cliente.dto.ClienteResponseDTO;
import com.totvs.backend.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RestController para operações relacionadas a clientes.
 */
@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Endpoint para cadastrar um novo cliente.
     *
     * @param clienteDTO DTO contendo os dados do cliente a ser cadastrado.
     * @return ResponseEntity representando o status HTTP da operação (201 Created).
     */
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(
            summary = "Cliente",
            description = "Rota para salvar cliente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de Cliente com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "403", description = "Proibido"),
            @ApiResponse(responseCode = "500", description = "Erro do lado do servidor")
    })
    public ResponseEntity<Void> cadastrarCliente(@RequestBody ClienteRequestDTO clienteDTO) {
            clienteService.cadastrarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint para buscar um cliente ativo por ID.
     *
     * @param id ID do cliente a ser buscado.
     * @return ResponseEntity com o DTO representando o cliente encontrado (200 OK).
     *         Ou ResponseEntity com status 404 caso o cliente não seja encontrado.
     */
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(
            summary = "Cliente",
            description = "Rota para buscar cliente ativo por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro do lado do servidor")
    })
    public ResponseEntity<ClienteResponseDTO> buscarClientePorId(@PathVariable("id") Long id) {
        ClienteResponseDTO clienteResponseDTO = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    /**
     * Endpoint para buscar todos os clientes ativos.
     *
     * @return ResponseEntity com a lista de clientes encontrados ou vazia (200).
     */
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(
            summary = "Clientes",
            description = "Rota para buscar todos os clientes ativos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro do lado do servidor")
    })
    public ResponseEntity<List<ClienteResponseDTO>> buscarClientes() {
        List<ClienteResponseDTO> clientes = clienteService.buscarClientes();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Endpoint para marcar um cliente como deletado (soft delete) pelo ID.
     *
     * @param id ID do cliente a ser marcado como deletado.
     * @return ResponseEntity representando o status HTTP da operação (204 No Content).
     *         Ou ResponseEntity com status 404 caso o cliente não seja encontrado.
     */
    @Operation(
            summary = "Soft Delete de Cliente",
            description = "Marca um cliente como deletado (soft delete) pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro do lado do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteCliente(@PathVariable("id") Long id) {
        clienteService.softDeleteCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint para atualizar os dados de um cliente pelo ID.
     *
     * @param id ID do cliente a ser atualizado.
     * @param clienteDTO DTO contendo os novos dados do cliente.
     * @return ResponseEntity com o DTO representando o cliente atualizado (200 OK).
     *         Ou ResponseEntity com status 400 se a requisição for inválida,
     *         ou status 404 caso o cliente não seja encontrado.
     */
    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(
            summary = "Atualizar Cliente",
            description = "Rota para atualizar cliente por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro do lado do servidor")
    })
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteRequestDTO clienteDTO) {
        ClienteResponseDTO clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }
}

