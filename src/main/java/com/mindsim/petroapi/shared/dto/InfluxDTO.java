package com.mindsim.petroapi.shared.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class InfluxDTO {
    private Integer id;
    private LocalDateTime time;
    private Double value;
    private String field;
    private String measurement;
    private String mfg;
    private String name;
    private String plataforma;
}
