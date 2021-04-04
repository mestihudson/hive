package hive.registro.domain;


public class EmailVinculadoException extends RuntimeException {
  public EmailVinculadoException() {
    super("Email já vinculado");
  }
}
