package br.com.fiap.GiveNWin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.GiveNWin.models.Cupom;

public interface CupomRepository extends JpaRepository<Cupom, Long> {
    
}
