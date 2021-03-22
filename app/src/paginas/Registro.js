import { useState } from "react"

import Api from "../servicos/Api"

function Registro() {
  const [email, setEmail] = useState("")
  const [senha, setSenha] = useState("")
  const [mostrarMensagem, setMostrarMensagem] = useState(false)
  const [mensagem, setMensagem] = useState("")

  const registrarClick = () => {
    Api.registrar({ email, senha })
      .then(() => {
        setMensagem(`Usuário ${email} registrado com sucesso`)
        setMostrarMensagem(true)
      })
  }

  const emailChange = ({ target: { value } }) => {
    setEmail(value)
  }

  const senhaChange = ({ target: { value } }) => {
    setSenha(value)
  }

  return (
    <>
      <h2 data-testid="page-header">Registro</h2>
      <input
        type="text" data-input="Email" value={email} onChange={emailChange}
      />
      <input
        type="password" data-input="Senha" value={senha} onChange={senhaChange}
      />
      <button data-trigger="Registrar" onClick={registrarClick}
      >Registrar</button>
      {
        mostrarMensagem && <span data-component="Mensagem">{ mensagem }</span>
      }
    </>
  )
}

export default Registro
