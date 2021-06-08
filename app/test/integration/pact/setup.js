import path from 'path'
import { Pact } from '@pact-foundation/pact'

global.port = 1234
global.provider = new Pact({
  cors: true,
  pactFileWriteMode: 'overwrite',
  consumer: 'consumer',
  provider: 'provider',
  port: global.port,
  spec: 2,
  log: path.resolve(process.cwd(), 'logs', 'pact.log'),
  dir: path.resolve(process.cwd(), 'pacts'),
  logLevel: 'INFO'
})
