import { User } from '@/models/users'
import { PayloadAction, createSlice } from '@reduxjs/toolkit'

const userDefaultState: User = {
  name: '',
  password: '',
}

const initialState: User = (() => {
  const storageState = localStorage.getItem('user_state')
  return storageState ? JSON.parse(storageState) : userDefaultState
})()

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    createUser: (_, action: PayloadAction<User>) => action.payload,
  },
})

export const { createUser } = userSlice.actions

export default userSlice
