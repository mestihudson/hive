package hive.registro.application.port.out;


import hive.registro.domain.Usuario;


public interface CriarContaPort {
  public void criarConta(final Usuario usuario);
}
