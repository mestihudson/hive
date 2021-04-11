import axios from "axios"

const Api = {
  registrar (payload) {
    return axios.post("/api/criar-conta", payload)
      .then(() => Promise.resolve())
      .catch(() => Promise.reject())
  }
}
export default Api
