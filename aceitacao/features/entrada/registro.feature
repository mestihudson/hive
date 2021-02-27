Feature: Registro

  Como um visitante
  Eu quero poder me registrar
  Para que eu possa me autenticar

  Scenario: deve criar conta de usuário
    Given eu sou um visitante
    When eu me registro
    Then eu sou um usuário

  Scenario: não deve criar conta sem informações obrigatórias
  Scenario: não deve criar conta com email já vinculado
