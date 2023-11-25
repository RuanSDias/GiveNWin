package br.com.fiap.GiveNWin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.GiveNWin.models.Doador;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
    
}
