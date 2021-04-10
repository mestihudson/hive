package hive;


import io.agroal.api.AgroalDataSource;

import java.sql.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/usuarios")
public class Main {
  private final AgroalDataSource ds;

  @Inject public Main(final AgroalDataSource ds) {
    this.ds = ds;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response criarConta(final Usuario usuario) throws Throwable {
    ds.getConnection().createStatement().executeUpdate(
      String.format(
        "insert into usuarios values (nextval('usuarios_id_seq'), '%s', '%s')",
        usuario.email,
        usuario.senha
      )
    );
    return Response.status(201).build();
  }

  public static class Usuario {
    public String email;
    public String senha;
  }

  @ApplicationPath("/api")
  public static class AppConfig extends Application {}
}
