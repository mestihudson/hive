package hive.registro.domain;


public class EmailInvalidoException extends RuntimeException {
  public EmailInvalidoException() {
    super("Email inválido");
  }
}
