package br.com.fiap.GiveNWin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.GiveNWin.models.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    
}
