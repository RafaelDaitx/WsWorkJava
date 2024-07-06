package br.com.rafaeldaitx.ProjetoCarro.service;

import br.com.rafaeldaitx.ProjetoCarro.data.ModeloDTO;
import br.com.rafaeldaitx.ProjetoCarro.exceptions.ResourceNotFoundException;
import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import br.com.rafaeldaitx.ProjetoCarro.model.Modelo;
import br.com.rafaeldaitx.ProjetoCarro.repository.CarroRepository;
import br.com.rafaeldaitx.ProjetoCarro.repository.MarcaRepository;
import br.com.rafaeldaitx.ProjetoCarro.repository.ModeloRepository;
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

    @Autowired
    private CarroRepository carroRepository;

    private static final Logger logger = Logger.getLogger(CarroService.class.getName());

    public List<Modelo> findAll() {
        logger.info("Findig all models");
        return modeloRepository.findAll();
    }

    public Modelo save(Modelo modelo){
        return modeloRepository.save(modelo);
    }

    public Optional<ModeloDTO> findViewById(Long id) {
        logger.info("Finding model with id: " + id);
        Optional<Modelo> modelo = Optional.ofNullable(modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with ID " + id)));

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
        logger.info("Deleting model with id: " + id);
        Optional<Modelo> modeloEncontrado = Optional.ofNullable(modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with ID " + id)));

        List<Carro> carroRelacionado = carroRepository.findModeloInCarro(id);

        if(!carroRelacionado.isEmpty())  throw new IllegalStateException("Não é possível deletar o modelo porque há carros associados a ele.");
        modeloRepository.delete(modeloEncontrado.get());
    }

    public ModeloDTO update(Long id, ModeloDTO modeloDTO){
        logger.info("Saving model with id: " + id);
        var modeloEncontrado = modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with ID " + id));

        var marcaAtualizada = marcaRepository.findById(modeloDTO.getMarca_id())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with ID " + id));;

            Modelo modeloAtualizado = modeloEncontrado;
            modeloAtualizado.setNome(modeloDTO.getNome());
            modeloAtualizado.setValor_fipe(modeloDTO.getValor_fipe());
            modeloAtualizado.setMarca(marcaAtualizada.);

            Modelo modeloSalvo = modeloRepository.save(modeloAtualizado);

            ModeloDTO modeloDtoConvertido2 = new ModeloDTO(
                    modeloSalvo.getId(),
                    modeloSalvo.getNome(),
                    modeloSalvo.getValor_fipe(),
                    modeloSalvo.getMarca().getId(),
                    modeloSalvo.getMarca().getNomeMarca()
            );
            return modeloDtoConvertido2;
    }
}
