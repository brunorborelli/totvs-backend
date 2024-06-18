package com.totvs.backend.service;

import com.totvs.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service que define operações relacionadas a Endereco.
 */
@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;


}
