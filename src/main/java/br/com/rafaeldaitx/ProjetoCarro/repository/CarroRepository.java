package br.com.rafaeldaitx.ProjetoCarro.repository;

import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    @Query("SELECT c FROM Carro c WHERE c.modelo.id = :id")
    List<Carro> findModeloInCarro(@Param("id")Long id);
}