package com.totvs.backend.stub;

import com.totvs.backend.model.cliente.Cliente;
import com.totvs.backend.model.cliente.dto.ClienteRequestDTO;
import com.totvs.backend.model.cliente.dto.ClienteResponseDTO;

import static com.totvs.backend.stub.EnderecoStub.getEndereco;
import static com.totvs.backend.stub.EnderecoStub.getEnderecoRequestDTO;
import static com.totvs.backend.stub.EnderecoStub.getEnderecoResponseDTO;
import static com.totvs.backend.stub.TelefoneStub.getTelefones;
import static com.totvs.backend.stub.TelefoneStub.getTelefonesRequestDTO;
import static com.totvs.backend.stub.TelefoneStub.getTelefonesResponseDTO;

public class ClienteStub {

    public static Cliente getCliente() {
        return Cliente.builder()
                .nome("João da Silva")
                .endereco(getEndereco())
                .telefones(getTelefones())
                .status(true)
                .build();
    }

    public static ClienteRequestDTO getClienteValidoRequestDTO(){
        return ClienteRequestDTO.builder()
                .nome("Joao")
                .endereco(getEnderecoRequestDTO())
                .telefones(getTelefonesRequestDTO())
                .build();
    }

    public static ClienteRequestDTO getClienteRequestDTO(){
        return ClienteRequestDTO.builder()
                .nome("João da Silva")
                .endereco(getEnderecoRequestDTO())
                .telefones(getTelefonesRequestDTO())
                .build();
    }

    public static ClienteResponseDTO getClienteResponseDTO(){
        return ClienteResponseDTO.builder()
                .nome("João da Silva")
                .endereco(getEnderecoResponseDTO())
                .telefones(getTelefonesResponseDTO())
                .build();
    }

}
