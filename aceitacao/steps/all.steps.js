const { After, Before, Given, When, Then } = require('@cucumber/cucumber')
const Runner = require('./Runner')

let runner

Before(() => {
  runner = new Runner()
})

After(async () => {
  runner.fechar()
})

Given(`eu sou um visitante`, async () => {
  await runner.abrir()
})

When(`eu me registro`, async () => {
  await runner.registro()
  await runner.preencher(
    "usuario@email.com",
    "P@ssw0rD"
  )
  await runner.registrar()
})

Then(`eu sou um usuário`, async () => {
  await runner.registrado("usuario@email.com")
})
