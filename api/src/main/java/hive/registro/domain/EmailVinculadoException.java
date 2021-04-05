package hive.registro.domain;


public class EmailVinculadoException extends DomainException {
  public EmailVinculadoException() {
    super("Email já vinculado");
  }
}
