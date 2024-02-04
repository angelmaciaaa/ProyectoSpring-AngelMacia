package es.formulauno.f1.repository;

import es.formulauno.f1.dto.PilotoDTO;
import es.formulauno.f1.entity.Piloto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PilotoRepository extends JpaRepository<Piloto, Integer> {
    //@Query("SELECT p FROM Piloto p JOIN Escuderia e ON e.name=p.escuderia_name WHERE p.escuderia_name")
    @Query(value = "SELECT * FROM Piloto p WHERE p.escuderia_name LIKE :nameEscuderia GROUP BY p.dorsal HAVING p.salario=MAX(p.salario) limit 1", nativeQuery = true)
    Piloto getPilotoWithMoreSalaryByEscuderia(String nameEscuderia);

    @Query("SELECT p FROM Piloto p JOIN Escuderia e ON p.escuderia.name = e.name WHERE e.posCampeonatoConstructores = :posicionEscuderia AND p.salario > :salarioo")
    List<Piloto> getPilotoQuery(int posicionEscuderia, int salarioo);

    @Query("SELECT p FROM Piloto p JOIN Escuderia e ON p.escuderia.name = e.name JOIN TeamPrincipal t ON t.escuderia.name = e.name WHERE t.name LIKE :nameTP")
    List<Piloto> getPilotosByTeamPrincipal(String nameTP);

    @Query("SELECT new es.formulauno.f1.dto.PilotoDTO(e.name, AVG(p.posCampeonatoPilotos)) FROM Piloto p JOIN Escuderia e ON p.escuderia.name = e.name WHERE e.posCampeonatoConstructores = :posicionEscuderia")
    List<PilotoDTO> getAVGPosicionPilotoByPosicionEscuderia(int posicionEscuderia);
}