package com.totvs.backend.mapper;


import com.totvs.backend.model.telefone.Telefone;
import com.totvs.backend.model.telefone.dto.TelefoneRequestDTO;
import com.totvs.backend.model.telefone.dto.TelefoneResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TelefoneMapper {
   public Telefone requestDtoToEntity(TelefoneRequestDTO dto) {
      if (dto == null) {
         return null;
      }

      Telefone telefone = new Telefone();
      telefone.setTelefone(dto.getTelefone());

      return telefone;
   }

   public TelefoneResponseDTO entityToResponseDTO(Telefone entity) {
      if (entity == null) {
         return null;
      }

      TelefoneResponseDTO dto = new TelefoneResponseDTO();
      dto.setTelefone(entity.getTelefone());

      return dto;
   }

}
