import axios from "axios"

const Api = {
  registrar (payload) {
    console.log(payload)
    return axios.post("/api/criar-conta", payload)
      .then(() => Promise.resolve())
      .catch(() => Promise.reject())
  }
}
export default Api
