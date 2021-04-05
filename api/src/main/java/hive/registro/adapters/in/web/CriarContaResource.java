package hive.registro.adapters.in.web;
import lombok.*;
public class CriarContaResource {
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Request {
    public String email;
    public String senha;

    public CriarContaCommand toCommand() throws DomainException {
      try {
        return CriarContaCommand.builder()
          .email(email)
          .senha(senha)
        .build();
      } catch(NullPointerException npe) {
        throw new DomainException(npe);
      }
    }
  }
}
