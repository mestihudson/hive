import axios from "axios"

axios.defaults.baseURL = "/api"

const Api = {
  registrar (payload) {
    console.error(payload)
    return axios.post("/usuarios", payload)
      .then((result) => Promise.resolve(result))
  }
}

export default Api
