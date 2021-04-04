package unit.hive.registro.application;


import hive.registro.application.CriarContaService;
import hive.registro.application.port.in.*;
import hive.registro.application.port.out.*;
import hive.registro.domain.*;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


class CriarContaServiceSpec {
  final EmailValidator emailValidator = mock(EmailValidator.class);
  final SenhaValidator senhaValidator = mock(SenhaValidator.class);
  final EmailVinculadoValidator emailVinculadoValidator = mock(
    EmailVinculadoValidator.class
  );
  final CriarContaPort criarContaPort = mock(CriarContaPort.class);
  final CriarContaCommand command = CriarContaCommand.builder()
    .email("inválido")
    .senha("qualquer")
    .build();
  final CriarContaService service = new CriarContaService(
    emailValidator, senhaValidator, emailVinculadoValidator, criarContaPort
  );

  @Test void deveFalharParaEmailInvalido() {
    doThrow(new EmailInvalidoException())
      .when(emailValidator).validar(any(String.class));
    assertThatExceptionOfType(EmailInvalidoException.class)
      .isThrownBy(() -> { service.criarConta(command); })
      .withMessage("Email inválido");
  }

  @Test void deveFalharParaSenhaInvalida() {
    doThrow(new SenhaInvalidaException())
      .when(senhaValidator).validar(any(String.class));
    assertThatExceptionOfType(SenhaInvalidaException.class)
      .isThrownBy(() -> { service.criarConta(command); })
      .withMessage("Senha inválida");
  }

  @Test void deveFalharParaEmailJaVinculado() {
    doThrow(new EmailVinculadoException())
      .when(emailVinculadoValidator).validar(any(String.class));
    assertThatExceptionOfType(EmailVinculadoException.class)
      .isThrownBy(() -> { service.criarConta(command); })
      .withMessage("Email já vinculado");
  }

  @Test void deveCriarConta() {
    service.criarConta(command);
    verify(criarContaPort, times(1)).criarConta(any());
  }
}
