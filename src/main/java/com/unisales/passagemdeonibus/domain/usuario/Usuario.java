package com.unisales.passagemdeonibus.domain.usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unisales.passagemdeonibus.domain.passagem.Passagem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "usuario")
@Entity(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Getter
@Setter
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sexo")
    private char sexo;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passagem> passagens;
}
