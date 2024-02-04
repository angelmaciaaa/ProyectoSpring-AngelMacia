package es.formulauno.f1.service;

import es.formulauno.f1.dto.PilotoDTO;
import es.formulauno.f1.entity.Piloto;
import es.formulauno.f1.exception.ErrorCode;
import es.formulauno.f1.exception.F1Exception;
import es.formulauno.f1.repository.PilotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PilotoService {
    private final PilotoRepository repository;

    public PilotoService(PilotoRepository repository){
        this.repository = repository;
    }

    public List<Piloto> findAll() throws F1Exception {
        List<Piloto> pilotoList = repository.findAll();
        if (!pilotoList.isEmpty()){
            return pilotoList;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado datos.");

    }

    public Piloto findByDorsal(int dorsal) throws F1Exception {
        Optional<Piloto> optionalPiloto = repository.findById(dorsal);
        Piloto piloto = optionalPiloto.orElse(null);
        if (piloto != null){
            return piloto;
        }else throw new F1Exception(ErrorCode.ID_NOT_FOUND, "ID no encontrado, introduce uno diferente por favor.");
    }

    public Piloto getPilotoWithMoreSalaryByEscuderia(String nameEscuderia) throws F1Exception {
        Piloto piloto = repository.getPilotoWithMoreSalaryByEscuderia(nameEscuderia);
        if (piloto != null){
            return piloto;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado datos buscando por el valor introducido, prueba con otro.");
    }

    public List<Piloto> getPilotosByPosEscuderiaANDBySalary(int posicionEscuderia, int salarioo) throws F1Exception {
        List<Piloto> pilotosList = repository.getPilotoQuery(posicionEscuderia, salarioo);
        if (!pilotosList.isEmpty()){
            return pilotosList;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado datos buscando por el valor introducido, prueba con otro");
    }

    public void delete(int dorsal){
        repository.deleteById(dorsal);
    }

    public List<Piloto> getPilotosByTeamPrincipal(String nameTP) throws F1Exception {
        List<Piloto> pilotoList = repository.getPilotosByTeamPrincipal(nameTP);
        if (!pilotoList.isEmpty()){
            return pilotoList;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado datos buscando por el valor introducido, prueba con otro.");
    }

    public List<PilotoDTO> getAVGPosicionPilotoByPosicionEscuderia(int posicionEscuderia) throws F1Exception {
        List<PilotoDTO> pilotoDTOList = repository.getAVGPosicionPilotoByPosicionEscuderia(posicionEscuderia);
        if (!pilotoDTOList.isEmpty()){
            return pilotoDTOList;
        }else throw new F1Exception(ErrorCode.DATA_NOT_FOUND, "No se han encontrado datos buscando por el valor introducido, prueba con otro.");
    }
}
