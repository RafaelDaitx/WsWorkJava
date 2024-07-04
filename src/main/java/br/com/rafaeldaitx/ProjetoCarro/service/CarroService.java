package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Marca;
import br.com.rafaeldaitx.ProjetoCarro.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Carro save(Carro carro){
        return carroRepository.save(carro);
    }

}
