package unit.hive.registro.domain;


import java.util.stream.Stream;

import hive.registro.domain.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static org.assertj.core.api.Assertions.*;


public class EmailValidatorDefaultSpec {
  @ParameterizedTest
  @MethodSource("emailsInvalidos")
  void deveInvalidarEmails(final String valor) {
    assertThatExceptionOfType(EmailInvalidoException.class)
      .isThrownBy(() -> { new EmailValidatorDefault().validar(valor); });
  }

  static Stream<String> emailsInvalidos() {
    return Stream.of(
      "1",
      "a",
      "abc",
      "abc@"
    );
  }

  @ParameterizedTest
  @MethodSource("emailsValidos")
  void deveValidarEmails(final String valor) {
    new EmailValidatorDefault().validar(valor);
  }

  static Stream<String> emailsValidos() {
    return Stream.of(
      "a@c",
      "1@1",
      "abc@com",
      "abc@email.com"
    );
  }
}
