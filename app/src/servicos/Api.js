import axios from "axios"

const Api = {
  registrar (payload) {
    return axios.post("/api/usuarios", payload)
      .then(() => Promise.resolve())
      .catch(() => Promise.reject())
  }
}
export default Api
