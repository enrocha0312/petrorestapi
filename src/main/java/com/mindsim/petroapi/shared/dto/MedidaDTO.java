package com.mindsim.petroapi.shared.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedidaDTO implements Serializable {
    private Double valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private VariavelDTO variavelDTO;
}
