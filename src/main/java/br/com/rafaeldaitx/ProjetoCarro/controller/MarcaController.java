package br.com.rafaeldaitx.ProjetoCarro.controller;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import br.com.rafaeldaitx.ProjetoCarro.data.MarcaDTO;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Marca;
import br.com.rafaeldaitx.ProjetoCarro.service.CarroService;
import br.com.rafaeldaitx.ProjetoCarro.service.MarcaService;
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
@RequestMapping("api/carros/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping
    @Operation(
            summary = "Finds all brands",
            description = "Finds all cars",
            tags = {"Brands"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MarcaDTO.class)))
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
    public List<Marca> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    @Operation(
            summary = "Finds a brand",
            description = "Finds a brand",
            tags = {"Brands"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MarcaDTO.class))
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
    public Map<String, Optional<MarcaDTO>> findViewById(@PathVariable(value = "id") Long id){
        Optional<MarcaDTO> marca = service.findViewById(id);
        Map<String, Optional<MarcaDTO>> response = new HashMap<>();
        response.put("marca", marca);
        return response;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Create a new brand",
            description = "Create a new brand",
            tags = {"Brands"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MarcaDTO.class))
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
    public Marca create(@RequestBody Marca marca) {
        return service.save(marca);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Updates a brand",
            description = "Updates a brand",
            tags = {"Brands"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MarcaDTO.class))
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
    public Marca update(@RequestBody Marca marca,  @PathVariable Long id){
        return service.update(id, marca);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletes a brand",
            description = "Deletes a brand",
            tags = {"Brands"},
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
