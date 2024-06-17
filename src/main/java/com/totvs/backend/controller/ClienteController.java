package com.totvs.backend.controller;

import com.totvs.backend.model.cliente.dto.ClienteRequestDTO;
import com.totvs.backend.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
