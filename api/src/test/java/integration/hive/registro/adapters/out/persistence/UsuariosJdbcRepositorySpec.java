package integration.hive.registro.adapters.out.persistence;


import hive.registro.adapters.out.persistence.UsuariosJdbcRepository;
import hive.registro.domain.*;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

import org.assertj.db.type.*;
import org.junit.jupiter.api.*;


import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.db.api.Assertions.assertThat;


@QuarkusTest
class UsuariosJdbcRepositorySpec {
  UsuariosJdbcRepository repository;
  @Inject AgroalDataSource ds;
  Changes changes;
  final Long ID = 1L;
  final String EMAIL = "usuario@meuemail.com";
  final String SENHA = "senha";

  @BeforeEach
  void beforeEach() {
    repository = new UsuariosJdbcRepository(ds);
  }

  @Test void deveDispararExcecaoQuandoEmailJaVinculado() throws Throwable {
    cleandb();
    insertUsuario();
    assertThatExceptionOfType(EmailVinculadoException.class)
      .isThrownBy(() -> { repository.validar(EMAIL); });
  }

  @Test void deveInserirUmRegistroNaTabelaUsuarios() throws Throwable {
    cleandb();
    changes = new Changes(ds);
    changes.setStartPointNow();

    final UsuariosJdbcRepository repository = new UsuariosJdbcRepository(ds);
    repository.criarConta(
      Usuario.builder().email(EMAIL).senha(SENHA).build()
    );

    changes.setEndPointNow();

    assertThat(changes)
      .change()
        .rowAtStartPoint()
          .doesNotExist()
        .rowAtEndPoint()
          .exists()
          .hasValues(ID, EMAIL, SENHA);
  }

  void execute(final String query) {
    try {
      ds.getConnection().createStatement().executeUpdate(query);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  void cleandb() {
    execute("delete from usuarios;");
    execute("alter sequence usuarios_id_seq restart with 1;");
  }

  void insertUsuario() {
    execute(
      String.format(
        "insert into usuarios (id, email, senha) values (%s, '%s', '%s');",
        "nextval('usuarios_id_seq')",
        EMAIL,
        SENHA
      )
    );
  }
}
