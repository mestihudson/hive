package integration.hive.registro.adapters.in.web;


import hive.registro.application.port.in.*;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import java.util.Arrays;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.json.simple.*;
import org.junit.jupiter.api.*;


import static io.restassured.RestAssured.*;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;


@QuarkusTest
class CriarContaResourceSpec {
  @InjectMock CriarContaUseCase useCase;

  @Test void deveRetornarCodigoHttp201QuandoBemSucedido() {
    given()
      .contentType("application/json; charset=utf-8")
      .body(
        params(
          param("email", "usuario@email.com"),
          param("senha", "P@ssw0rD")
        )
      )
    .when()
      .post("/api/criar-conta")
    .then()
      .statusCode(201);
  }

  @Test void deveRetornarCodigoHttp400QuandoDaOcorrenciaDeExcecaoDeDominio() {
    given()
      .contentType("application/json; charset=utf-8")
      .body(params())
    .when()
      .post("/api/criar-conta")
    .then()
      .statusCode(400)
      .body(notNullValue());
  }

  @Alternative
  @Priority(1)
  @ApplicationScoped
  public static class CriarContaUseCaseFake implements CriarContaUseCase {
    @Override public void criarConta(final CriarContaCommand command) {}
  }

  static String params(final Pair... pairs) {
    final JSONObject params = new JSONObject();
    Arrays.asList(pairs).stream().forEach(pair -> {
      params.put(pair.key, pair.value);
    });
    return params.toJSONString();
  }

  static Pair param(final String key, final Object value) {
    return new Pair(key, value);
  }

  public final static class Pair {
    public String key;
    public Object value;
    public Pair(final String key, final Object value) {
      this.key = key;
      this.value = value;
    }
  }
}
