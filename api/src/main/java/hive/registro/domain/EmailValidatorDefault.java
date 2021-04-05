package hive.registro.domain;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;


@Default
@ApplicationScoped
public class EmailValidatorDefault implements EmailValidator {
  public void validar(final String valor) {
    final String EMAIL_PATTERN = "^(.+)@(\\S+)$";
    final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    final Matcher matcher = pattern.matcher(valor);
    if (!matcher.matches()) {
      throw new EmailInvalidoException();
    }
  }
}
