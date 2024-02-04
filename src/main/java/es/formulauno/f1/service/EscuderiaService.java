package es.formulauno.f1.service;

import es.formulauno.f1.controller.util.EscuderiaFieldSort;
import es.formulauno.f1.entity.Escuderia;
import es.formulauno.f1.exception.ErrorCode;
import es.formulauno.f1.exception.F1Exception;
import es.formulauno.f1.repository.EscuderiaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscuderiaService {
    private final EscuderiaRepository repository;

    public EscuderiaService(EscuderiaRepository repository) {
        this.repository = repository;
    }

    public List<Escuderia> findAll() throws F1Exception {
        List<Escuderia> escuderiaList = repository.findAll();
        if (!escuderiaList.isEmpty()){
            return escuderiaList;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado datos.");
    }

    public Escuderia findByName(String name) throws F1Exception {
        Optional<Escuderia> optionalEscuderia = repository.findById(name);
        Escuderia escuderia = optionalEscuderia.orElse(null);
        if (escuderia != null){
            return escuderia;
        }else throw new F1Exception(ErrorCode.ID_NOT_FOUND, "ID no encontrado, introduce otro diferente por favor.");
    }

    public Escuderia findEscuderiaMejorPosicion() throws F1Exception {
        Escuderia escuderia = repository.findFirstByOrderByPosCampeonatoConstructoresDesc();
        if (escuderia != null){
            return escuderia;
        }else throw new F1Exception(ErrorCode.ID_NOT_FOUND, "No se ha encontrado ninguna escudería.");
    }

    public Escuderia create(Escuderia escuderia) throws F1Exception {
        if (escuderia != null){
            return repository.save(escuderia);
        }else throw new F1Exception(ErrorCode.CAN_NOT_CREATE, "Ha ocurrido un error al crear la escudería, comprueba que los datos están bien introducidos.");
    }

    public List<Escuderia> findAllPageabled(Integer pageNo, Integer pageSize, EscuderiaFieldSort sortBy, Sort.Direction orderBy) throws F1Exception {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orderBy, sortBy.getField()));
        List<Escuderia> escuderiaList = repository.findAll(pageable).getContent();
        if (!escuderiaList.isEmpty()){
            return escuderiaList;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado datos");
    }

    public Escuderia update(Escuderia escuderia) throws F1Exception {
        if (escuderia.getName() != null){
            return repository.save(escuderia);
        }else throw new F1Exception(ErrorCode.ID_NOT_FOUND, "El campo nombre es obligatorio.");
    }

    public Escuderia putUpdate(Escuderia escuderia) throws F1Exception {
        if (escuderia.getName() != null){
            return repository.save(escuderia);
        }else throw new F1Exception(ErrorCode.ID_NOT_FOUND, "El campo nombre es obligatorio");
    }

    public List<Escuderia> getEscuderiasWithPilotoInTop3() throws F1Exception {
        List<Escuderia> escuderiaList = repository.getEscuderiasWithPilotoInTop3();
        if (!escuderiaList.isEmpty()){
            return escuderiaList;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "Datos no encontrados, ninguna escudería tiene algún nameEscuderia en el top 3.");
    }

    public void delete(String name){
        repository.deleteById(name);
    }
}
