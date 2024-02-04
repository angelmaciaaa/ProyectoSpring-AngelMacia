package es.formulauno.f1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "piloto")
public class Piloto {
    @Id
    private int dorsal;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "pos_campeonato_pilotos")
    private int posCampeonatoPilotos;

    @Column(nullable = false)
    private int salario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "escuderia_name")
    private Escuderia escuderia;
}
