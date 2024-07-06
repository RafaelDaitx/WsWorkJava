package br.com.rafaeldaitx.ProjetoCarro.controller;

import br.com.rafaeldaitx.ProjetoCarro.data.MarcaDTO;
import br.com.rafaeldaitx.ProjetoCarro.data.ModeloDTO;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Modelo;
import br.com.rafaeldaitx.ProjetoCarro.service.ModeloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/carros/modelo")
public class ModeloController {
    @Autowired
    private ModeloService service;

    @GetMapping
    @Operation(
            summary = "Finds all models",
            description = "Finds all models",
            tags = {"Models"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModeloDTO.class)))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    )
            }
    )
    public List<Modelo> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    @Operation(
            summary = "Finds a model",
            description = "Finds a model",
            tags = {"Models"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ModeloDTO.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    )
            }
    )
    public Map<String, Optional<ModeloDTO>> findViewById(@PathVariable(value = "id") Long id){
        Optional<ModeloDTO> modelo = service.findViewById(id);
        Map<String, Optional<ModeloDTO>> response = new HashMap<>();
        response.put("modelo", modelo);
        return response;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Create a new model",
            description = "Create a new model",
            tags = {"Models"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ModeloDTO.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    )
            }
    )
    public Modelo create(@RequestBody Modelo modelo) {
        return service.save(modelo);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Updates a model",
            description = "Updates a model",
            tags = {"Models"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ModeloDTO.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    )
            }
    )
    public ModeloDTO update(@RequestBody ModeloDTO modeloDTO, @PathVariable Long id){
        return service.update(id, modeloDTO);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletes a model",
            description = "Deletes a model",
            tags = {"Models"},
            responses = {
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    )
            }
    )
    public void delete(@PathVariable(value = "id") Long id){
        service.delete(id);
    }
}
