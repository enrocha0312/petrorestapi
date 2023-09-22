package com.mindsim.petroapi.entities.IdClasses;

import com.mindsim.petroapi.entities.Variavel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedidaId {
    private Instant timestamp;
    private Integer tagId;
}
