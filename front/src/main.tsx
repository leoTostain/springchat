import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import './index.css'
import App from './components/App.tsx'
import 'bootstrap/dist/css/bootstrap.min.css'
import './axios_helper.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
