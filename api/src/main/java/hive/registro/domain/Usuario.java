package hive.registro.domain;


import lombok.*;


@Builder
public class Usuario {
  private final String email;
  private final String senha;
}
