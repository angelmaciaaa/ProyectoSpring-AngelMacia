package es.formulauno.f1.controller;

import es.formulauno.f1.controller.util.EscuderiaFieldSort;
import es.formulauno.f1.entity.Escuderia;
import es.formulauno.f1.exception.F1Exception;
import es.formulauno.f1.service.EscuderiaService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EscuderiaRestController implements EscuderiaAPI{
    private final EscuderiaService escuderiaService;

    public EscuderiaRestController(EscuderiaService escuderiaService){
        this.escuderiaService = escuderiaService;
    }

    @GetMapping("/escuderias")
    public ResponseEntity<List<Escuderia>> getAllEscuderias() throws F1Exception {
        return new ResponseEntity<>(escuderiaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/escuderia/{name}")
    public ResponseEntity<Escuderia> getByName(@PathVariable("name") String name) throws F1Exception {
        return new ResponseEntity<>(escuderiaService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/escuderia/mejorposicion")
    public ResponseEntity<Escuderia> getEscuderiaMejorPosicion() throws F1Exception {
        return new ResponseEntity<>(escuderiaService.findEscuderiaMejorPosicion(), HttpStatus.OK);
    }

    @PostMapping("/escuderia")
    public ResponseEntity<Escuderia> newEscuderia(@RequestBody Escuderia escuderia) throws F1Exception {
        Escuderia newEscuderia = escuderiaService.create(escuderia);
        return new ResponseEntity<>(newEscuderia, HttpStatus.CREATED);
    }

    @GetMapping("/escuderiapageable")
    public ResponseEntity<List<Escuderia>> getEscuderiaPageable(@RequestParam(defaultValue = "1") Integer pageNo,
                                                                @RequestParam(defaultValue = "3") Integer pageSize,
                                                                @RequestParam(required = false, defaultValue = "posCampeonatoConstructores") EscuderiaFieldSort sortBy,
                                                                @RequestParam(defaultValue = "ASC") Sort.Direction orderBy) throws F1Exception {
        return new ResponseEntity<>(escuderiaService.findAllPageabled(pageNo-1, pageSize, sortBy, orderBy), HttpStatus.OK);
    }

    @PatchMapping("/escuderia/actualizar")
    public ResponseEntity<Escuderia> update(@RequestBody Escuderia escuderia){
        try{
            Escuderia escuderiaUpdated = escuderiaService.update(escuderia);
            return new ResponseEntity<>(escuderia, HttpStatus.OK);
        } catch (F1Exception e) {
            return new ResponseEntity<>(escuderia, HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping("/escuderia/actualizarconput")
    public ResponseEntity<Escuderia> putUpdate(@RequestBody Escuderia escuderia){
        try{
            Escuderia escuderiaUpdated = escuderiaService.putUpdate(escuderia);
            return new ResponseEntity<>(escuderia, HttpStatus.OK);
        } catch (F1Exception e) {
            return new ResponseEntity<>(escuderia, HttpStatus.NOT_MODIFIED);
        }    }

    @GetMapping("/escuderia/topthree")
    public ResponseEntity<List<Escuderia>> getEscuderiasWithPilotoInTop3() throws F1Exception {
        return new ResponseEntity<>(escuderiaService.getEscuderiasWithPilotoInTop3(), HttpStatus.OK);
    }
}
