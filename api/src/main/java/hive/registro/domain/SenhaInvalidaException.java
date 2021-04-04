package hive.registro.domain;


public class SenhaInvalidaException extends RuntimeException {
  public SenhaInvalidaException() {
    super("Senha inválida");
  }
}
