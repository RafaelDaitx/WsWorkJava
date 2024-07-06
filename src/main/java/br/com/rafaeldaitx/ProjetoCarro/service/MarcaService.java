package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import br.com.rafaeldaitx.ProjetoCarro.data.MarcaDTO;
import br.com.rafaeldaitx.ProjetoCarro.exceptions.ResourceNotFoundException;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Marca;
import br.com.rafaeldaitx.ProjetoCarro.repository.CarroRepository;
import br.com.rafaeldaitx.ProjetoCarro.repository.MarcaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    private static final Logger logger = Logger.getLogger(CarroService.class.getName());

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca save(Marca marca){
        return marcaRepository.save(marca);
    }

    public Optional<MarcaDTO> findViewById(Long id) {
        logger.info("Finding car with id: " + id);
        Optional<Marca> marca = Optional.ofNullable(marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID " + id)));

        Optional<MarcaDTO> dto = Optional.of(new MarcaDTO(
                marca.get().getId(),
                marca.get().getNomeMarca()
        ));

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting car with id: " + id);
        Optional<Marca> marcaEncontrada = Optional.ofNullable(marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with ID " + id)));

        marcaRepository.delete(marcaEncontrada.get());
    }

    public Marca update(Long id, Marca marca) {
        Optional<Marca> marcaEncontrada = Optional.ofNullable(marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with ID " + id)));

            Marca marcaAtualizada = marcaEncontrada.get();
            marcaAtualizada.setNomeMarca(marca.getNomeMarca());

            return marcaRepository.save(marcaAtualizada);
    }
}
