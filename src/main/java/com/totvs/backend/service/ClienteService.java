package com.totvs.backend.service;

import com.totvs.backend.exceptions.ObjetoNaoEncontradoException;
import com.totvs.backend.mapper.ClienteMapper;
import com.totvs.backend.model.cliente.Cliente;
import com.totvs.backend.model.cliente.dto.ClienteRequestDTO;
import com.totvs.backend.model.cliente.dto.ClienteResponseDTO;
import com.totvs.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper mapper;

    @Transactional
    public void cadastrarCliente(ClienteRequestDTO clienteDTO) {
        Cliente cliente = mapper.requestDtoToEntity(clienteDTO);
        cliente.setStatus(true);
        clienteRepository.save(cliente);
    }

    public List<ClienteResponseDTO> buscarClientes(){
        return mapper.entitiyListToResponseDtoList(clienteRepository.findAllClientes());
    }

    public ClienteResponseDTO buscarClientePorId(Long id){
        Cliente cliente = clienteRepository.findClienteById(id)
                .orElseThrow(()-> new ObjetoNaoEncontradoException("Cliente não encontrado"));
        return mapper.entityToResponseDTO(cliente);
    }




}
