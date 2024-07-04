package br.com.rafaeldaitx.ProjetoCarro.repository;

import br.com.rafaeldaitx.ProjetoCarro.model.Marca;
import br.com.rafaeldaitx.ProjetoCarro.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> { }