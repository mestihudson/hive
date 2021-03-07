import { Link } from "react-router-dom"

function Raiz() {
  return (
    <>
      <h1 data-testid="app-header">Hive</h1>
      <Link to="/registro" data-trigger="Registro">Registro</Link>
    </>
  )
}

export default Raiz
