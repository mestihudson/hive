package unit.hive.registro.adapters.in.web;


import hive.registro.adapters.in.web.CriarContaResource;
import hive.registro.domain.*;

import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;


import static org.assertj.core.api.Assertions.*;


class CriarContaRequestSpec {
  @MethodSource("situacoes")
  @ParameterizedTest void deveDispararExcecaoDominio(
    final CriarContaResource.Request request) {
    assertThatExceptionOfType(DomainException.class)
      .isThrownBy(() -> { request.toCommand(); });
  }

  static Stream<Arguments> situacoes() {
    return Stream.of(
      Arguments.of(new CriarContaResource.Request()),
      Arguments.of(new CriarContaResource.Request("", null)),
      Arguments.of(new CriarContaResource.Request(null, ""))
    );
  }
}
