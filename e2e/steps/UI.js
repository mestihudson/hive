const { Builder, By } = require('selenium-webdriver')
const { expect } = require('chai')

class UI {
  constructor () {}

  async abrir () {
    if (!this.driver) {
      this.driver = new Builder()
        .forBrowser("chrome")
        .usingServer("http://hub:4444/wd/hub")
        .build()
    }
    await this.driver.get("http://13.13.13.5:7000")
  }

  async preencherRegistro (email, senha) {
    const inputEmail = await this.driver.findElement(
      By.xpath("//*[@data-input='Email']")
    )
    await inputEmail.sendKeys(email)
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

  async mensagemPresente (mensagem) {
    const components = await this.driver.findElements(
      By.xpath("//*[@data-component='Mensagem']")
    )
    let result = components.length === 1
    for(let component of components) {
      result = await component.getText() === mensagem
    }
    return result
  }

  async fechar() {
    await this.driver.quit()
  }
}

module.exports = UI
