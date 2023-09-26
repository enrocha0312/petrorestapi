package com.mindsim.petroapi.services;


import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import com.mindsim.petroapi.entities.Medida;
import com.mindsim.petroapi.entities.Variavel;
import com.mindsim.petroapi.repositories.MedidaRepository;
import com.mindsim.petroapi.shared.dto.MedidaDTO;
import com.mindsim.petroapi.shared.dto.VariavelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedidaService {
    @Autowired
    private MedidaRepository medidaRepository;
    public List<MedidaDTO> findAll(){
        List<VariavelDTO> variavelDTOList = medidaRepository.findAll()
                .stream()
                .map(v->new ModelMapper().map(v.getVariavel(), VariavelDTO.class))
                .collect(Collectors.toList());
        List<MedidaDTO> medidaDTOList = medidaRepository.findAll()
                .stream()
                .map(m -> new ModelMapper().map(m, MedidaDTO.class))
                .collect(Collectors.toList());
        for(MedidaDTO m : medidaDTOList){
            m.setVariavelDTO(variavelDTOList.get(medidaDTOList.indexOf(m)));
        }
        return medidaDTOList;
    }
    public Optional<MedidaDTO> findById(MedidaId composeId){
        return Optional.of(new ModelMapper().map(medidaRepository.findById(composeId).get(), MedidaDTO.class));
    }
    public List<MedidaDTO> findByTagId(Integer tagId){
        List<MedidaDTO> medidaDTOList = medidaRepository.findByTagId(tagId)
                .stream()
                .map(m -> new ModelMapper().map(m, MedidaDTO.class))
                .collect(Collectors.toList());
        List<VariavelDTO> variavelList = medidaRepository.findByTagId(tagId)
                .stream()
                .map(v -> new ModelMapper().map(v.getVariavel(), VariavelDTO.class))
                .collect(Collectors.toList());
        for(MedidaDTO m : medidaDTOList){
            m.setVariavelDTO(variavelList.get(medidaDTOList.indexOf(m)));
        }
        return medidaDTOList;
    }
    public void deleteById(MedidaId composeId){
        medidaRepository.deleteById(composeId);
    }
    public MedidaDTO add(MedidaDTO medidaDto){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String agora = LocalDateTime.now().format(dateTimeFormatter);
        LocalDateTime dataAgora = LocalDateTime.parse(agora, dateTimeFormatter);
        medidaDto.setTimestamp(dataAgora);
        Medida m = new ModelMapper().map(medidaDto, Medida.class);
        m = medidaRepository.save(m);
        medidaDto.setVariavelDTO(new ModelMapper().map(m.getVariavel(), VariavelDTO.class));
        return medidaDto;
    }
    public MedidaDTO update(MedidaId composeId, MedidaDTO medidaDTO){
        medidaDTO.setTimestamp(composeId.getTimestamp());
        medidaDTO.setVariavelDTO(new ModelMapper().map(composeId.getVariavel(), VariavelDTO.class));
        Medida m = new ModelMapper().map(medidaDTO, Medida.class);
        medidaRepository.save(m);
        return medidaDTO;
    }
}
