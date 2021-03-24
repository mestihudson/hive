const { Pool } = require("pg")

class DB {
  constructor () {
    this.pool = new Pool({
      host: "db",
      database: "postgres",
      port: 5432,
      user: "postgres",
      password: "postgres12345678"
    })
  }

  async abrir() {
    this.client = await this.pool.connect()
  }

  async limpar () {
    if (!this.client) {
      await this.abrir()
    }
    await this.client.query("delete from usuarios;")
    await this.client.query("alter sequence usuarios_id_seq restart with 1;")
  }

  async usuarioPresente(email) {
    if (!this.client) {
      await this.abrir()
    }
    const result = await this.client
      .query(`select count(id) from usuarios where email = '${email}'`)
    return result.rows[0].count === "1"
  }

  async fechar () {
    if (this.client) {
      await this.client.end()
    }
  }
}

module.exports = DB
