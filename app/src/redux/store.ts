import { configureStore } from '@reduxjs/toolkit'
import userReducers from './users/slice'
import persistanceToLocalStorageMiddelware from './users/persistanceToLocalStorageMiddelware'

export const store = configureStore({
  reducer: {
    user: userReducers
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(persistanceToLocalStorageMiddelware)
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
