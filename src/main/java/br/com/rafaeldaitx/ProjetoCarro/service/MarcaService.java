package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import br.com.rafaeldaitx.ProjetoCarro.data.MarcaDTO;
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
        Optional<Marca> marca = marcaRepository.findById(id);

        Optional<MarcaDTO> dto = Optional.of(new MarcaDTO(
                marca.get().getNomeMarca()
        ));

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting car with id: " + id);
        Optional<Marca> marcaEncontrada = marcaRepository.findById(id);

        if(marcaEncontrada.isEmpty()) throw new EntityNotFoundException("Marca not found with id " + id);
        marcaRepository.delete(marcaEncontrada.get());
    }

    public Marca update(Long id, Marca marca) {
        Optional<Marca> marcaEncontrada = marcaRepository.findById(id);

        if (marcaEncontrada.isPresent()) {
            Marca marcaAtualizada = marcaEncontrada.get();
            marcaAtualizada.setNomeMarca(marca.getNomeMarca());

            return marcaRepository.save(marcaAtualizada);
        } else {
            throw new EntityNotFoundException("Marca not found with id " + id);
        }
    }
}
