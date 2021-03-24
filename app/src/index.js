import { StrictMode } from 'react'
import { render } from 'react-dom'

import Raiz from './Raiz'

render(
  <StrictMode>
    <Raiz/>
  </StrictMode>,
  document.getElementById('raiz')
)
