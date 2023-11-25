package br.com.fiap.GiveNWin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.GiveNWin.models.Receptor;

public interface ReceptorRepository extends JpaRepository<Receptor, Long> {
    
}
