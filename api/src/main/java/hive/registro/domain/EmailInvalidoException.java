package hive.registro.domain;


public class EmailInvalidoException extends DomainException {
  public EmailInvalidoException() {
    super("Email inválido");
  }
}
