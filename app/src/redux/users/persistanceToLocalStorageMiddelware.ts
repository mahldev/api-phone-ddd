import { Middleware } from '@reduxjs/toolkit'

const persistanceToLocalStorageMiddelware: Middleware = (store) => (next) => (action) => {
  next(action)
  localStorage.setItem('__user_state__', JSON.stringify(store.getState().user))
}

export default persistanceToLocalStorageMiddelware
