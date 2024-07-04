package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.model.Modelo;
import br.com.rafaeldaitx.ProjetoCarro.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;


    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }

    public Modelo save(Modelo modelo){
        return modeloRepository.save(modelo);
    }
}
