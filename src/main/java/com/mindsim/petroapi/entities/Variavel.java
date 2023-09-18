package com.mindsim.petroapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name = "variavel")
@NoArgsConstructor
public class Variavel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tag;
    private String plataforma;
    private String name;
    @Column(name = "mfg_name")
    private String mfgName;
}
