package com.mindsim.petroapi.services;

import com.mindsim.petroapi.entities.Variavel;
import com.mindsim.petroapi.repositories.VariavelRepository;
import com.mindsim.petroapi.shared.dto.VariavelDTO;
import net.bytebuddy.matcher.StringMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class VariavelServiceTest {
    private static final String TAG = "tagTest";
    private static final String PLATAFORMA = "plataformTest";
    private static final String NAME = "nameTest";
    private static final String MFG_NAME = "mfgNameTest";
    private static final Integer ID = 1;
    @InjectMocks
    private VariavelService variavelService;
    @Mock
    private VariavelRepository variavelRepository;
    @Mock
    private ModelMapper modelMapper;
    private Variavel variavel;
    private VariavelDTO variavelDTO;
    private Optional<VariavelDTO> optionalVariavelDTO;

    private Optional<Variavel> optionalVariavel;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        variavel = Variavel.builder()
                .tag(TAG)
                .id(ID)
                .name(NAME)
                .mfgName(MFG_NAME)
                .plataforma(PLATAFORMA)
                .build();
        variavelDTO = new ModelMapper().map(variavel, VariavelDTO.class);
        optionalVariavelDTO = Optional.of(variavelDTO);
        optionalVariavel = Optional.of(variavel);
    }

    @Test
    void findAll() {
    }

    @Test
    void findByIdWorksSuccessfully() {
        when(variavelRepository.findById(anyInt())).thenReturn(optionalVariavel);
        VariavelDTO response = variavelService.findById(ID).get();
        assertNotNull(response);
        assertEquals(VariavelDTO.class, response.getClass());
        assertEquals(response.getId(), ID);
        assertEquals(response.getMfgName(), MFG_NAME);
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