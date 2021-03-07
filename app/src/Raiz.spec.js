import { render, screen } from "@testing-library/react"

import Raiz from "./Raiz"

describe("<Raiz/>", () => {
  it("deve apresentar título da aplicação", () => {
    render(<Raiz/>)
    expect(screen.getByTestId("app-header")).toHaveTextContent("Hive")
  })

  it("deve apresentar link para página de registro", () => {
    render(<Raiz/>)
    expect(screen.getByTestId("link-registro")).toHaveTextContent("Registro")
  })
})
