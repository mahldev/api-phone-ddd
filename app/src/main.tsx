import ReactDOM from 'react-dom/client'
import './index.css'
import { BrowserRouter, Route, Routes, useNavigate } from 'react-router-dom'
import { NextUIProvider } from '@nextui-org/react'
import { IndexPage } from './pages/IndexPage'
import { AllPhonePage } from './pages/AllPhonePage'
import { PhoneDetailsView } from './pages/PhoneDatailsPage'

function App() {
  const navigate = useNavigate()

  return (
    <NextUIProvider navigate={navigate}>
      <Routes>
        <Route path='/' element={<IndexPage />} />
        <Route path='/phones' element={<AllPhonePage />} />
        <Route path='/phones/:id' element={<PhoneDetailsView />} />
      </Routes>
    </NextUIProvider>
  )
}

ReactDOM.createRoot(document.getElementById('root')!).render(
  <NextUIProvider>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </NextUIProvider>
)
