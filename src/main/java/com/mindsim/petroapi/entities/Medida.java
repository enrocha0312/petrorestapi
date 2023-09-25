package com.mindsim.petroapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindsim.petroapi.entities.IdClasses.MedidaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name = "medida")
@NoArgsConstructor
@IdClass(MedidaId.class)
public class Medida implements Serializable {
    @NotEmpty
    private Double valor;
    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "_timestamp")
    @Id
    private LocalDateTime timestamp;
    @Id
    @ManyToOne
    @JoinColumn(name = "tagid")
    private Variavel variavel;
}
