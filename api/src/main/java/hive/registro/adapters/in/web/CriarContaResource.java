package hive.registro.adapters.in.web;


import hive.registro.application.port.in.*;
import hive.registro.domain.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import lombok.*;


@Path("criar-conta")
public class CriarContaResource {
  private final CriarContaUseCase uc;

  @Inject public CriarContaResource(final CriarContaUseCase uc) {
    this.uc = uc;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response criarConta(final Request request) {
    try {
      uc.criarConta(request.toCommand());
      return Response.status(201).build();
    } catch (DomainException de) {
      return Response.status(400).entity(de.getMessage()).build();
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  public static class Request {
    public String email;
    public String senha;

    public CriarContaCommand toCommand() throws DomainException {
      try {
        return CriarContaCommand.builder()
          .email(email)
          .senha(senha)
        .build();
      } catch(NullPointerException npe) {
        throw new DomainException(npe);
      }
    }
  }
}
