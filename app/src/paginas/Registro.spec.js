import { render, screen } from "@testing-library/react"

import Registro from "./Registro"

describe("<Registro/>", () => {
  it("deve apresentar título da página", () => {
    render(<Registro/>)
    expect(screen.getByTestId("page-header")).toHaveTextContent("Registro")
  })

  it("deve possuir campo de texto 'Email'", () => {
    const { container } = render(<Registro/>)
    expect(container.querySelector("[data-input='Email']")).toBeInTheDocument()
  })

  it("deve possuir campo de senha 'Senha'", () => {
    const { container } = render(<Registro/>)
    expect(container.querySelector("[data-input='Senha']")).toBeInTheDocument()
  })

  it("deve possuir botão 'Registrar'", () => {
    const { container } = render(<Registro/>)
    expect(container.querySelector("[data-trigger='Registrar']")).toBeInTheDocument()
  })
})
