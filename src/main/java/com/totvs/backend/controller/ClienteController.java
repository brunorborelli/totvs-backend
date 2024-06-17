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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

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
}
