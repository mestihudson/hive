#language: pt
Funcionalidade: Criar conta

  Como um visitante
  Eu quero criar uma conta de usuário
  Para entrar e jogar uma partida

  @ok
  Cenário: Deve criar conta
    Dado que eu sou um visitante
    Quando eu tento me registrar
    Então eu consigo criar uma conta de usuário

  @ok
  Cenário: Deve falhar para email inválido
    Dado que eu sou um visitante
    Quando eu tento me registrar com um email inválido
    Então eu não consigo criar uma conta de usuário

  Cenário: Deve falhar para senha inválida
    Dado que eu sou um visitante
    Quando eu tento me registrar com uma senha inválida
    Então eu não consigo criar uma conta de usuário

  Cenário: Deve falhar para email já vinculado
    Dado que eu sou um visitante
    Quando eu tento me registrar com um email já vinculado
    Então eu não consigo criar uma conta de usuário
