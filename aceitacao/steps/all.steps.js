const {
  After, Before, Given, When, Then, setDefaultTimeout
} = require("@cucumber/cucumber")
const { expect } = require("chai")

const DB = require("./DB")
const UI = require("./UI")

let ui
let db
let usuario

setDefaultTimeout(2 * 60 * 1000)

Before(() => {
  db = new DB()
  ui = new UI()
  usuario = { email: "usuario@email.com", senha: "P@ssw0rD" }
})

Before(async () => {
  await db.limpar()
})

After(async () => {
  await ui.fechar()
  await db.fechar()
})

Given("eu sou um visitante", async () => {
  expect(await db.usuarioPresente(usuario.email)).to.be.false
})

When("eu me registro", async () => {
  await ui.abrir()
  await ui.preencherRegistro(usuario.email, usuario.senha)
  await ui.registrar()
})

Then("eu sou um usuário", async () => {
  expect(
    await ui.mensagemPresente(`Usuário ${usuario.email} registrado com sucesso`)
  ).to.be.true
  expect(await db.usuarioPresente(usuario.email)).to.be.true
})
