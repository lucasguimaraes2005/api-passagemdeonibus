package com.unisales.passagemdeonibus.domain.passagem;
import com.unisales.passagemdeonibus.domain.onibus.Onibus;
import com.unisales.passagemdeonibus.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "passagem")
@Entity(name = "passagem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Passagem {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_onibus", referencedColumnName = "id")
    private Onibus onibus;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "data_viagem")
    private java.sql.Date dataViagem;

    @Column(name = "hora_viagem")
    private java.sql.Time horaViagem;

    @Column(name = "preco")
    private double preco;
}
