import { render, screen } from "@testing-library/react"
import { MemoryRouter as Router } from "react-router-dom"

import Raiz from "./Raiz"

describe("<Raiz/>", () => {
  const renderRaiz = () => {
    render(
      <Router>
        <Raiz/>
      </Router>
    )
  }

  it("deve apresentar título da aplicação", () => {
    renderRaiz()
    expect(screen.getByTestId("app-header")).toHaveTextContent("Hive")
  })

  it("deve apresentar link para página de registro", () => {
    renderRaiz()
    expect(screen.getByTestId("link-registro")).toHaveTextContent("Registro")
  })
})
