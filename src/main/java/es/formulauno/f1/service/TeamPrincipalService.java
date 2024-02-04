package es.formulauno.f1.service;

import es.formulauno.f1.entity.Escuderia;
import es.formulauno.f1.entity.TeamPrincipal;
import es.formulauno.f1.exception.ErrorCode;
import es.formulauno.f1.exception.F1Exception;
import es.formulauno.f1.repository.TeamPrincipalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamPrincipalService {
    private final TeamPrincipalRepository repository;

    public TeamPrincipalService(TeamPrincipalRepository repository){
        this.repository = repository;
    }

    public List<TeamPrincipal> findAll() throws F1Exception {
       List<TeamPrincipal> teamPrincipalList = repository.findAll();
       if (!teamPrincipalList.isEmpty()){
           return teamPrincipalList;
       }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado datos.");
    }

    public TeamPrincipal findByName(Long id) throws F1Exception {
        Optional<TeamPrincipal> optionalTeamPrincipal = repository.findById(id);
        TeamPrincipal teamPrincipal = optionalTeamPrincipal.orElse(null);
        if (teamPrincipal != null){
            return teamPrincipal;
        }else throw new F1Exception(ErrorCode.ID_NOT_FOUND, "ID no encontrado, introduce uno diferente por favor.");
    }

    public List<TeamPrincipal> getTpByPresupuestoEscuderia(int presupuestoEscuderia) throws F1Exception {
        List<TeamPrincipal> teamPrincipalList = repository.getTpByPresupuestoEscuderia(presupuestoEscuderia);
        if (!teamPrincipalList.isEmpty()){
            return teamPrincipalList;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado Team Principals que su escudería tenga un presupuesto mayor al indicado.");
    }
}
