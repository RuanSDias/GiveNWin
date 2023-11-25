package br.com.fiap.GiveNWin.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doador extends Usuario {

    private String cpf;
    private LocalDate dtNascimento;
    private int pontuacao;
    private Enum Generos;

    public Doador(String cpf, LocalDate dtNascimento, Enum generos) {
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        Generos = generos;
    }    
}
