package hive.registro.domain;


import lombok.*;


@Builder
@Getter
public class Usuario {
  private final String email;
  private final String senha;
}
