package com.mindsim.petroapi.shared;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedidaResponse implements Serializable {
    private Double valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant timestamp;
    private VariavelResponse variavelResponse;
    private Integer tagId;
}
