package com.totvs.backend.service;

import com.totvs.backend.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

}
