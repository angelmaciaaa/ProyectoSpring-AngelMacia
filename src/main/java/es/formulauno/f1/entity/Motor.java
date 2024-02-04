package es.formulauno.f1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "motor")
public class Motor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = true)
    private int km;

    @ManyToMany(mappedBy = "motorList")
    @ToString.Exclude
    private List<Escuderia> escuderias;
}
