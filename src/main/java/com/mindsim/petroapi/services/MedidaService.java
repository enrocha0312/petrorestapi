package com.mindsim.petroapi.services;

import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import com.mindsim.petroapi.entities.Medida;
import com.mindsim.petroapi.repositories.MedidaRepository;
import com.mindsim.petroapi.shared.dto.MedidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedidaService {
    @Autowired
    private MedidaRepository medidaRepository;
    public List<MedidaDTO> findAll(){
        return medidaRepository.findAll()
                .stream()
                .map(m -> new ModelMapper().map(m, MedidaDTO.class))
                .collect(Collectors.toList());
    }
    public Optional<MedidaDTO> findById(MedidaId composeId){
        return Optional.of(new ModelMapper().map(medidaRepository.findById(composeId).get(), MedidaDTO.class));
    }
    public Set<MedidaDTO> findByTagId(Integer tagId){
        return medidaRepository.findByTagId(tagId)
                .stream()
                .map(m -> new ModelMapper().map(m, MedidaDTO.class))
                .collect(Collectors.toSet());
    }
    public void deleteById(MedidaId composeId){
        medidaRepository.deleteById(composeId);
    }
    public MedidaDTO add(MedidaDTO medidaDto){
        medidaDto.setTagId(null);
        medidaDto.setTimestamp(LocalDateTime.now());
        Medida m = new ModelMapper().map(medidaDto, Medida.class);
        m = medidaRepository.save(m);
        medidaDto.setTagId(m.getTagId());
        medidaDto.setTimestamp(m.getTimestamp());
        return medidaDto;
    }
    public MedidaDTO update(MedidaId composeId, MedidaDTO medidaDTO){
        medidaDTO.setTimestamp(composeId.getTimestamp());
        medidaDTO.setTagId(composeId.getTagId());
        Medida m = new ModelMapper().map(medidaDTO, Medida.class);
        medidaRepository.save(m);
        return medidaDTO;
    }
}
