beforeAll(() => {
  global.provider.setup().then(() => {
    global.provider.addInteraction({
      uponReceiving: 'uma requisição para criar uma conta de usuário',
      withRequest: {
        method: 'POST',
        path: '/api/criar-conta'
      },
      withResponse: {
        status: 201,
        body: eachLike({
          message: 'Conta de usuário criada com sucesso'
        })
      }
    })
  })
})

afterAll(() => {
  global.provider.finalize()
})
