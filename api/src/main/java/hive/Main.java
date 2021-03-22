package hive;


import io.agroal.api.AgroalDataSource;

import java.sql.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import lombok.extern.java.Log;


@Log
@Path("/usuarios")
public class Main {
  private final AgroalDataSource ds;

  @Inject public Main(final AgroalDataSource ds) {
    this.ds = ds;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response criarConta(final Usuario usuario) throws Throwable {
    final String query = String.format(
      "insert into usuarios values (nextval('usuarios_id_seq'), '%s', '%s')",
      usuario.email, usuario.senha
    );
    final Connection connection = ds.getConnection();
    final Statement statement = connection.createStatement();
    statement.executeUpdate(query);
    return Response.status(201).build();
  }
}
