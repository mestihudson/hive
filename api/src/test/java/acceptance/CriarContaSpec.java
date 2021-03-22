package acceptance;


import io.restassured.RestAssured;

import java.io.*;
import java.util.*;

import org.assertj.db.type.*;
import org.junit.jupiter.api.*;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.assertj.db.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;


class CriarContaSpec extends DbSpec {
  Changes changes;

  @BeforeEach void clean() throws Throwable {
    execute("db/clean.sql");
  }

  @BeforeEach void initChanges() {
    changes = changes();
  }

  @BeforeEach void initRestAssured() {
    configRestAssured("http://localhost", 8080, "/api");
  }

  @Test public void comSucessoPdweb() throws Throwable {
    changes.setStartPointNow();

    final String EMAIL = "usuario@email.com";
    final String SENHA = "P@ssw0rD";

    given()
      .contentType("application/json; charset=utf-8")
      .body(
        params(
          param("email", EMAIL),
          param("senha", SENHA)
        )
      )
    .when()
      .post("/usuarios")
    .then()
      .statusCode(201)
    ;

    changes.setEndPointNow();

    assertNumberOfChangesIsOn(
      changes, 1,
      "usuarios"
    );
    assertThat(changes)
      .onTable("usuarios")
        .change()
          .isCreation()
          .rowAtStartPoint()
            .doesNotExist()
          .rowAtEndPoint()
            .exists()
            .value("id").isEqualTo(1L)
            .value("email").isEqualTo(EMAIL)
            .value("senha").isEqualTo(SENHA);
  }
}
