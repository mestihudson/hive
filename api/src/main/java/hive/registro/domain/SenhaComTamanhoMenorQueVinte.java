package hive.registro.domain;


public class SenhaComTamanhoMenorQueVinte extends BaseSenhaValidator {
  public SenhaComTamanhoMenorQueVinte(final SenhaValidator delegate) {
    super(delegate);
  }

  public SenhaComTamanhoMenorQueVinte() {
    this(null);
  }

  @Override
  public SenhaValidator validar(final String valor) {
    if (valor.length() > 20) {
      throw new SenhaInvalidaException();
    }
    return super.validar(valor);
  }
}
