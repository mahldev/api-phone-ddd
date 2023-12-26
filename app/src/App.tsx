import { NextUIProvider } from '@nextui-org/react'
import { Route, Routes, useNavigate } from 'react-router-dom'
import { IndexPage, AllPhonePage, PhoneDetailsView } from '@/pages'

export const App = () => {
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

export default App
