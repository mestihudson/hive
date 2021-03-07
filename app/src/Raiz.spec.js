import { render, screen } from "@testing-library/react"
import { MemoryRouter as Router } from "react-router-dom"

import Raiz from "./Raiz"

describe("<Raiz/>", () => {
  it("deve apresentar título da aplicação", () => {
    render(
      <Router>
        <Raiz/>
      </Router>
    )
    expect(screen.getByTestId("app-header")).toHaveTextContent("Hive")
  })

  it("deve apresentar link para página de registro", () => {
    render(
      <Router>
        <Raiz/>
      </Router>
    )
    expect(screen.getByTestId("link-registro")).toHaveTextContent("Registro")
  })
})
