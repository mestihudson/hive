package hive.registro.domain;


public class SenhaComTamanhoMaiorQueOito extends BaseSenhaValidator {
  public SenhaComTamanhoMaiorQueOito(final SenhaValidator delegate) {
    super(delegate);
  }

  public SenhaComTamanhoMaiorQueOito() {
    this(null);
  }

  @Override
  public SenhaValidator validar(final String valor) {
    if (valor.length() < 8) {
      throw new SenhaInvalidaException();
    }
    return super.validar(valor);
  }
}
