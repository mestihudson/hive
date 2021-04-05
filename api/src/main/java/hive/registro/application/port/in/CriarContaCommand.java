package hive.registro.application.port.in;


import lombok.*;


@Builder
@Getter
public class CriarContaCommand {
  @NonNull
  private final String email;
  @NonNull
  private final String senha;
}
