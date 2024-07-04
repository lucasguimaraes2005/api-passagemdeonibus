package com.unisales.passagemdeonibus.domain.onibus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.unisales.passagemdeonibus.domain.passagem.Passagem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "onibus")
@Entity(name = "onibus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Onibus {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "placa")
    @JsonSetter
    private String placa;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "capacidade")
    private int capacidade;

    @JsonIgnore
    @OneToMany(mappedBy = "onibus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passagem> passagens;

    @JsonCreator
    public Onibus(@JsonProperty("placa") String placa) {
        this.placa = placa;
    }
}
