package com.mindsim.petroapi.entities.IdClasses;

import com.mindsim.petroapi.entities.Variavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedidaId {
    private LocalDateTime timestamp;
    private Variavel variavel;
}
