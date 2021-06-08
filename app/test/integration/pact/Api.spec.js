import Api from '@/componentes/Api'

describe("Api", () => {
  describe("registrar", () => {
    afterEach(() => {
      global.provider.verify()
    })

    it("sanitize", async () => {
      const response = await Api
        .registrar({ email: 'usuario@email.com', senha: 'P@ssw0rD' })
      expect(response.message).toBe('Conta de usuário criada com sucesso')
    })
  })
})
