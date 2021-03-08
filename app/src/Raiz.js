import { Link, BrowserRouter as Router, Switch, Route } from "react-router-dom"

import Registro from "./paginas/Registro"

function Raiz() {
  return (
    <>
      <h1 data-testid="app-header">Hive</h1>
      <Router>
        <Link to="/registro" data-trigger="Registro">Registro</Link>
        <Switch>
          <Route>
            <Registro/>
          </Route>
        </Switch>
      </Router>
    </>
  )
}

export default Raiz
