package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.data.MarcaDTO;
import br.com.rafaeldaitx.ProjetoCarro.data.ModeloDTO;
import br.com.rafaeldaitx.ProjetoCarro.mapper.DozerMapper;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Marca;
import br.com.rafaeldaitx.ProjetoCarro.model.Modelo;
import br.com.rafaeldaitx.ProjetoCarro.repository.MarcaRepository;
import br.com.rafaeldaitx.ProjetoCarro.repository.ModeloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    private static final Logger logger = Logger.getLogger(CarroService.class.getName());

    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }

    public Modelo save(Modelo modelo){
        return modeloRepository.save(modelo);
    }

    public Optional<ModeloDTO> findViewById(Long id) {
        logger.info("Finding Modelo with id: " + id);
        Optional<Modelo> modelo = modeloRepository.findById(id);

        Optional<ModeloDTO> dto = Optional.of(new ModeloDTO(
               modelo.get().getId(),
                modelo.get().getNome(),
                modelo.get().getValor_fipe(),
                modelo.get().getMarca().getId(),
                modelo.get().getMarca().getNomeMarca()
        ));

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting Modelo with id: " + id);
        Optional<Modelo> modeloEncontrado = modeloRepository.findById(id);
        modeloRepository.delete(modeloEncontrado.get());
    }

    public ModeloDTO update(Long id, ModeloDTO modeloDTO){
        var modeloEncontrado = modeloRepository.findById(id);

        if (modeloEncontrado.isPresent()) {
            Modelo modeloAtualizado = modeloEncontrado.get();
            modeloAtualizado.setNome(modeloDTO.getNome());
            modeloAtualizado.setValor_fipe(modeloDTO.getValor_fipe());

            if(modeloDTO.getMarca_id() != null){
                var marcaAtualizada = marcaRepository.findById(modeloDTO.getMarca_id());

                if(marcaAtualizada.isPresent()) modeloAtualizado.setMarca(marcaAtualizada.get());

            }

            Modelo modeloSalvo = modeloRepository.save(modeloAtualizado);

            ModeloDTO modeloDtoConvertido2 = new ModeloDTO(
                    modeloSalvo.getId(),
                    modeloSalvo.getNome(),
                    modeloSalvo.getValor_fipe(),
                    modeloSalvo.getMarca().getId(),
                    modeloSalvo.getMarca().getNomeMarca()
            );
            return modeloDtoConvertido2;
        } else {
            throw new EntityNotFoundException("Carro not found with id " + id);
        }
    }
}
