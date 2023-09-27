package com.mindsim.petroapi.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@Table(name = "variavel")
@NoArgsConstructor
@Builder
public class Variavel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tag;
    private String plataforma;
    private String name;
    @Column(name = "mfg_name")
    private String mfgName;
}
