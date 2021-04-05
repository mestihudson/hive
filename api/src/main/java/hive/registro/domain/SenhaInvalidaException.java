package hive.registro.domain;


public class SenhaInvalidaException extends DomainException {
  public SenhaInvalidaException() {
    super("Senha inválida");
  }
}
