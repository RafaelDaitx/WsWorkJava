package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Modelo;
import br.com.rafaeldaitx.ProjetoCarro.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> findAll() {
        List<Carro> carros = carroRepository.findAll();
        List<CarroDTO> carroDTOs = new ArrayList<>();

        for (Carro carro : carros) {
            Modelo modelo = carro.getModelo();
            if (modelo == null) {
                // Handle the case where the modelo is null
                System.out.println("Modelo is null for Carro ID: " + carro.getId());
                continue;
            }
            CarroDTO dto = new CarroDTO(
                    carro.getId(),
                    carro.getTimestamp_cadastro().getTime() / 1000, // Converting to Unix timestamp
                    modelo.getId(),
                    carro.getAno(),
                    carro.getCombustivel(),
                    carro.getNum_portas(),
                    carro.getCor(),
                    modelo.getNome(),
                    modelo.getValor_fipe()
            );
            carroDTOs.add(dto);
        }

        return carroDTOs;
    }

    public Carro save(Carro carro){
        return carroRepository.save(carro);
    }

    public Carro update(Long id, Carro carro){
        Optional<Carro> carroEncontrado = carroRepository.findById(carro.getId());
        carroEncontrado.get().setId(id);
        return carroRepository.save(carro);
    }

    public Optional<CarroDTO> findViewById(Long id) {
        Optional<Carro> carros = carroRepository.findById(id);

        Optional<CarroDTO> dto = Optional.of(new CarroDTO(
                carros.get().getId(),
                carros.get().getTimestamp_cadastro().getTime() / 1000, // Converting to Unix timestamp
                carros.get().getId(),
                carros.get().getAno(),
                carros.get().getCombustivel(),
                carros.get().getNum_portas(),
                carros.get().getCor(),
                carros.get().getModelo().getNome(),
                carros.get().getModelo().getValor_fipe()
        ));

        return dto;

    }

    public void delete(Long id) {
        Optional<Carro> carroEncontrado = carroRepository.findById(id);
        carroRepository.delete(carroEncontrado.get());
    }
}
