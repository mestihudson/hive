package hive.registro.domain;


public abstract class BaseSenhaValidator implements SenhaValidator {
  private final SenhaValidator delegate;

  public BaseSenhaValidator(final SenhaValidator delegate) {
    this.delegate = delegate;
  }

  @Override
  public SenhaValidator validar(final String valor) {
    return this.delegate.validar(valor.trim());
  }
}
