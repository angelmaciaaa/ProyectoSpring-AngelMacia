package es.formulauno.f1.controller;

import es.formulauno.f1.entity.Escuderia;
import es.formulauno.f1.entity.TeamPrincipal;
import es.formulauno.f1.exception.F1Exception;
import es.formulauno.f1.service.TeamPrincipalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TeamPrincipalRestController {
    private final TeamPrincipalService teamPrincipalService;


    public TeamPrincipalRestController(TeamPrincipalService teamPrincipalService) {
        this.teamPrincipalService = teamPrincipalService;
    }

    @GetMapping("/teamprincipals")
    public ResponseEntity<List<TeamPrincipal>> getAllTeamPrincipals() throws F1Exception {
        return new ResponseEntity<>(teamPrincipalService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/teamprincipal/id/{id}")
    public ResponseEntity<TeamPrincipal> getByName(@PathVariable("id")Long id) throws F1Exception {
        return new ResponseEntity<>(teamPrincipalService.findByName(id), HttpStatus.OK);
    }

    @GetMapping("/teamprincipal/presupuesto/{presupuestoEscuderia}")
    public ResponseEntity<List<TeamPrincipal>> getTpByPresupuestoEscuderia(@PathVariable("presupuestoEscuderia") int presupuesto) throws F1Exception {
        return new ResponseEntity<>(teamPrincipalService.getTpByPresupuestoEscuderia(presupuesto), HttpStatus.OK);
    }
}
