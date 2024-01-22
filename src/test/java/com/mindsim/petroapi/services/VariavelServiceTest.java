package com.mindsim.petroapi.services;

import com.mindsim.petroapi.entities.Variavel;
import com.mindsim.petroapi.repositories.VariavelRepository;
import com.mindsim.petroapi.services.exceptions.ResourceNotFoundException;
import com.mindsim.petroapi.shared.dto.VariavelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
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
    void findAllWorksSuccessfully() {
        Variavel variavel2 = Variavel.builder().
                tag("tag2").mfgName("mfg2").plataforma("plataforma2").name("nome2").id(2).build();
        when(variavelRepository.findAll()).thenReturn(List.of(variavel, variavel2));
        List<VariavelDTO> response = variavelService.findAll();
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(1, response.get(0).getId());
        assertEquals(2, response.get(1).getId());
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
    void findByIdFindsNoObject(){
        when(variavelRepository.findById(anyInt())).thenThrow(new ResourceNotFoundException(ID));
        try{
            variavelService.findById(ID);
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Resource not found. Id "+ID, ex.getMessage());
        }
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