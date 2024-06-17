package com.totvs.backend.service;

import com.totvs.backend.mapper.ClienteMapper;
import com.totvs.backend.model.cliente.dto.ClienteRequestDTO;
import com.totvs.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper mapper;

    @Transactional
    public void cadastrarCliente(ClienteRequestDTO clienteDTO) {
        clienteRepository.save(mapper.requestDtoToEntity(clienteDTO));
    }


}
