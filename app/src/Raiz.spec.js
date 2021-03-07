import { render, screen } from "@testing-library/react"
import { MemoryRouter as Router } from "react-router-dom"

import Raiz from "./Raiz"

describe("<Raiz/>", () => {
  beforeEach(() => {
    render(
      <Router>
        <Raiz/>
      </Router>
    )
  })

  it("deve apresentar título da aplicação", () => {
    expect(screen.getByTestId("app-header")).toHaveTextContent("Hive")
  })

  it("deve apresentar link para página de registro", () => {
    expect(screen.getByTestId("link-registro")).toHaveTextContent("Registro")
  })
})
