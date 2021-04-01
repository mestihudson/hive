const {
  After, Before, Given, When, Then, setDefaultTimeout
} = require("@cucumber/cucumber")
const { expect } = require("chai")

const DB = require("./DB")
const UI = require("./UI")

let ui
let db
let usuario
let emailInvalido = "1"
let senhaInvalida = "1"

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

Given("que eu sou um visitante", async () => {
  expect(await db.usuarioPresente(usuario.email, usuario.senha)).to.be.false
})

When("eu tento me registrar", async () => {
  await registrarCom(usuario.email, usuario.senha)
})

Then("eu consigo criar uma conta de usuário", async () => {
  await usuarioRegistrado(usuario.email, usuario.senha, true)
})

When("eu tento me registrar com um email inválido", async () => {
  await registrarCom(emailInvalido, usuario.senha)
})

When("eu tento me registrar com uma senha inválida", async () => {
  await registrarCom(usuario.email, senhaInvalida)
})

When("eu tento me registrar com um email já vinculado", async () => {
  await db.criarContaCom(usuario.email)
  await registrarCom(usuario.email, usuario.senha)
})

Then("eu não consigo criar uma conta de usuário", async () => {
  await usuarioRegistrado(usuario.email, usuario.senha, false)
})

const registrarCom = async (email, senha) => {
  await ui.abrir()
  await ui.preencherRegistro(email, senha)
  await ui.registrar()
}

const usuarioRegistrado = async (email, senha, registrado) => {
  await new Promise(f => setTimeout(f, 1000))
  expect(
    await ui
      .mensagemPresente(`Usuário ${usuario.email} registrado com sucesso`)
  )
    .to.be.equal(registrado)
  expect(await db.usuarioPresente(usuario.email, usuario.senha))
    .to.be.equal(registrado)
}
