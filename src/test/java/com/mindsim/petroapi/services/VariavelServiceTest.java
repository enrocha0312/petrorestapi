package com.mindsim.petroapi.services;

import com.mindsim.petroapi.entities.Variavel;
import com.mindsim.petroapi.repositories.VariavelRepository;
import com.mindsim.petroapi.shared.dto.VariavelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VariavelServiceTest {

    @InjectMocks
    private VariavelService variavelService;
    @Mock
    private VariavelRepository variavelRepository;
    @Mock
    private ModelMapper modelMapper;
    private Variavel variavel;
    private VariavelDTO variavelDTO;
    private Optional<VariavelDTO> optionalVariavelDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        variavel = Variavel.builder()
                .tag("tagTest")
                .id(1)
                .name("nameTest")
                .mfgName("mfgNameTest")
                .plataforma("plataformTest")
                .build();
        variavelDTO = modelMapper.map(variavel, VariavelDTO.class);
        optionalVariavelDTO = Optional.of(variavelDTO);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }
}