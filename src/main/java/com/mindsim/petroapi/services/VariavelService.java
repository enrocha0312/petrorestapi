package com.mindsim.petroapi.services;

import com.mindsim.petroapi.entities.Variavel;
import com.mindsim.petroapi.repositories.MedidaRepository;
import com.mindsim.petroapi.repositories.VariavelRepository;
import com.mindsim.petroapi.services.exceptions.DatabaseException;
import com.mindsim.petroapi.services.exceptions.ResourceNotFoundException;
import com.mindsim.petroapi.shared.dto.VariavelDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VariavelService {

    @Autowired
    private VariavelRepository variavelRepository;
    public List<VariavelDTO> findAll(){
        return variavelRepository
                .findAll()
                .stream()
                .map(v->new ModelMapper().map(v, VariavelDTO.class))
                .collect(Collectors.toList());
    }
    public Optional<VariavelDTO> findById(Integer id){
        Variavel v = variavelRepository.findById(id).get();
        return Optional.of(Optional.of(new ModelMapper().map(v, VariavelDTO.class))
                .orElseThrow(()->new ResourceNotFoundException(id)));
    }

    public void deleteById(Integer id){
        try {
            variavelRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public VariavelDTO add(VariavelDTO variavelDTO){
        variavelDTO.setId(null);
        Variavel v = new ModelMapper().map(variavelDTO, Variavel.class);
        v = variavelRepository.save(v);
        variavelDTO.setId(v.getId());
        return variavelDTO;
    }

    public VariavelDTO update(Integer id, VariavelDTO variavelDTO){
        try {
            variavelDTO.setId(id);
            Variavel variavel = new ModelMapper().map(variavelDTO, Variavel.class);
            variavelRepository.save(variavel);
            return variavelDTO;
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
