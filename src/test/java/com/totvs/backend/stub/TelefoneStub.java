package com.totvs.backend.stub;

import com.totvs.backend.model.telefone.Telefone;
import com.totvs.backend.model.telefone.dto.TelefoneRequestDTO;
import com.totvs.backend.model.telefone.dto.TelefoneResponseDTO;

import java.util.List;

public class TelefoneStub {

    public static Telefone getTelefone1() {
        return Telefone.builder()
                .telefone("5562982971994")
                .status(true)
                .build();
    }
    public static Telefone getTelefone2() {
        return Telefone.builder()
                .telefone("32257248")
                .status(true)
                .build();
    }

    public static List<Telefone> getTelefones() {
        return List.of(getTelefone1(), getTelefone2());
    }

    public static TelefoneRequestDTO getTelefoneRequestDTO() {
        return TelefoneRequestDTO.builder()
                .telefone("5562982971994")
                .build();
    }
    public static TelefoneResponseDTO getTelefoneResponseDTO() {
        return TelefoneResponseDTO.builder()
                .telefone("5562982971994")
                .build();
    }

    public static List<TelefoneRequestDTO> getTelefonesRequestDTO() {
        return List.of(getTelefoneRequestDTO());
    }

    public static List<TelefoneResponseDTO> getTelefonesResponseDTO() {
        return List.of(getTelefoneResponseDTO());
    }
}
