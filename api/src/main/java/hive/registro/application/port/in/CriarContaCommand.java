package hive.registro.application.port.in;


import lombok.*;
import lombok.experimental.*;


@Builder
@Getter
public class CriarContaCommand {
  private final String email;
  private final String senha;
}
