package br.com.rafaeldaitx.ProjetoCarro.controller;

import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Marca;
import br.com.rafaeldaitx.ProjetoCarro.service.CarroService;
import br.com.rafaeldaitx.ProjetoCarro.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carros/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping
    public List<Marca> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE})
    public Marca create(@RequestBody Marca marca) {
        return service.save(marca);
    }

}
