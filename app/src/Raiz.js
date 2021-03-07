import { Link, BrowserRouter as Router } from "react-router-dom"

function Raiz() {
  return (
    <>
      <h1 data-testid="app-header">Hive</h1>
      <Router>
        <Link to="/registro" data-trigger="Registro">Registro</Link>
      </Router>
    </>
  )
}

export default Raiz
