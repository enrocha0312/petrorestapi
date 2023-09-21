package com.mindsim.petroapi.entities.IdClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedidaId {
    private LocalDateTime timestamp;
    private Integer tagId;
}
