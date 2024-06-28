package com.unisales.passagemdeonibus.domain.onibus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String placa;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "capacidade")
    private int capacidade;

    @JsonIgnore
    @OneToMany(mappedBy = "onibus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passagem> passagens;
}
