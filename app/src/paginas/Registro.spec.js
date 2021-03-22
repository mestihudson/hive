import { render, screen, fireEvent, cleanup } from "@testing-library/react"
import { act } from "react-dom/test-utils"

import Registro from "./Registro"
import Api from "../servicos/Api"

jest.mock("../servicos/Api", () => {
  return {
    registrar: jest.fn()
  }
})

describe("<Registro/>", () => {
  beforeEach(jest.resetAllMocks)

  afterEach(cleanup)

  it("deve apresentar título da página", () => {
    render(<Registro/>)
    expect(screen.getByTestId("page-header")).toHaveTextContent("Registro")
  })

  it("deve possuir campo de texto 'Email'", () => {
    const { container } = renderRegistro()
    expect(getBy(container, "[data-input='Email']")).toBeInTheDocument()
  })

  it("deve possuir campo de senha 'Senha'", () => {
    const { container } = renderRegistro()
    expect(getBy(container, "[data-input='Senha']")).toBeInTheDocument()
  })

  it("deve possuir botão 'Registrar'", () => {
    const { container } = renderRegistro()
    expect(getBy(container, "[data-trigger='Registrar']")).toBeInTheDocument()
  })

  const click = async (container, selector) => {
    await act(async () => {
      await fireEvent.click(getBy(container, selector))
    })
  }

  const change = async (container, selector, value) => {
    await act(async () => {
      await fireEvent.change(getBy(container, selector), { target: { value } })
    })
  }

  const renderRegistro = () => {
    return render(<Registro/>)
  }

  const registrar = async (payload) => {
    const { container } = renderRegistro()
    await change(container, "[data-input='Email']", payload.email)
    await change(container, "[data-input='Senha']", payload.senha)
    await click(container, "[data-trigger='Registrar']")
    return { container }
  }

  const payload = {
    email: 'nome@email.com',
    senha: 'P@ssw0rD'
  }

  const getBy = (container, selector) => {
    return container.querySelector(selector)
  }

  it("deve disparar api quando acionado botão 'Registrar'", async () => {
    Api.registrar.mockImplementation(() => Promise.resolve())
    await registrar(payload)
    expect(Api.registrar).toHaveBeenCalledWith(payload)
  })

  it("deve apresentar mensagem de sucesso para retorno da api", async () => {
    Api.registrar.mockImplementation(() => Promise.resolve())
    const { container } = await registrar(payload)
    expect(getBy(container, "[data-component='Mensagem']"))
      .toHaveTextContent(`Usuário ${payload.email} registrado com sucesso`)
  })
})
