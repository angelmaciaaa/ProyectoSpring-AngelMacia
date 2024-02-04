package es.formulauno.f1.controller;

import es.formulauno.f1.controller.util.EscuderiaFieldSort;
import es.formulauno.f1.entity.Escuderia;
import es.formulauno.f1.exception.F1Exception;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Escuderia API", description = "Spring application with spring-boot 3.x")
public interface EscuderiaAPI {

    @Operation(summary = "Retrieve a list of Escuderias",
            description = "Get all the Escuderias that are created. The response is a list of Escuderias objects",
            tags = {"get", "escuderia"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Escuderia.class))}),
            @ApiResponse(responseCode = "500", description = "Data not found",
                    content = {@Content(schema = @Schema())})
    }
    )
    ResponseEntity<List<Escuderia>> getAllEscuderias() throws F1Exception;


    @Operation(summary = "Retrieve a Escuderia by his name",
            description = "Get an Escuderia that is created putting his name. The response is an Escuderia object",
            tags = {"get", "escuderia"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Escuderia.class))}),
            @ApiResponse(responseCode = "500", description = "Data not found",
                    content = {@Content(schema = @Schema())})
    }
    )
    ResponseEntity<Escuderia> getByName(@PathVariable("name") String name) throws F1Exception;


    @Operation(summary = "Retrieve the Escuderia by Position",
            description = "Get the Escuderia on the best position in the championship. The response is an Escuderia object",
            tags = {"get", "escuderia"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Escuderia.class))}),
            @ApiResponse(responseCode = "500", description = "Data not found",
                    content = {@Content(schema = @Schema())})
    }
    )
    ResponseEntity<Escuderia> getEscuderiaMejorPosicion() throws F1Exception;


    @Operation(summary = "Create an Escuderia",
            description = "Create a Escuderia putting de data. The response is the Escuderia object created.",
            tags = {"post", "escuderia"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Escuderia.class))}),
            @ApiResponse(responseCode = "400", description = "You only can put the 'presupuesto' and 'posCampeonatoConstructores'" +
                    " attributes from a Escuderia.", content = {@Content(schema = @Schema())})
    }
    )
    ResponseEntity<Escuderia> newEscuderia(@RequestBody Escuderia escuderia) throws F1Exception;


    @Operation(summary = "Retrieve a list of Escuderias",
            description = "Get all the Escuderias that are created. The response is a list of Escuderias objects",
            tags = {"get", "escuderia"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Escuderia.class))}),
            @ApiResponse(responseCode = "500", description = "Data not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "One parameter is empty",
                    content = {@Content(schema = @Schema())})
    }
    )
    ResponseEntity<List<Escuderia>> getEscuderiaPageable(@RequestParam(defaultValue = "1") Integer pageNo,
                                                         @RequestParam(defaultValue = "3") Integer pageSize,
                                                         @RequestParam(required = false, defaultValue = "posCampeonatoConstructores") EscuderiaFieldSort sortBy,
                                                         @RequestParam(defaultValue = "ASC") Sort.Direction orderBy) throws F1Exception;


    @Operation(summary = "Update an Escuderia",
            description = "Update the Escuderia depending on what 'name' you put. The response is the Escuderia updated",
            tags = {"patch", "escuderia"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Escuderia.class))}),
            @ApiResponse(responseCode = "400", description = "You only can update the 'presupuesto' and 'posCampeonatoConstructores'" +
                    " attributes from a Escuderia.", content = {@Content(schema = @Schema())})
    }
    )
    ResponseEntity<Escuderia> update(@RequestBody Escuderia escuderia);


    @Operation(summary = "Update an Escuderia",
            description = "Update the Escuderia depending on what 'name' you put. The response is the Escuderia updated",
            tags = {"put", "escuderia"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Escuderia.class))}),
            @ApiResponse(responseCode = "400", description = "You only can update the 'presupuesto' and 'posCampeonatoConstructores'" +
                    " attributes from a Escuderia.", content = {@Content(schema = @Schema())})
    }
    )
    ResponseEntity<Escuderia> putUpdate(@RequestBody Escuderia escuderia);


    @Operation(summary = "Retrieve a list of Escuderias",
            description = "Get all the Escuderias that have at least one driver in the top 3 of the championship. The response is a list of Escuderias objects",
            tags = {"get", "escuderia"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Escuderia.class))}),
            @ApiResponse(responseCode = "500", description = "Data not found",
                    content = {@Content(schema = @Schema())})
    }
    )
    ResponseEntity<List<Escuderia>> getEscuderiasWithPilotoInTop3() throws F1Exception;
}
