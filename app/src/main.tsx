import ReactDOM from 'react-dom/client'
import './index.css'
import { BrowserRouter } from 'react-router-dom'
import { NextUIProvider } from '@nextui-org/react'
import { store } from './redux/store'
import { Provider } from 'react-redux'
import App from './App'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <NextUIProvider>
    <BrowserRouter>
      <Provider store={store}>
        <main className='font-roboto'>
          <App />
        </main>
      </Provider>
    </BrowserRouter>
  </NextUIProvider>
)
