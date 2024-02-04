package es.formulauno.f1.controller;

import es.formulauno.f1.dto.PilotoDTO;
import es.formulauno.f1.entity.Escuderia;
import es.formulauno.f1.entity.Piloto;
import es.formulauno.f1.exception.F1Exception;
import es.formulauno.f1.service.PilotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PilotoRestController {
    private final PilotoService pilotoService;

    public PilotoRestController(PilotoService pilotoService) {
        this.pilotoService = pilotoService;
    }

    @GetMapping("/pilotos")
    public ResponseEntity<List<Piloto>> getAllPilotos() throws F1Exception {
        return new ResponseEntity<>(pilotoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/piloto/{id}")
    public ResponseEntity<Piloto> getByDorsal(@PathVariable("id") int dorsal) throws F1Exception {
        return new ResponseEntity<>(pilotoService.findByDorsal(dorsal), HttpStatus.OK);
    }

    @GetMapping("/piloto/mayorSalario/{escuderia_name}")
    public ResponseEntity<Piloto> getPilotoWithMoreSalaryByEscuderia(@PathVariable("escuderia_name") String escuderiaName) throws F1Exception {
        return new ResponseEntity<>(pilotoService.getPilotoWithMoreSalaryByEscuderia(escuderiaName), HttpStatus.OK);
    }

    @GetMapping("/piloto/{posCampeonatoEscuderia}/salarygt/{salario}")
    public ResponseEntity<List<Piloto>> getPilotosByPosEscuderiaANDBySalary(@PathVariable("posCampeonatoEscuderia") int posicionEscuderia, @PathVariable("salario") int salario) throws F1Exception {
        return new ResponseEntity<>(pilotoService.getPilotosByPosEscuderiaANDBySalary(posicionEscuderia, salario), HttpStatus.OK);
    }

    @DeleteMapping("/piloto/delete/{dorsal}")
    public ResponseEntity<String> delete(@PathVariable("dorsal") int dorsal) {
        pilotoService.delete(dorsal);
        return new ResponseEntity<>("El piloto ha sido eliminado", HttpStatus.OK);
    }

    @GetMapping("/piloto/teamprincipalname/{nametp}")
    public ResponseEntity<List<Piloto>> getPilotosByTeamPrincipal(@PathVariable("nametp") String nametp) throws F1Exception {
        return new ResponseEntity<>(pilotoService.getPilotosByTeamPrincipal(nametp), HttpStatus.OK);
    }

    @GetMapping("/piloto/avgposicion/{posicionEscuderia}")
    public ResponseEntity<List<PilotoDTO>> getAVGPosicionPilotoByPosicionEscuderia(@PathVariable("posicionEscuderia") int posicionEscuderia) throws F1Exception {
        return new ResponseEntity<>(pilotoService.getAVGPosicionPilotoByPosicionEscuderia(posicionEscuderia), HttpStatus.OK);
    }

}
