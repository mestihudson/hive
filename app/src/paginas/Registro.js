import { useState } from "react"

import Api from "../servicos/Api"

function Registro() {
  const [email, setEmail] = useState("")
  const [senha, setSenha] = useState("")
  const [mostrarMensagem, setMostrarMensagem] = useState(false)
  const [mensagem, setMensagem] = useState("")

  const emailChange = ({ target }) => setEmail(target.value)

  const senhaChange = ({ target }) => setSenha(target.value)

  const registrarClick = () => {
    Api.registrar({ email, senha })
      .then(() => {
        setMensagem(
          `Usuário ${email} registrado com sucesso`
        )
        setMostrarMensagem(true)
      })
      .catch(() => {
        setMensagem(
          `Usuário ${email} não pode ser registrado`
        )
        setMostrarMensagem(true)
      })
  }

  return (
    <>
      <input type="text" data-input="Email" onChange={emailChange}/>
      <input type="password" data-input="Senha" onChange={senhaChange}/>
      <button data-trigger="Registrar" onClick={registrarClick}
      >Registrar</button>
      {
        mostrarMensagem &&
          <span data-component="Mensagem"
          >{ mensagem }</span>
      }
    </>
  )
}
export default Registro
