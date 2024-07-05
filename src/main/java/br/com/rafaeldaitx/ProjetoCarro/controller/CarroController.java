package br.com.rafaeldaitx.ProjetoCarro.controller;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, List<CarroDTO>> findAllCars() {
        List<CarroDTO> carros = service.findAll();
        Map<String, List<CarroDTO>> response = new HashMap<>();
        response.put("cars", carros);
        return response;
    }

    @PostMapping
    public Carro create(@RequestBody Carro carro) {
        return service.save(carro);
    }

    @PutMapping("{id}")
    public Carro update(@RequestBody Carro carro,  @PathVariable Long id){
        return service.update(id, carro);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Map<String, Optional<CarroDTO>> findViewById(@PathVariable(value = "id") Long id){
        Optional<CarroDTO> carro = service.findViewById(id);
        Map<String, Optional<CarroDTO>> response = new HashMap<>();
        response.put("car", carro);
        return response;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(value = "id") Long id){
        service.delete(id);
    }
}
