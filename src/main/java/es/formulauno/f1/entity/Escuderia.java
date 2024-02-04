package es.formulauno.f1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "escuderia")
public class Escuderia {
    @Id
    private String name;

    @Column(nullable = false)
    private int presupuesto;

    @Column(nullable = false, name = "pos_campeonato_constructores")
    private int posCampeonatoConstructores;

    @JsonManagedReference
    @OneToOne(mappedBy = "escuderia", cascade = CascadeType.REMOVE)
    private TeamPrincipal teamPrincipal;

    @JsonManagedReference
    @OneToMany(mappedBy = "escuderia", cascade = CascadeType.ALL)
    private List<Piloto> pilotos;

    @ManyToMany
    @JoinTable(name = "escuderia_motor", joinColumns = @JoinColumn(name = "escuderia_name"),
            inverseJoinColumns = @JoinColumn(name = "motor_id")
    )
    @ToString.Exclude
    private List<Motor> motorList = new ArrayList<>();
}
