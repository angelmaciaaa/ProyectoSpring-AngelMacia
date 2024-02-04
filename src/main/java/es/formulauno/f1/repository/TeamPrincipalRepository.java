package es.formulauno.f1.repository;

import es.formulauno.f1.entity.Escuderia;
import es.formulauno.f1.entity.TeamPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamPrincipalRepository extends JpaRepository<TeamPrincipal, Long> {
    @Query("SELECT t FROM TeamPrincipal t JOIN Escuderia e ON t.escuderia.name = e.name WHERE e.presupuesto > :presupuesto")
    List<TeamPrincipal> getTpByPresupuestoEscuderia(int presupuesto);
}
