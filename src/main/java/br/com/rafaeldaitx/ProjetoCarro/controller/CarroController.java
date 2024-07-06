package br.com.rafaeldaitx.ProjetoCarro.controller;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.service.CarroService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping
    @Operation(
            summary = "Finds all cars",
            description = "Finds all cars",
            tags = {"Cars"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CarroDTO.class)))
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
    public Map<String, List<CarroDTO>> findAllCars() {
        List<CarroDTO> carros = service.findAll();
        Map<String, List<CarroDTO>> response = new HashMap<>();
        response.put("cars", carros);
        return response;
    }

    @PostMapping
    @Operation(
            summary = "Create a new car",
            description = "Create a new car",
            tags = {"Cars"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CarroDTO.class))
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
    public Carro create(@RequestBody Carro carro) {
        return service.save(carro);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Updates a car",
            description = "Updates a car",
            tags = {"Cars"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CarroDTO.class))
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
    public ResponseEntity<CarroDTO> update(@RequestBody CarroDTO carro, @PathVariable Long id){
        return ResponseEntity.ok(service.update(id, carro));
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    @Operation(
        summary = "Finds a car",
        description = "Finds a car",
        tags = {"Cars"},
        responses = {
                @ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = @Content(schema = @Schema(implementation = CarroDTO.class))
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
    public Map<String, Optional<CarroDTO>> findViewById(@PathVariable(value = "id") Long id){
        Optional<CarroDTO> carro = service.findViewById(id);
        Map<String, Optional<CarroDTO>> response = new HashMap<>();
        response.put("car", carro);
        return response;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletes a car",
            description = "Deletes a car",
            tags = {"Cars"},
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
