package hive.registro.adapters.out.persistence;


import hive.registro.application.port.out.*;
import hive.registro.domain.*;

import io.agroal.api.AgroalDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;


public class UsuariosJdbcRepository
  implements EmailVinculadoValidator, CriarContaPort {
  private final AgroalDataSource ds;

  @Inject public UsuariosJdbcRepository(final AgroalDataSource ds) {
    this.ds = ds;
  }

  @Override
  public void validar(final String valor) {
    try {
      final ResultSet rs = ds.getConnection().createStatement().executeQuery(
        String.format(
          "select count(id) as quant from usuarios where email = '%s'",
          valor
        )
      );
      if (rs.next() && rs.getString("quant").equals("1")) {
        throw new EmailVinculadoException();
      }
    } catch (SQLException sqle) {
      throw new DomainException(sqle);
    }
  }

  @Override
  public void criarConta(final Usuario usuario) {
    try {
      ds.getConnection().createStatement().executeUpdate(
        String.format(
          "insert into usuarios values (%s, '%s', '%s')",
          "nextval('usuarios_id_seq')",
          usuario.getEmail(),
          usuario.getSenha()
        )
      );
    } catch (SQLException sqle) {
      throw new DomainException(sqle);
    }
  }
}
