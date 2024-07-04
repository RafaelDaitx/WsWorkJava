package br.com.rafaeldaitx.ProjetoCarro.controller;

import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping
    public List<Carro> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Carro create(@RequestBody Carro carroVO) {
        return service.save(carroVO);
    }

}
