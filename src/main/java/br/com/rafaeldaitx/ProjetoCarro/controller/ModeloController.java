package br.com.rafaeldaitx.ProjetoCarro.controller;

import br.com.rafaeldaitx.ProjetoCarro.model.Modelo;
import br.com.rafaeldaitx.ProjetoCarro.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/carros/modelo")
public class ModeloController {
    @Autowired
    private ModeloService service;

    @GetMapping
    public List<Modelo> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE})
    public Modelo create(@RequestBody Modelo modelo) {
        return service.save(modelo);
    }
}
