package com.mindsim.petroapi.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluxRequest implements Serializable {
    private LocalDateTime time;
    private Double value;
    private String field;
    private String measurement;
    private String mfg;
    private String name;
    private String plataforma;
}
