import { render, screen } from "@testing-library/react"
import { MemoryRouter as Router } from "react-router-dom"

import Raiz from "./Raiz"

describe("<Raiz/>", () => {
  const renderRaiz = () => {
    return render(
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
    const { container } = renderRaiz()
    expect(container.querySelector("[data-trigger='Registro']")).toHaveTextContent("Registro")
  })
})
