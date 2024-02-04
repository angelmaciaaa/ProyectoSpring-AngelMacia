package es.formulauno.f1.repository;

import es.formulauno.f1.entity.Escuderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EscuderiaRepository extends JpaRepository<Escuderia, String> {
    Escuderia findFirstByOrderByPosCampeonatoConstructoresDesc();

    @Query("SELECT e FROM Escuderia e JOIN Piloto p ON e.name = p.escuderia.name WHERE p.posCampeonatoPilotos<=3")
    List<Escuderia> getEscuderiasWithPilotoInTop3();

}
