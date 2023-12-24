import ReactDOM from 'react-dom/client'
import './index.css'
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from 'react-router-dom'
import { NextUIProvider } from '@nextui-org/react'
import { IndexPage } from './pages/IndexPage'
import { AllPhonePage } from './pages/AllPhonePage'
import { PhoneDetailsView } from './pages/PhoneDatailsPage'

const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path='/' element={<IndexPage />} />
      <Route path='/phones' element={<AllPhonePage />} />
      <Route path='/phones/:id' element={<PhoneDetailsView />} />
    </>
  )
)

ReactDOM.createRoot(document.getElementById('root')!).render(
  <NextUIProvider>
    <main className='font-montserrat'>
      <RouterProvider router={router} />
    </main>
  </NextUIProvider>
)
