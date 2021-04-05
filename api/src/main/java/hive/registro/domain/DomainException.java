package hive.registro.domain;


public class DomainException extends RuntimeException {
  public DomainException(final String message) {
    super(message);
  }

  public DomainException(final Throwable t) {
    this(t.getMessage());
  }
}
