package com.mindsim.petroapi.shared;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedidaRequest implements Serializable {
    private Double valor;
    private VariavelRequest variavelRequest;
    private Integer tagId;
}
