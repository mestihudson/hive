package hive.registro.domain;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;


@Default
@ApplicationScoped
public class SenhaValidatorDefault extends BaseSenhaValidator {
  public SenhaValidatorDefault() {
    this(
     new SenhaComTamanhoMaiorQueOito(
        new SenhaComTamanhoMenorQueVinte()
      )
    );
  }

  public SenhaValidatorDefault(final SenhaValidator delegate) {
    super(delegate);
  }

  @Override
  public SenhaValidator validar(final String valor) {
    return super.validar(valor);
  }
}
