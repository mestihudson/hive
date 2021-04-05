package hive.registro.domain;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;


@Default
@ApplicationScoped
public class SenhaValidatorDefault implements SenhaValidator {
  public void validar(final String valor) {
    final String tratado = valor.trim();
    tamanhoMenorQue8(tratado);
    tamanhoMaiorQue20(tratado);
  }

  private void tamanhoMenorQue8(final String valor) {
    if (valor.length() < 8) {
      throw new SenhaInvalidaException();
    }
  }

  private void tamanhoMaiorQue20(final String valor) {
    if (valor.length() > 20) {
      throw new SenhaInvalidaException();
    }
  }
}
