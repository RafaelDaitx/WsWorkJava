package br.com.rafaeldaitx.ProjetoCarro.repository;

import br.com.rafaeldaitx.ProjetoCarro.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}