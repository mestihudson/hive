const { Builder, By } = require('selenium-webdriver')
const { expect } = require('chai')

class Runner {
  constructor () {}

  async abrir () {
    if (!this.driver) {
      this.driver = new Builder()
        .forBrowser("chrome")
        .usingServer("http://hub:4444/wd/hub")
        .build()
    }
    await this.driver.get("http://app:7000")
  }

  async registro () {
    const trigger = await this.driver.findElement(
      By.xpath("//*[@data-trigger='Registro']")
    )
    await trigger.click()
  }

  async preencher (email, senha) {
    const inputEmail = await this.driver.findElement(
      By.xpath("//*[@data-input='Email']")
    )
    await input.sendKeys(email)
    const inputSenha = await this.driver.findElement(
      By.xpath("//*[@data-input='Senha']")
    )
    await inputSenha.sendKeys(senha)
  }

  async registrar () {
    const trigger = await this.driver.findElement(
      By.xpath("//*[@data-trigger='Registrar']")
    )
    await trigger.click()
  }

  async registrado (email) {
    const components = await this.driver.findElements(
      By.xpath("//*[@data-component='Mensagem']")
    )
    for(let component of components) {
      expect(await component.getText())
        .to.be(`Usuário ${email} registrado com sucesso`)
    }
  }

  async fechar() {
    await this.driver.quit()
  }
}

module.exports = Runner
