package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Marca;
import br.com.rafaeldaitx.ProjetoCarro.repository.CarroRepository;
import br.com.rafaeldaitx.ProjetoCarro.repository.MarcaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;


    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca save(Marca marca){
        return marcaRepository.save(marca);
    }
}
