import { User, Phone } from '@/models'
import { PayloadAction, createSlice } from '@reduxjs/toolkit'

const userDefaultState: User = {
  name: '',
  password: '',
  wishlist: [],
  isLoggedIn: false
}

const initialState: User = (() => {
  const storageState = localStorage.getItem('__user_state__');
  return storageState ? JSON.parse(storageState) : userDefaultState;
})();

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    createUser: (_, action: PayloadAction<User>) => ({ ...action.payload, isLoggedIn: true }),
    resetUser: () => userDefaultState,
    addToWishlist: (state, action: PayloadAction<Phone[]>) =>
      ({ ...state, wishlist: [...state.wishlist, ...action.payload] }),
    removeFromWishlist: (state, action: PayloadAction<Phone>) =>
      ({ ...state, wishlist: state.wishlist.filter(item => item.id !== action.payload.id) }),
  },
})

export const { createUser, resetUser, addToWishlist, removeFromWishlist } = userSlice.actions

export default userSlice.reducer
