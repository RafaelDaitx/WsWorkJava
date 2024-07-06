package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import br.com.rafaeldaitx.ProjetoCarro.exceptions.ResourceNotFoundException;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Modelo;
import br.com.rafaeldaitx.ProjetoCarro.repository.CarroRepository;
import br.com.rafaeldaitx.ProjetoCarro.repository.ModeloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;


    @Autowired
    private ModeloRepository modeloRepository;

    private static final Logger logger = Logger.getLogger(CarroService.class.getName());

    public List<CarroDTO> findAll() {
        logger.info("Findig all cars");
        List<Carro> carros = carroRepository.findAll();
        List<CarroDTO> carroDTOs = new ArrayList<>();

        for (Carro carro : carros) {
            Modelo modelo = carro.getModelo();

            if (modelo == null)
                throw new EntityNotFoundException("Modelo is null for Carro ID: " + carro.getId());

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

    public CarroDTO save(CarroDTO carroDTO) {
        Modelo modeloCarro = modeloRepository.findById(carroDTO.getModelo_id())
                .orElseThrow(() -> new ResourceNotFoundException("Modelo not found with ID " + carroDTO.getModelo_id()));

        Carro carroSalvo = new Carro();

        carroSalvo.setTimestamp_cadastro(new Date());
        carroSalvo.setAno(carroDTO.getAno());
        carroSalvo.setCombustivel(carroDTO.getCombustivel());
        carroSalvo.setNum_portas(carroDTO.getNum_portas());
        carroSalvo.setCor(carroDTO.getCor());
        carroSalvo.setModelo(modeloCarro);

        carroRepository.save(carroSalvo);

        return carroDTO;
    }

    public CarroDTO update(Long id, CarroDTO carroDTO) {
        Carro carroEncontrado = carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro not found with id " + id));

        Modelo modeloCarro = modeloRepository.findById(carroDTO.getModelo_id())
                .orElseThrow(() -> new ResourceNotFoundException("Modelo not found with ID " + carroDTO.getModelo_id()));

        carroEncontrado.setAno(carroDTO.getAno());
        carroEncontrado.setCombustivel(carroDTO.getCombustivel());
        carroEncontrado.setNum_portas(carroDTO.getNum_portas());
        carroEncontrado.setCor(carroDTO.getCor());
        carroEncontrado.setModelo(modeloCarro);

        carroRepository.save(carroEncontrado);

        return new CarroDTO(
                carroEncontrado.getId(),
                carroEncontrado.getTimestamp_cadastro().getTime() / 1000, // Converting to Unix timestamp
                modeloCarro.getId(),
                carroEncontrado.getAno(),
                carroEncontrado.getCombustivel(),
                carroEncontrado.getNum_portas(),
                carroEncontrado.getCor(),
                modeloCarro.getNome(),
                modeloCarro.getValor_fipe()
        );
    }
    public Optional<CarroDTO> findViewById(Long id) {
        logger.info("Finding car with id: " + id);
        Optional<Carro> carro = Optional.ofNullable(carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID " + id)));

        Optional<CarroDTO> dto = Optional.of(new CarroDTO(
                carro.get().getId(),
                carro.get().getTimestamp_cadastro().getTime() / 1000, // Converting to Unix timestamp
                carro.get().getId(),
                carro.get().getAno(),
                carro.get().getCombustivel(),
                carro.get().getNum_portas(),
                carro.get().getCor(),
                carro.get().getModelo().getNome(),
                carro.get().getModelo().getValor_fipe()
        ));

        return dto;

    }

    public void delete(Long id) {
        logger.info("Deleting car with id: " + id);
        Optional<Carro> carroEncontrado = Optional.ofNullable(carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID " + id)));
        carroRepository.delete(carroEncontrado.get());
    }
}
