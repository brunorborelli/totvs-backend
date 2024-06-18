package com.totvs.backend.stub;

import com.totvs.backend.model.endereco.Endereco;
import com.totvs.backend.model.endereco.dto.EnderecoRequestDTO;
import com.totvs.backend.model.endereco.dto.EnderecoResponseDTO;

public class EnderecoStub {
    public static Endereco getEndereco() {
        return Endereco.builder()
                .id(1L)
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .logradouro("Rua das Flores")
                .numero("123")
                .status(true)
                .build();
    }

    public static EnderecoRequestDTO getEnderecoRequestDTO(){
        return EnderecoRequestDTO.builder()
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .logradouro("Rua das Flores")
                .numero("123")
                .build();
    }

    public static EnderecoResponseDTO getEnderecoResponseDTO(){
        return EnderecoResponseDTO.builder()
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .logradouro("Rua das Flores")
                .numero("123")
                .build();
    }



}
