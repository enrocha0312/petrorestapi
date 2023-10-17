package com.mindsim.petroapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "influx_dados_enviados")
public class Influx implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "_time")
    private LocalDateTime time;
    @NotEmpty
    private Double value;
    private String field;
    private String measurement;
    private String mfg;
    @NotEmpty
    private String name;
    private String plataforma;
}
