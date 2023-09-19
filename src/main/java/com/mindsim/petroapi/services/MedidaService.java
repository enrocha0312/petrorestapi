package com.mindsim.petroapi.services;

import com.mindsim.petroapi.repositories.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedidaService {
    @Autowired
    private MedidaRepository medidaRepository;
}
