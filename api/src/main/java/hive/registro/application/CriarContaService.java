package hive.registro.application;


import hive.registro.application.port.in.*;
import hive.registro.application.port.out.*;
import hive.registro.domain.*;

import javax.inject.Inject;


public class CriarContaService implements CriarContaUseCase {
  private final EmailValidator emailValidator;
  private final SenhaValidator senhaValidator;
  private final EmailVinculadoValidator emailVinculadoValidator;
  private final CriarContaPort criarContaPort;

  @Inject public CriarContaService(
    final EmailValidator emailValidator,
    final SenhaValidator senhaValidator,
    final EmailVinculadoValidator emailVinculadoValidator,
    final CriarContaPort criarContaPort
  ) {
    this.emailValidator = emailValidator;
    this.senhaValidator = senhaValidator;
    this.emailVinculadoValidator = emailVinculadoValidator;
    this.criarContaPort = criarContaPort;
  }

  public void criarConta(final CriarContaCommand command) {
    emailValidator.validar(command.getEmail());
    senhaValidator.validar(command.getSenha());
    emailVinculadoValidator.validar(command.getEmail());
    final Usuario usuario = Usuario.builder()
      .email(command.getEmail())
      .senha(command.getSenha())
      .build();
    System.out.println(usuario.toString());
    criarContaPort.criarConta(usuario);
  }
}
