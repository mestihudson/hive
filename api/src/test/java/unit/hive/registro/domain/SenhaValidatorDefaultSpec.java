package unit.hive.registro.domain;


import java.util.stream.Stream;

import hive.registro.domain.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static org.assertj.core.api.Assertions.*;


public class SenhaValidatorDefaultSpec {
  @ParameterizedTest
  @MethodSource("tamanhoMenorQue8")
  void deveInvalidarSenhasComTamanhoMenorQue8(final String valor) {
    assertThatExceptionOfType(SenhaInvalidaException.class)
      .isThrownBy(() -> { new SenhaValidatorDefault().validar(valor); });
  }

  static Stream<String> tamanhoMenorQue8() {
    return Stream.of(
      "1",
      "a",
      "abc",
      "abc@",
      "1234567",
      "        "
    );
  }

  @ParameterizedTest
  @MethodSource("tamanhoMaiorQue20")
  void deveInvalidarSenhasComTamanhoMaiorQue20(final String valor) {
    assertThatExceptionOfType(SenhaInvalidaException.class)
      .isThrownBy(() -> { new SenhaValidatorDefault().validar(valor); });
  }

  static Stream<String> tamanhoMaiorQue20() {
    return Stream.of(
      "123456789012345678901",
      "1234567890123456789011"
    );
  }
}
