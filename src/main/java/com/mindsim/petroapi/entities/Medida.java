package com.mindsim.petroapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name = "medida")
@NoArgsConstructor
@IdClass(MedidaId.class)
public class Medida implements Serializable {
    private Double valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    @Column(name = "_timestamp")
    @Id
    private LocalDateTime timestamp;
    @Id
    @Column(name = "tagid")
    private Integer tagId;
    @ManyToOne
    @JoinColumn(name = "tagid")
    private Variavel variavel;
}
