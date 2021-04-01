package hive;


import io.agroal.api.AgroalDataSource;

import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
    if (!emailTemFormatoValido(usuario.email)) {
      return Response.status(400).build();
    }
    if (!senhaValida(usuario.senha)) {
      return Response.status(400).build();
    }
    ds.getConnection().createStatement().executeUpdate(
      String.format(
        "insert into usuarios values (nextval('usuarios_id_seq'), '%s', '%s')",
        usuario.email,
        usuario.senha
      )
    );
    return Response.status(201).build();
  }

  private boolean emailTemFormatoValido(final String valor) {
    boolean resultado = true;
    try {
      new InternetAddress(valor).validate();
    } catch (AddressException ae) {
      resultado = false;
    }
    return resultado;
  }

  private boolean senhaValida(final String valor) {
    return valor.length() >= 8;
  }
}
