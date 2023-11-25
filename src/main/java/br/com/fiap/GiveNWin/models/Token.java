package br.com.fiap.GiveNWin.models;

public record Token(
    String token,
    String type,
    String prefix
) {}